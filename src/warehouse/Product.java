package warehouse;

public class Product {
    //Mandatory properties
    private final String name;
    private final String manufacturer;
    private final String unit;
    private final Long id;
    //static counter to assign automatic ID values to objects.
    private static long idCounter = 100;
    //Optional properties with default values.
    private Double quantity = 0.0;
    private String shelf = "--";

    //Parameterised Constructor for mandatory properties
    public Product (String name, String manufacturer, String unit) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.unit = unit;
        id = ++idCounter;                   //Assigning the id value
    }

    //Overloaded constructor in case of [quantity] property is given a value
    public Product (String name, String manufacturer, String unit, Double quantity) {
       this(name, manufacturer, unit);
       this.quantity = quantity;
    }

    //Overloaded constructor in case of [shelf] property is given a value
    public Product (String name, String manufacturer, String unit, String shelf) {
        this(name, manufacturer, unit);
        this.shelf = shelf;
    }

    //Overloaded constructor in case of [quantity] and [shelf] properties are given a value
    public Product (String name, String manufacturer, String unit, Double quantity, String shelf) {
       this(name, manufacturer, unit);
       this.quantity = quantity;
       this.shelf = shelf;
    }

    //Getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public String getShelf() {
        return shelf;
    }

    //Setter methods for optional properties
    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

}
