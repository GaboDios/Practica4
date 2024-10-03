import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * La clase ctOS simula la gestión de solicitudes de impresión en una empresa
 * con múltiples pisos y áreas. El sistema distribuye trabajos de impresión y registra eventos en un log.
 * Utiliza un patrón Proxy para gestionar el acceso a una impresora compartida.
 *
 * @author Gabo, Pedro Yamil, Isaac (Los Hijos de Korhal)
 */
public class ctOS {
    /**
     * Método principal que configura el sistema de impresión, agregando pisos, empleados, y
     * ejecutando las solicitudes de impresión. Registra eventos a través de un log y procesa
     * los trabajos en una cola de impresión.
     *
     * @param args Argumentos de la línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        // Inicializa la cola de impresión y el proxy de la impresora
        BlockingQueue<SolicitudImpresion> colaImpresion = new LinkedBlockingQueue<>();
        Impresora impresora = Impresora.getInstancia();
        ProxyImpresora proxy = new ProxyImpresora(impresora, colaImpresion);
        Log log = new Log();
        Servidor servidor = Servidor.getInstancia(colaImpresion);

        Piso piso1 = new Piso("Piso 1", new Area("Atención a Clientes", null, false));
        Piso piso2 = new Piso("Piso 2", new Area("Recursos Humanos", null, false));
        Piso piso3 = new Piso("Piso 3", new Area("Contabilidad", null, false));
        Piso piso4 = new Piso("Piso 4", new Area("Desarrollo y Mercadotecnia", null, true));
        Piso piso5 = new Piso("Piso 5", new Area("Dirección", null, false));

        servidor.agregarPiso(piso1);
        servidor.agregarPiso(piso2);
        servidor.agregarPiso(piso3);
        servidor.agregarPiso(piso4);
        servidor.agregarPiso(piso5);

        agregarEmpleados(piso1, "Atención a Clientes", false);
        agregarEmpleados(piso2, "Recursos Humanos", false);
        agregarEmpleados(piso3, "Contabilidad", false);
        agregarEmpleados(piso4, "Desarrollo y Mercadotecnia", false);
        agregarEmpleados(piso5, "Dirección", false);

        // Hilo que simula la creación de solicitudes de impresión por empleados
        Thread solicitudesThread = new Thread(() -> {
            for (Piso piso : servidor.getPisos()) {
                for (Empleado empleado : piso.getEmpleados()) {
                    SolicitudImpresion solicitudColor = new SolicitudImpresion(empleado, "color");
                    if (proxy.imprimir(solicitudColor)) {
                        log.registrarEvento("[" + Thread.currentThread().getName() + "]Solicitud de impresión color agregada por " + empleado.getNombre() + " en " + piso.getNombrePiso());
                    } else {
                        log.registrarEvento("[" + Thread.currentThread().getName() + "]Solicitud de impresión color DENEGADA para " + empleado.getNombre() + " en " + piso.getNombrePiso());
                    }

                    SolicitudImpresion solicitudBN = new SolicitudImpresion(empleado, "blanco y negro");
                    proxy.imprimir(solicitudBN);
                    log.registrarEvento("[" + Thread.currentThread().getName() + "]Solicitud de impresión blanco y negro agregada por " + empleado.getNombre() + " en " + piso.getNombrePiso());

                    // Añadir un retraso simulado para que no se generen todas las solicitudes al mismo tiempo
                    try {
                        Thread.sleep(500);  // Retraso de 0.5 segundos entre solicitudes
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        solicitudesThread.start();

        // Hilo que simula el procesamiento de trabajos en la impresora
        Thread impresoraThread = new Thread(() -> {
            try {
                while (impresora.isRunning()) {
                    SolicitudImpresion trabajo = colaImpresion.take();
                    impresora.procesarTrabajo(trabajo);
                    log.registrarEvento("[" + Thread.currentThread().getName() + "]Trabajo de impresión procesado para " + trabajo.getEmpleado().getNombre());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        impresoraThread.start();

        // Espera a que los hilos finalicen tras 60 segundos
        try {
            Thread.sleep(60000);
            impresora.detener();
            solicitudesThread.interrupt();
            impresoraThread.interrupt();

            solicitudesThread.join();
            impresoraThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Muestra el historial de eventos en el log
        System.out.println("Historial del log de la impresora:");
        System.out.println("##################################");
        for (String evento : log.getHistorial()) {
            System.out.println(evento);
        }
        System.out.println("##################################");
    }

    /**
     * Método auxiliar que agrega empleados a un piso específico.
     *
     * @param piso El piso al que se agregarán los empleados.
     * @param area El nombre del área donde trabajan los empleados.
     * @param permisoColor Indica si los empleados tienen permiso para imprimir en color.
     */
    private static void agregarEmpleados(Piso piso, String area, boolean permisoColor) {
        Empleado empleado1 = new Empleado("Empleado1 del " + area, generarRFC(), piso.getArea(), piso);
        Empleado empleado2 = new Empleado("Empleado2 del " + area, generarRFC(), piso.getArea(), piso);

        piso.addEmpleado(empleado1);
        piso.addEmpleado(empleado2);
    }

    /**
     * Genera un RFC aleatorio para los empleados, que consta de letras y números.
     *
     * @return Una cadena con un RFC aleatorio de 13 caracteres.
     */
    private static String generarRFC() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder rfc = new StringBuilder();
        for (int i = 0; i < 13; i++) {
            int indice = (int) (Math.random() * caracteres.length());
            rfc.append(caracteres.charAt(indice));
        }
        return rfc.toString();
    }
}
