import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
/**
 * La clase Servidor gestiona las solicitudes de impresión y organiza los pisos de la empresa.
 * Implementa el patrón Singleton para asegurar que solo exista una instancia. También controla la
 * cola de solicitudes de impresión y delega el procesamiento a la impresora.
 *
 * @author Gabo, Pedro Yamil, Isaac (Los Hijos de Korhal)
 */
public class Servidor {
    private static Servidor instancia;
    private List<Piso> pisos;
    private Impresora impresora;
    private BlockingQueue<SolicitudImpresion> colaImpresion;

    /**
     * Constructor privado para el patrón Singleton. Inicializa los pisos y la impresora.
     *
     * @param colaImpresion La cola de solicitudes de impresión que gestionará el servidor.
     */

    private Servidor(BlockingQueue<SolicitudImpresion> colaImpresion) {
        this.pisos = new ArrayList<>();
        this.impresora = Impresora.getInstancia();
        this.colaImpresion = colaImpresion;
    }

    /**
     * Devuelve la instancia única del servidor. Si no existe, la crea.
     * Este método es sincronizado para garantizar seguridad en entornos multihilo.
     *
     * @param colaImpresion La cola de solicitudes de impresión.
     * @return La instancia única del servidor.
     */
    public static synchronized Servidor getInstancia(BlockingQueue<SolicitudImpresion> colaImpresion) {
        if (instancia == null) {
            instancia = new Servidor(colaImpresion);
        }
        return instancia;
    }

    /**
     * Agrega un nuevo piso a la lista de pisos gestionados por el servidor.
     *
     * @param piso El piso que se agregará.
     */
    public void agregarPiso(Piso piso) {
        pisos.add(piso);
    }

    /**
     * Obtiene la lista de pisos administrados por el servidor.
     *
     * @return La lista de pisos.
     */
    public List<Piso> getPisos() {
        return pisos;
    }

    /**
     * Procesa las solicitudes de impresión que se encuentran en la cola de impresión.
     * Extrae una solicitud a la vez de la cola y la envía a la impresora para ser procesada.
     */
    public void procesarCola() {
        try {
            while (true) {
                SolicitudImpresion solicitud = colaImpresion.take();
                impresora.procesarTrabajo(solicitud);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
