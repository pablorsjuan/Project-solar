public class Electrodomestico {
    //atributos
    static int cantElects; //Es una variable de clase, se modifica cada vez
    //que entre un nuevo producto.
    private String nombre;
    private float vatios;
    private float kW; //vatios/1000
    private float kWh;
    private int horasUso; //No puede ser mayor a 24
    private int cantidadElec=1; //Si no se da un número y no se elimina, se entiende que solo tiene 1
    private int dias; //No puede ser mayor a 31
    private float costoKWH; //Depende de la empresa
    //Constructor
    public Electrodomestico(String nombre, float vatios, int horasUso, int cantidadElec) {
        this.nombre = nombre;
        this.vatios = vatios;
        this.horasUso = horasUso;
        this.cantidadElec=cantidadElec;
        kW=vatios/1000;
        kWh=kW*horasUso;
        cantElects++;
    }
    //Métodos
    //Get and set
    public static int getCantElects() {return cantElects;}
    public String getNombre() {return nombre;}
    public float getVatios() {return vatios;}
    public float getkW() {return kW;}
    public int getHorasUso() {return horasUso;}
    public int getDias() {return dias;}
    public float getCostoKWH() {return costoKWH;}
    public int getCantidadElec() {return cantidadElec;}
    public float getkWh() {return kWh;}
}