import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ctOS {

    public static void main(String[] args) {
        System.out.println("Iniciando main...");
        // Crear cola de impresión compartida
        BlockingQueue<SolicitudImpresion> colaImpresion = new LinkedBlockingQueue<>();

        System.out.println("Iniciando impresora...");
        // Obtener instancia única de la impresora mediante Singleton
        Impresora impresora = Impresora.getInstancia();
        System.out.println("Iniciando proxy...");
        ProxyImpresora proxy = new ProxyImpresora(impresora, colaImpresion);

        // Crear log
        System.out.println("Iniciando log...");
        Log log = new Log();

        // Crear servidor (Singleton) con la cola de impresión
        System.out.println("Iniciando servidor...");
        Servidor servidor = Servidor.getInstancia(colaImpresion);

        // Crear los pisos y áreas
        Piso piso1 = new Piso("Piso 1", new Area("Atención a Clientes", null, false));
        Piso piso2 = new Piso("Piso 2", new Area("Recursos Humanos", null, false));
        Piso piso3 = new Piso("Piso 3", new Area("Contabilidad", null, false));
        Piso piso4 = new Piso("Piso 4", new Area("Desarrollo y Mercadotecnia", null, false));
        Piso piso5 = new Piso("Piso 5", new Area("Dirección", null, false));

        // Agregar pisos al servidor
        servidor.agregarPiso(piso1);
        servidor.agregarPiso(piso2);
        servidor.agregarPiso(piso3);
        servidor.agregarPiso(piso4);
        servidor.agregarPiso(piso5);

        // Crear empleados y asignarlos a los pisos
        agregarEmpleados(piso1, "Atención a Clientes", false);
        agregarEmpleados(piso2, "Recursos Humanos", false);
        agregarEmpleados(piso3, "Contabilidad", false);
        agregarEmpleados(piso4, "Desarrollo", false);
        agregarEmpleados(piso4, "Mercadotecnia", true);
        agregarEmpleados(piso5, "Dirección", false);

        // Hilo para solicitudes de impresión
        Thread solicitudesThread = new Thread(() -> {
            for (Piso piso : servidor.getPisos()) {
                for (Empleado empleado : piso.getEmpleados()) {
                    SolicitudImpresion solicitudColor = new SolicitudImpresion(empleado, "color");
                    proxy.imprimir(solicitudColor);  // Pasa la solicitud a la cola
                    log.registrarEvento("Solicitud de impresión color agregada por " + empleado.getNombre() + " en " + piso.getNombrePiso());

                    SolicitudImpresion solicitudBN = new SolicitudImpresion(empleado, "blanco y negro");
                    proxy.imprimir(solicitudBN);  // Pasa la solicitud a la cola
                    log.registrarEvento("Solicitud de impresión blanco y negro agregada por " + empleado.getNombre() + " en " + piso.getNombrePiso());
                }
            }
        });

        // Hilo para procesar los trabajos de impresión
        Thread impresoraThread = new Thread(() -> {
            try {
                while (impresora.isRunning()) {  // Utilizamos isRunning para controlar el ciclo
                    SolicitudImpresion trabajo = colaImpresion.take(); // Toma de la cola
                    impresora.procesarTrabajo(trabajo);
                    log.registrarEvento("Trabajo de impresión procesado para " + trabajo.getEmpleado().getNombre());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Iniciar ambos hilos
        solicitudesThread.start();
        impresoraThread.start();

        try {
            // Simulamos la finalización después de cierto tiempo
            Thread.sleep(10000);  // Esperar 10 segundos antes de detener
            impresora.detener();  // Detenemos el ciclo del hilo de la impresora
            solicitudesThread.interrupt();  // Interrumpimos el hilo de solicitudes
            impresoraThread.interrupt();  // Interrumpimos el hilo de impresora

            // Esperar a que los hilos terminen
            solicitudesThread.join();
            impresoraThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Mostrar el historial de log al final
        System.out.println("Historial del log de la impresora:");
        for (String evento : log.getHistorial()) {
            System.out.println(evento);
        }
    }

    // Método auxiliar para agregar empleados a un piso
    private static void agregarEmpleados(Piso piso, String area, boolean permisoColor) {
        Empleado empleado1 = new Empleado("Empleado1 del " + area, generarRFC(), piso.getArea(), piso);
        Empleado empleado2 = new Empleado("Empleado2 del " + area, generarRFC(), piso.getArea(), piso);

        piso.addEmpleado(empleado1);
        piso.addEmpleado(empleado2);
    }

    // Método auxiliar para generar un RFC aleatorio
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
