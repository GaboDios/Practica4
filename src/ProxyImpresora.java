import java.util.concurrent.BlockingQueue;

public class ProxyImpresora {
    private Impresora impresora;
    private BlockingQueue<SolicitudImpresion> colaImpresion;

    public ProxyImpresora(Impresora impresora, BlockingQueue<SolicitudImpresion> colaImpresion) {
        this.impresora = impresora;
        this.colaImpresion = colaImpresion;
    }

    public void imprimir(SolicitudImpresion solicitud) {
        try {
            colaImpresion.put(solicitud);  // Añadir solicitud a la cola
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private boolean verificarPermisos(Area areaSolicita, SolicitudImpresion solicitud) {
        return areaSolicita.tienePermisoColor() || solicitud.getTipoImpresion().equals("blanco y negro");
    }
}