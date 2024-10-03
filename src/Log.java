import java.util.List;
import java.util.ArrayList;
/**
 * La clase Log se encarga de registrar y almacenar los eventos generados
 * durante la operación de impresión. Los eventos se registran en un historial
 * que puede ser consultado posteriormente.
 *
 * @author Gabo, Pedro Yamil, Isaac (Los Hijos de Korhal)
 */
public class Log {
    private List<String> historial;

    /**
     * Constructor que inicializa una lista vacía para almacenar el historial de eventos.
     */
    public Log() {
        this.historial = new ArrayList<>();
    }

    /**
     * Registra un nuevo evento en el historial.
     *
     * @param evento El evento que se registrará.
     */
    public void registrarEvento(String evento) {
        historial.add(evento);
    }

    /**
     * Devuelve el historial de eventos registrados.
     *
     * @return Una lista con el historial de eventos.
     */
    public List<String> getHistorial() {
        return historial;
    }
}
