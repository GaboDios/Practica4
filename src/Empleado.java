public class Empleado {
    private String nombre;
    private String rfc;
    private Area areaTrabajo;
    private Piso pisoTrabajo;
    private boolean permisoColor;

    public Empleado(String nombre, String rfc, Area areaTrabajo, Piso pisoTrabajo, boolean permisoColor) {
        this.nombre = nombre;
        this.rfc = rfc;
        this.areaTrabajo = areaTrabajo;
        this.pisoTrabajo = pisoTrabajo;
        this.permisoColor = permisoColor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public Area getAreaTrabajo() {
        return areaTrabajo;
    }

    public Piso getPisoTrabajo() {
        return pisoTrabajo;
    }

    public boolean tienePermisoColor() {
        return permisoColor;
    }
}
