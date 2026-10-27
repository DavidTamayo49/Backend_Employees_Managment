package com.dtm.reactive_application.infrastructure.primaryadapters.controller.response;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {

    private List<String> mensajes = new ArrayList<>();
    private List<T> datos = new ArrayList<>();

    public final List<String> getMensajes() {
        return mensajes;
    }

    public final void setMensajes(final List<String> mensajes) {
        this.mensajes = mensajes;
    }

    public final List<T> getDatos() {
        return datos;
    }

    public final void setDatos(final List<T> datos) {
        this.datos = datos;
    }
}
