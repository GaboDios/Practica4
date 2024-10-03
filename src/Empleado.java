/**
 * La clase Empleado representa a un empleado de una empresa, con su información
 * personal como nombre y RFC, así como su área de trabajo y el piso en el que trabaja.
 *
 * @author Gabo, Pedro Yamil, Isaac (Los Hijos de Korhal)
 */
public class Empleado {
    private String nombre;
    private String rfc;
    private Area areaTrabajo;
    private Piso pisoTrabajo;

    /**
     * Constructor para crear un nuevo objeto Empleado.
     *
     * @param nombre El nombre del empleado.
     * @param rfc El RFC del empleado.
     * @param areaTrabajo El área en la que trabaja el empleado.
     * @param pisoTrabajo El piso en el que trabaja el empleado.
     */
    public Empleado(String nombre, String rfc, Area areaTrabajo, Piso pisoTrabajo) {
        this.nombre = nombre;
        this.rfc = rfc;
        this.areaTrabajo = areaTrabajo;
        this.pisoTrabajo = pisoTrabajo;
    }

    /**
     * Obtiene el nombre del empleado.
     *
     * @return El nombre del empleado.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el RFC del empleado.
     *
     * @return El RFC del empleado.
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Obtiene el área de trabajo del empleado.
     *
     * @return El área donde trabaja el empleado.
     */
    public Area getAreaTrabajo() {
        return areaTrabajo;
    }

    /**
     * Obtiene el piso de trabajo del empleado.
     *
     * @return El piso donde trabaja el empleado.
     */
    public Piso getPisoTrabajo() {
        return pisoTrabajo;
    }

}
