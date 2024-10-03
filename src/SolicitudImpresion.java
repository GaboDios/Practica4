import java.util.Date;
/**
 * La clase SolicitudImpresion representa una solicitud de impresión realizada por un empleado.
 * Cada solicitud incluye información sobre el empleado, el tipo de impresión (color o blanco y negro),
 * el estado de la solicitud y la fecha en que se realizó la solicitud.
 *
 * @author Gabo, Pedro Yamil, Isaac (Los Hijos de Korhal)
 */
public class SolicitudImpresion {
    private Empleado empleado;
    private String tipoImpresion;
    private String estado;
    private Date fechaSolicitud;

    /**
     * Constructor que inicializa una nueva solicitud de impresión con el empleado que la realizó,
     * el tipo de impresión y establece el estado como "pendiente" y la fecha actual.
     *
     * @param empleado El empleado que realiza la solicitud.
     * @param tipoImpresion El tipo de impresión solicitado ("color" o "blanco y negro").
     */
    public SolicitudImpresion(Empleado empleado, String tipoImpresion) {
        this.empleado = empleado;
        this.tipoImpresion = tipoImpresion;
        this.estado = "pendiente";
        this.fechaSolicitud = new Date();
    }

    /**
     * Devuelve el empleado que realizó la solicitud.
     *
     * @return El empleado que realizó la solicitud de impresión.
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * Devuelve el tipo de impresión solicitada (color o blanco y negro).
     *
     * @return El tipo de impresión solicitada.
     */
    public String getTipoImpresion() {
        return tipoImpresion;
    }

    /**
     * Devuelve el estado actual de la solicitud de impresión.
     *
     * @return El estado de la solicitud de impresión.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece un nuevo estado para la solicitud de impresión (por ejemplo, "completada").
     *
     * @param estado El nuevo estado de la solicitud.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Devuelve la fecha en que se realizó la solicitud de impresión.
     *
     * @return La fecha de la solicitud de impresión.
     */
    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }
}
