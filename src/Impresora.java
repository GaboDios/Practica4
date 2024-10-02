import java.util.Queue;
import java.util.LinkedList;
public class Impresora {
    private String estado;
    private Queue<SolicitudImpresion> trabajosPendientes;
    private boolean capacidadColor;

    public Impresora() {
        this.estado = "libre";
        this.trabajosPendientes = new LinkedList<>();
        this.capacidadColor = true; // Siempre tiene capacidad de imprimir a color.
    }

    public String getEstado() {
        return estado;
    }

    public void agregarTrabajo(SolicitudImpresion solicitud) {
        trabajosPendientes.offer(solicitud);
        estado = "ocupada";
    }

    public SolicitudImpresion procesarTrabajo() {
        if (!trabajosPendientes.isEmpty()) {
            return trabajosPendientes.poll();
        }
        estado = "libre";
        return null;
    }
}
