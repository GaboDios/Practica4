/**
 * La clase Impresora representa una impresora compartida en una empresa.
 * La clase implementa el patrón Singleton para asegurar que solo exista una instancia de la impresora.
 * Gestiona el procesamiento de trabajos de impresión y mantiene un estado de disponibilidad.
 *
 * @author Gabo, Pedro Yamil, Isaac (Los Hijos de Korhal)
 */
public class Impresora {
    private static Impresora instancia;
    private String estado;
    private boolean capacidadColor;
    private volatile boolean running = true;

    /**
     * Constructor privado para implementar el patrón Singleton.
     * La impresora siempre tiene capacidad de imprimir a color y se inicializa en estado "libre".
     */
    private Impresora() {
        this.estado = "libre";
        this.capacidadColor = true; // Siempre tiene capacidad de imprimir a color.
    }

    /**
     * Devuelve la única instancia de la impresora. Si no existe, la crea.
     * Este método es sincronizado para garantizar la seguridad en entornos multihilo.
     *
     * @return La instancia única de la impresora.
     */
    public static synchronized Impresora getInstancia() {
        if (instancia == null) {
            instancia = new Impresora();
        }
        return instancia;
    }

    /**
     * Obtiene el estado actual de la impresora.
     *
     * @return El estado de la impresora ("libre" o "ocupada").
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Procesa una solicitud de impresión. Cambia el estado de la impresora a "ocupada" durante el proceso,
     * simula el tiempo de impresión y luego regresa a "libre".
     *
     * @param solicitud La solicitud de impresión a procesar.
     */
    public void procesarTrabajo(SolicitudImpresion solicitud) {
        if (solicitud != null) {
            estado = "ocupada";

            try {
                // Simula el tiempo necesario para procesar la solicitud de impresión
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            // Muestra información del trabajo procesado
            System.out.println("Procesando trabajo de impresión: " + solicitud.getTipoImpresion() +
                    " para " + solicitud.getEmpleado().getNombre());

            estado = "libre"; // Vuelve a estar libre después del trabajo
            System.out.println("Trabajo de impresión completado para: " + solicitud.getEmpleado().getNombre());
        }
    }

    /**
     * Indica si la impresora está en funcionamiento.
     *
     * @return {@code true} si la impresora está en funcionamiento, {@code false} si está detenida.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Detiene la impresora, cambiando el estado de funcionamiento a {@code false}.
     */
    public void detener() {
        running = false;
    }
}
