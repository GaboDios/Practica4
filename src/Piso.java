import java.util.List;
import java.util.ArrayList;
public class Piso {
    private String nombrePiso;
    private Area area;
    private List<Empleado> empleados;

    public Piso(String nombrePiso, Area area) {
        this.nombrePiso = nombrePiso;
        this.area = area;
        this.empleados = new ArrayList<>();
    }

    public String getNombrePiso() {
        return nombrePiso;
    }

    public Area getArea() {
        return area;
    }

    public void addEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }
}
