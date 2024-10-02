import java.util.List;
import java.util.ArrayList;

public class ctOS {

    public static void main(String[] args) {

        // Crear impresora y proxy
        Impresora impresora = new Impresora();
        ProxyImpresora proxy = new ProxyImpresora(impresora);

        // Crear servidor (Singleton)
        Servidor servidor = Servidor.getInstancia();

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

        // Simular solicitudes de impresión
        for (Piso piso : servidor.getPisos()) {
            for (Empleado empleado : piso.getEmpleados()) {
                SolicitudImpresion solicitudColor = new SolicitudImpresion(empleado, "color");
                SolicitudImpresion solicitudBN = new SolicitudImpresion(empleado, "blanco y negro");

                // Mandar a imprimir
                System.out.println("Empleado: " + empleado.getNombre() + " solicita impresión a color.");
                proxy.imprimir(solicitudColor);

                System.out.println("Empleado: " + empleado.getNombre() + " solicita impresión en blanco y negro.");
                proxy.imprimir(solicitudBN);
            }
        }
    }

    // Método auxiliar para agregar empleados a un piso
    private static void agregarEmpleados(Piso piso, String area, boolean permisoColor) {
        Empleado empleado1 = new Empleado("Empleado1 del " + area, generarRFC(), piso.getArea(), piso, permisoColor);
        Empleado empleado2 = new Empleado("Empleado2 del " + area, generarRFC(), piso.getArea(), piso, permisoColor);

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
