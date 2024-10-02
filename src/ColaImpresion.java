import java.util.Queue;
import java.util.LinkedList;
public class ColaImpresion {
    private Queue<SolicitudImpresion> solicitudes;

    public ColaImpresion() {
        this.solicitudes = new LinkedList<>();
    }

    public void agregarSolicitud(SolicitudImpresion solicitud) {
        solicitudes.offer(solicitud);
    }

    public SolicitudImpresion procesarSiguiente() {
        return solicitudes.poll();
    }
}
