Crea desde cero el frontend de una aplicación para gestionar los empleados de una empresa (CRUD). El backend ya existe y está funcionando; abajo está su contrato exacto. No inventes endpoints ni campos que no estén aquí.

## Stack

- React 19 + TypeScript (modo estricto) + Vite
- TanStack Query para todas las llamadas a la API (caché, estados de carga/error, invalidación tras mutaciones)
- React Hook Form + Zod para los formularios
- React Router para las rutas
- shadcn/ui + Tailwind CSS para los componentes visuales
- `fetch` nativo envuelto en un cliente HTTP propio (sin axios)
- Vitest + Testing Library para los tests

La interfaz debe estar en español.

## Arquitectura (por features)

```
src/
├── app/                      # arranque: router, QueryClientProvider, layout principal, Toaster
├── shared/
│   ├── api/httpClient.ts     # cliente HTTP base (ver "Cliente HTTP")
│   ├── config/env.ts         # lee VITE_API_URL
│   └── ui/                   # componentes reutilizables (tabla, diálogo de confirmación, etc.)
└── features/
    ├── catalogs/
    │   ├── api/              # getDocumentTypes, getRoles, getCompanies
    │   ├── model/            # tipos TS
    │   └── hooks/            # useDocumentTypes, useRoles, useCompanies
    └── employees/
        ├── api/              # employeesApi: list, register, modify, remove
        ├── model/            # tipos TS + esquemas Zod
        ├── hooks/            # useEmployees, useRegisterEmployee, useModifyEmployee, useDeleteEmployee
        ├── components/       # EmployeeTable, EmployeeForm, CompanyFilter, DeleteEmployeeDialog
        └── pages/            # EmployeesListPage, NewEmployeePage, EditEmployeePage
```

Reglas de dependencia:
- Los componentes y páginas **nunca** llaman a `fetch` directamente: usan hooks.
- Los hooks usan las funciones de `api/`.
- Solo `api/` y `shared/api` conocen URLs.

## Configuración

- Archivo `.env` con `VITE_API_URL=http://localhost:8080`, y un `.env.example` con el mismo valor.
- El servidor de desarrollo de Vite debe correr en el puerto **5173**: es el único origen permitido por CORS en el backend.

## Contrato de la API

URL base: `${VITE_API_URL}/api/v1`

### Envoltorio de todas las respuestas (éxito y error)

```ts
type ApiResponse<T> = {
  datos: T[];        // siempre es un arreglo (vacío en errores y en DELETE)
  mensajes: string[]; // mensajes para mostrar al usuario, ya en español
};
```

### Catálogos (para los `<select>` de los formularios)

| Método | Ruta | `datos` |
|---|---|---|
| GET | `/document-types` | `{ id: string; name: string }[]` |
| GET | `/roles` | `{ id: string; name: string }[]` |
| GET | `/companies` | `{ id: string; name: string; nit: string }[]` |

Todos los `id` son UUID. Los catálogos cambian poco: usa `staleTime` alto (por ejemplo, 10 minutos).

### Empleados

**Listar:** `GET /employees`, con filtro opcional `?companyId=<uuid>`.

`datos` es `EmployeeDetail[]`, ordenado por nombre:
```ts
type EmployeeDetail = {
  id: string;
  name: string;
  document: string;
  documentType: { id: string; name: string };
  role: { id: string; name: string };
  company: { id: string; name: string; nit: string };
};
```
Respuestas:
- 200: con la lista, que puede venir vacía.
- 400: si el `companyId` no existe.

**Registrar:** `POST /employees`

Cuerpo:
```ts
{ name: string; document: string; documentTypeId: string; roleId: string; companyId: string }
```
Respuestas:
- 201: `datos[0]` trae `{ id, name, document, documentTypeId, roleId, companyId }`. Solo trae IDs, no nombres.
- 400: si falla una regla de negocio. El motivo viene en `mensajes[0]`.

**Modificar (parcial):** `PATCH /employees/{id}`

Cuerpo: los mismos campos que el registro, todos opcionales. Envía **solo los campos que el usuario cambió**.
Respuestas:
- 200: `datos[0]` igual que en el registro.
- 400: si falla una regla, o si el cuerpo no trae ningún cambio ("Debe enviar al menos un campo para modificar").
- 404: si el empleado no existe.

**Eliminar:** `DELETE /employees/{id}`

Respuestas:
- 200: `datos` vacío, `mensajes[0]` = "Empleado eliminado exitosamente".
- 404: si el empleado no existe.

### Reglas de validación del backend (replícalas en Zod)

- `name`: obligatorio después de quitar espacios a los lados, máximo 100 caracteres.
- `document`: obligatorio, alfanumérico, entre 5 y 20 caracteres (regex `^[A-Za-z0-9]{5,20}$`).
- `documentTypeId`, `roleId`, `companyId`: obligatorios, UUID válidos de los catálogos.
- No puede haber dos empleados con el mismo tipo y número de documento. Esto solo lo valida el backend: muestra su mensaje.

## Cliente HTTP (`shared/api/httpClient.ts`)

- Antepone la URL base y envía `Content-Type: application/json` cuando hay cuerpo.
- Si la respuesta es 2xx, devuelve el `ApiResponse<T>` parseado.
- Si no es 2xx:
  - intenta parsear el `ApiResponse` y lanza un `ApiError` con `status` y `message` igual a `mensajes[0]`;
  - si el cuerpo no se puede parsear, usa un mensaje genérico: "Se ha presentado un error inesperado".
- Si hay un error de red (backend apagado), lanza un `ApiError` con el mensaje "No se pudo conectar con el servidor".

## Pantallas y comportamiento

### Listado (`/empleados`, también es la ruta por defecto `/`)

- **Tabla** con las columnas Nombre, Tipo de documento, Documento, Rol, Empresa y Acciones (Editar, Eliminar).
- **Filtro por empresa:** un select con la opción "Todas" más las empresas. Se sincroniza con el query param `?companyId=`.
- **Botón "Nuevo empleado".**
- **Estados:**
  - Carga: skeleton.
  - Lista vacía: mensaje y botón para crear.
  - Error: mensaje del `ApiError` y botón "Reintentar".

### Crear (`/empleados/nuevo`)

- Formulario con los cinco campos; los tres IDs se eligen en selects alimentados por los catálogos.
- Valida con Zod antes de enviar y muestra los errores junto a cada campo.
- **Al tener éxito:** toast con `mensajes[0]`, invalida la query de empleados y vuelve al listado.
- **Si el backend responde 400:** muestra `mensajes[0]` en un toast o una alerta, sin perder lo escrito.

### Editar (`/empleados/:id/editar`)

- Usa el mismo componente `EmployeeForm` que crear, precargado con los datos del empleado. Tómalos de la caché del listado o, si no están, del `GET /employees`.
- Al enviar, calcula la diferencia con los valores originales y manda en el PATCH **solo los campos cambiados**. Si no cambió nada, deshabilita el botón Guardar.
- **Si responde 404:** toast "El empleado indicado no existe" y vuelve al listado.

### Eliminar

- Diálogo de confirmación con el nombre del empleado ("¿Eliminar a Ana Pérez? Esta acción no se puede deshacer.").
- **Al confirmar:** llama a DELETE, muestra un toast con el mensaje e invalida el listado.
- **Si responde 404:** también invalida el listado, porque ya no existe.

## Calidad

- Tipos TS para todo el contrato; nada de `any`.
- Tests con Vitest + Testing Library, con `fetch` mockeado:
  - `httpClient`: éxito, error 400 con `mensajes`, error de red.
  - Esquema Zod del empleado: casos válidos e inválidos.
  - `EmployeeForm`: muestra errores de validación y, en modo edición, envía solo los campos cambiados.
- Scripts en `package.json`: `dev`, `build`, `preview`, `test`, `lint`.
- Un `README.md` breve: requisitos, cómo configurar `.env`, cómo levantar el backend en `localhost:8080` y cómo correr el front.

Cuando termines, ejecuta `npm run build` y `npm test`, y confirma que ambos pasan.
