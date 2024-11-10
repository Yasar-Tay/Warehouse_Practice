package warehouse;

import java.util.List;
/**
 *
 * */
public class ProductManagement {
    private static final ProductRepository repository;

    /** static block to initialize repository */
    static {
        repository = new ProductRepository();
    }

    /** Method to get whole product list from repository which will be used to display all products in Runner class */
    public static List<Product> getListOfProducts(){
        return repository.getInventory();
    }

    //If user enters a value for the shelf and quantity fields, or not.
    public static void addProduct(String name, String manufacturer, String unit, Double quantity, String shelf){

        if (shelf == null && quantity == null) {
            repository.addProduct(new Product(name, manufacturer, unit));
        } else if (quantity != null && shelf == null) {
            repository.addProduct(new Product(name, manufacturer, unit, quantity));
        } else if (quantity == null) {
            repository.addProduct(new Product(name, manufacturer, unit, shelf));
        } else {
            repository.addProduct(new Product(name, manufacturer, unit, quantity, shelf));
        }
    }

    public static void enterQuantity(Long id, Double quantity){
        repository.enterQuantity(id, quantity);
    }

    public static void exitQuantity(Long id, Double quantity){
        repository.exitQuantity(id, quantity);
    }

    public static void assignShelf(Long id, String shelf){
        repository.putOnShelf(id, shelf);
    }

    public static void deleteProduct(Long id) {
        repository.deleteProduct(id);
    }
}
