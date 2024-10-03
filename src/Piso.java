import java.util.List;
import java.util.ArrayList;
/**
 * La clase Piso representa un piso dentro de una empresa. Cada piso tiene un nombre,
 * una área específica y una lista de empleados que trabajan en él.
 *
 * @author Gabo, Pedro Yamil, Isaac (Los Hijos de Korhal)
 */

public class Piso {
    private String nombrePiso;
    private Area area;
    private List<Empleado> empleados;

    /**
     * Constructor para crear un nuevo piso.
     *
     * @param nombrePiso El nombre del piso.
     * @param area El área asociada al piso.
     */
    public Piso(String nombrePiso, Area area) {
        this.nombrePiso = nombrePiso;
        this.area = area;
        this.empleados = new ArrayList<>();
    }

    /**
     * Obtiene el nombre del piso.
     *
     * @return El nombre del piso.
     */
    public String getNombrePiso() {
        return nombrePiso;
    }

    /**
     * Obtiene el área asociada al piso.
     *
     * @return El área del piso.
     */
    public Area getArea() {
        return area;
    }

    /**
     * Agrega un empleado a la lista de empleados del piso.
     *
     * @param empleado El empleado que se agregará al piso.
     */
    public void addEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    /**
     * Devuelve la lista de empleados que trabajan en el piso.
     *
     * @return Una lista de empleados en el piso.
     */
    public List<Empleado> getEmpleados() {
        return empleados;
    }
}
