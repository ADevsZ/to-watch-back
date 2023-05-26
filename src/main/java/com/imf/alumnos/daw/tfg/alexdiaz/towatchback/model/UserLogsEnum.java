package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model;

public enum UserLogsEnum {
    USUARIO_REGISTRADO("Se ha registrado el nuevo usuario "),
    MODIFICACION_DATOS_USUARIO("Se han modificado los datos de perfil del usuario "),
    PELICULA_VALORADA(" ha valorado la película "),
    SERIE_VALORADA(" ha valorado la serie "),
    PELICULA_VISTA(" ha visto la película "),
    SERIE_VISTA(" ha visto la serie "),
    WATCHLIST_COMPLETADA(" ha completado la Watchlist: "),
    WATCHLIST_ELIMINADA(" ha eliminado la Watchlist: "),
    WATCHLIST_ACTIVADA(" ha desactivado la Watchlist: "),
    WATCHLIST_DESACTIVADA(" ha activado la Watchlist: ");

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
