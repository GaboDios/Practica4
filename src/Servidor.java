import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class Servidor {
    private static Servidor instancia;
    private List<Piso> pisos;
    private Impresora impresora;
    private BlockingQueue<SolicitudImpresion> colaImpresion;  // Cola de trabajos de impresión

    // Constructor modificado para aceptar la cola de impresión
    private Servidor(BlockingQueue<SolicitudImpresion> colaImpresion) {
        this.pisos = new ArrayList<>();
        this.impresora = Impresora.getInstancia();  // Usar el patrón Singleton para obtener la impresora
        this.colaImpresion = colaImpresion;  // Asignar la cola externa
    }

    // Método modificado para obtener la instancia con cola de impresión
    public static synchronized Servidor getInstancia(BlockingQueue<SolicitudImpresion> colaImpresion) {
        if (instancia == null) {
            instancia = new Servidor(colaImpresion);
        }
        return instancia;
    }

    public void agregarPiso(Piso piso) {
        pisos.add(piso);
    }

    public List<Piso> getPisos() {
        return pisos;
    }

    // Método que procesa la cola externa de solicitudes de impresión
    public void procesarCola() {
        try {
            while (true) {
                SolicitudImpresion solicitud = colaImpresion.take();  // Bloquea hasta que haya una solicitud disponible
                impresora.procesarTrabajo(solicitud);  // Pasa la solicitud a la impresora
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
