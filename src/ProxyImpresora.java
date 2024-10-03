import java.util.concurrent.BlockingQueue;
/**
 * La clase ProxyImpresora actúa como un intermediario entre la impresora y los empleados.
 * Gestiona las solicitudes de impresión, verificando los permisos del área del empleado
 * y agregando las solicitudes a la cola de impresión si se permiten.
 *
 * @author Gabo, Pedro Yamil, Isaac (Los Hijos de Korhal)
 */
public class ProxyImpresora {
    private Impresora impresora;
    private BlockingQueue<SolicitudImpresion> colaImpresion;

    /**
     * Constructor para inicializar el proxy con una impresora y una cola de impresión.
     *
     * @param impresora La impresora real que procesará las solicitudes.
     * @param colaImpresion La cola donde se almacenarán las solicitudes de impresión.
     */
    public ProxyImpresora(Impresora impresora, BlockingQueue<SolicitudImpresion> colaImpresion) {
        this.impresora = impresora;
        this.colaImpresion = colaImpresion;
    }

    /**
     * Método para manejar la solicitud de impresión. Verifica si el área tiene permiso para
     * imprimir en color antes de agregar la solicitud a la cola de impresión.
     *
     * @param solicitud La solicitud de impresión a procesar.
     * @return {@code true} si la solicitud fue aceptada y agregada a la cola, {@code false} si fue denegada.
     */
    public boolean imprimir(SolicitudImpresion solicitud) {
        // Verificar si el área tiene permisos usando el método verificarPermisos
        if (verificarPermisos(solicitud.getEmpleado().getAreaTrabajo(), solicitud)) {
            try {
                // Agregar la solicitud a la cola de impresión
                colaImpresion.put(solicitud);
                return true;  // Solicitud aceptada
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;  // Error al agregar a la cola
            }
        } else {
            return false;  // Permisos denegados
        }
    }

    /**
     * Verifica si el área del empleado tiene permiso para imprimir en color.
     * Las impresiones en blanco y negro están permitidas sin restricciones.
     *
     * @param areaSolicita El área desde donde se hace la solicitud de impresión.
     * @param solicitud La solicitud de impresión.
     * @return {@code true} si el área tiene permiso para imprimir en color o si es en blanco y negro.
     */
    private boolean verificarPermisos(Area areaSolicita, SolicitudImpresion solicitud) {
        return areaSolicita.tienePermisoColor() || solicitud.getTipoImpresion().equals("blanco y negro");
    }
}
