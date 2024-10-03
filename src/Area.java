/**
 * La clase Area representa un área dentro de un piso en una empresa.
 * Cada área tiene un nombre, un piso al que pertenece, y un indicador de si
 * tiene permiso para el uso de colores.
 *
 * @author Gabo, Pedro Yamil, Isaac (Los Hijos de Korhal)
 */
public class Area {
    private String nombreArea;
    private Piso piso;
    private boolean permisoColor;

    /**
     * Constructor para crear un nuevo objeto Area.
     *
     * @param nombreArea El nombre del área.
     * @param piso El piso al que pertenece el área.
     * @param permisoColor Indica si el área tiene permiso para el uso de color.
     */
    public Area(String nombreArea, Piso piso, boolean permisoColor) {
        this.nombreArea = nombreArea;
        this.piso = piso;
        this.permisoColor = permisoColor;
    }

    /**
     * Obtiene el nombre del área.
     *
     * @return El nombre del área.
     */
    public String getNombreArea() {
        return nombreArea;
    }

    /**
     * Obtiene el piso al que pertenece el área.
     *
     * @return El piso al que pertenece el área.
     */
    public Piso getPiso() {
        return piso;
    }

    /**
     * Indica si el área tiene permiso para el uso de color.
     *
     * @return {@code true} si el área tiene permiso para usar color, {@code false} en caso contrario.
     */
    public boolean tienePermisoColor() {
        return permisoColor;
    }
}
