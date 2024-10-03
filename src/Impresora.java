public class Impresora {
    private static Impresora instancia;  // Instancia única de la clase
    private String estado;
    private boolean capacidadColor;
    private volatile boolean running = true;

    // Constructor privado para evitar creación de objetos directamente
    private Impresora() {
        this.estado = "libre";
        this.capacidadColor = true; // Siempre tiene capacidad de imprimir a color.
    }

    // Método estático para obtener la única instancia de la clase
    public static synchronized Impresora getInstancia() {
        if (instancia == null) {
            instancia = new Impresora();
        }
        return instancia;
    }

    public String getEstado() {
        return estado;
    }

    public void procesarTrabajo(SolicitudImpresion solicitud) {
        if (solicitud != null) {
            estado = "ocupada";

            try {
                Thread.sleep(2000); // Simulación de procesamiento
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("Procesando trabajo de impresión: " + solicitud.getTipoImpresion() +
                    " para " + solicitud.getEmpleado().getNombre());

            estado = "libre";
            System.out.println("Trabajo de impresión completado para: " + solicitud.getEmpleado().getNombre());
        }
    }

    // Método para verificar si la impresora está corriendo
    public boolean isRunning() {
        return running;
    }

    // Método para detener la impresora
    public void detener() {
        running = false;
    }
}
