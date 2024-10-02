import java.util.Date;
public class SolicitudImpresion {
    private Empleado empleado;
    private String tipoImpresion;
    private String estado;
    private Date fechaSolicitud;

    public SolicitudImpresion(Empleado empleado, String tipoImpresion) {
        this.empleado = empleado;
        this.tipoImpresion = tipoImpresion;
        this.estado = "pendiente";
        this.fechaSolicitud = new Date();
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public String getTipoImpresion() {
        return tipoImpresion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
