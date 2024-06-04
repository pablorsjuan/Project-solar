import java.util.ArrayList;

public class Electrodomestico {
    //atributos
    static int cantElects; //Es una variable de clase, se modifica cada vez
    static ArrayList<Electrodomestico> listaElecs = new ArrayList<>();
    //que entre un nuevo producto.
    private String nombre;
    private float vatios;
    private float kW; //vatios/1000
    private float kWh;
    private int horasUso = 0; //No puede ser mayor a 24
    private int minutosUso = 0; //si se llena hora, no es necesario minutos, y si se llena minutos, horas=0;
    private int cantidadElec = 1; //Si no se da un número y no se elimina, se entiende que solo tiene 1
    //Constructor
    public Electrodomestico(String nombre, float vatios, int horasUso, int minutosUso, int cantidadElec) {
        this.nombre = nombre;
        this.vatios = vatios;
        this.horasUso = horasUso;
        this.minutosUso = minutosUso;
        this.cantidadElec = cantidadElec;
        kW = vatios / 1000;
        if (horasUso == 0) {
            kWh = kW * minutosUso * cantidadElec;
        } else {
            kWh = kW * horasUso + minutosUso * cantidadElec;
        }
        cantElects++;
    }
    //Métodos
    //Get and set
    public static int getCantElects() {
        return cantElects;
    }
}