package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model;

public enum UserLogsEnum {
    USUARIO_REGISTRADO("Se ha registrado el nuevo usuario "),
    MODIFICACION_DATOS_USUARIO("Se han modificado los datos de perfil del usuario "),
    PELICULA_VALORADA(" ha valorado la película "),
    SERIE_VALORADA(" ha valorado la serie ");

    public final String label;
    UserLogsEnum(String label) {this.label = label; }

    public static UserLogsEnum valueOfLabel(String label) {
        for (UserLogsEnum l: values()) {
            if (l.label.equals(label)) {
                return l;
            }
        }
        return null;
    }
}
