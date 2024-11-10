package warehouse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductRepository {
    private final List<Product> inventory;

    public ProductRepository() {
        //Initialising the product list
        inventory = new ArrayList<>(Arrays.asList(
                new Product("Floor", "Hekimoglu", "Sack", 50.0),
                new Product("Sugar", "BorSeker", "Sack", 100.0, "3A"),
                new Product("Sugar", "Kristal", "Sack", 100.0,"3B"),
                new Product("Oil", "Ona", "Kg"),
                new Product("Oil", "Yudum", "Kg"),
                new Product("Pasta", "Barilla", "Pack", 200.0, "4C"),
                new Product("Pasta", "Filiz", "Pack", 100.0)
        ));
    }

    public List<Product> getInventory() {
        return inventory;
    }

    public void addProduct(Product product){
        inventory.add(product);
    }

    /*
     * This method searches the inventory with the given ID to find the relevant product.
     * If the method finds the product that has been looked for, it returns it.
     * If it can not find it, it throws an exception which is handled in UserInterfaceProvider class.
     * Since this is a helper method that is used by other methods in this class, it is private.
     */
    private Product findProductById(long id){

        Product product = null;

        for (Product pr: inventory) {
            if (pr.getId() == id){
                product = pr;
            }
        }
        //If the product with the given ID can not be found, an exception is thrown which is handled in UserInterfaceProvider Class.
        if (product == null)
            throw new RuntimeException("Product is not found! Please check the ID and try again");
        else
            return product;
    }

    /*
     * This method increases the quantity of an existing product.
     * It finds the relevant product by using the first "ID" parameter.
     * It uses findProductById() method to find the product which  throws an exception if it can not find the product.
     * Second parameter of this method is the increase amount which will be added to the quantity amount of the product that was found.
     */
    public void enterQuantity(Long id, Double quantityAdding){
        Product product = findProductById(id);
        product.setQuantity(product.getQuantity() + quantityAdding);
    }


    /*
     * This method decreases the quantity of an existing product.
     * It finds the relevant product by using the first "ID" parameter.
     * It uses findProductById() method to find the product which throws an exception if it can not find the product.
     * Second parameter of this method is the decrease amount which will be removed from the quantity amount of the product that was found.
     */
    public void exitQuantity(Long id, Double quantityRemoving){

        Product product = findProductById(id);

        //if the quantity will decrease below 0, it throws exception which is also handled in UserInterfaceProvider class
        if (product.getQuantity() - quantityRemoving < 0){
            throw new RuntimeException(String.format("Insufficient quantity! You can exit %s %s maximum.", product.getQuantity(), product.getUnit()));
        }

        product.setQuantity(product.getQuantity() - quantityRemoving);
    }


    /*
     * This method allows user to assign the value for 'shelf' property of the product with the given ID
     * It uses findProductById() method to find the product which throws an exception if it can not find the product.
     */
    public void putOnShelf(Long id, String shelf){
        Product product = findProductById(id);
        product.setShelf(shelf);
    }


    /*
     * This method deletes the product with the given ID from the inventory list.
     * It uses findProductById() method to find the product which throws an exception if it can not find the product.
     */
    public void deleteProduct(Long id){
        Product product = findProductById(id);
        inventory.remove(product);
    }


}
