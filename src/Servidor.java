import java.util.List;
import java.util.ArrayList;
public class Servidor {
    private static Servidor instancia;
    private List<Piso> pisos;
    private Impresora impresora;

    private Servidor() {
        this.pisos = new ArrayList<>();
        this.impresora = new Impresora();
    }

    public static synchronized Servidor getInstancia() {
        if (instancia == null) {
            instancia = new Servidor();
        }
        return instancia;
    }

    public void agregarPiso(Piso piso) {
        pisos.add(piso);
    }

    public List<Piso> getPisos() {
        return pisos;
    }

    public void procesarCola() {
        while (!impresora.getEstado().equals("libre")) {
            SolicitudImpresion solicitud = impresora.procesarTrabajo();
            if (solicitud != null) {
                System.out.println("Procesando trabajo de: " + solicitud.getEmpleado().getNombre());
            }
        }
    }
}
