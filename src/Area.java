public class Area {
    private String nombreArea;
    private Piso piso;
    private boolean permisoColor;

    public Area(String nombreArea, Piso piso, boolean permisoColor) {
        this.nombreArea = nombreArea;
        this.piso = piso;
        this.permisoColor = permisoColor;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public Piso getPiso() {
        return piso;
    }

    public boolean tienePermisoColor() {
        return permisoColor;
    }
}
