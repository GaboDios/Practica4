import java.util.List;
import java.util.ArrayList;
public class Log {
    private List<String> historial;

    public Log() {
        this.historial = new ArrayList<>();
    }

    public void registrarEvento(String evento) {
        historial.add(evento);
        System.out.println("Evento registrado: " + evento);
    }

    public List<String> getHistorial() {
        return historial;
    }
}
