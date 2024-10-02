public class ProxyImpresora {
    private Impresora impresoraReal;

    public ProxyImpresora(Impresora impresoraReal) {
        this.impresoraReal = impresoraReal;
    }

    public void imprimir(SolicitudImpresion solicitud) {
        if (verificarPermisos(solicitud.getEmpleado(), solicitud)) {
            impresoraReal.agregarTrabajo(solicitud);
            System.out.println("Solicitud de impresión aceptada.");
        } else {
            System.out.println("Permiso denegado para imprimir a color.");
        }
    }

    private boolean verificarPermisos(Empleado empleado, SolicitudImpresion solicitud) {
        return empleado.tienePermisoColor() || solicitud.getTipoImpresion().equals("blanco y negro");
    }
}