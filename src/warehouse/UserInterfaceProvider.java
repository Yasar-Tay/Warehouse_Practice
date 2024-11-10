package warehouse;

import java.util.List;
import java.util.Scanner;

public class UserInterfaceProvider {
    //color codes for console..
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";

    //Scanner object that will be initialised in Constructor.
    private final Scanner scanner;

    public UserInterfaceProvider() {
        scanner = new Scanner(System.in);
    }

    /* Main menu of our program.
     * According to the choice of user, it initiates the relevant private methods.
     * User inputs are sent to Validator class to check if they are valid or not.
     * It keeps rendering until user enters 'q'
     */
    public void welcomeScreen() {
        String userInput = "";
        do {
            System.out.println();
            String welcomeHeader = "Welcome to WareHouse Management Application";
            System.out.println("*".repeat(welcomeHeader.length()));
            System.out.println(welcomeHeader);
            System.out.println("*".repeat(welcomeHeader.length()));

            System.out.println(ANSI_CYAN + """
                    1 - List all products
                    2 - Add a new product
                    3 - Enter quantity of an existing product
                    4 - Exit quantity of an existing product
                    5 - Assign product on a particular shelf
                    6 - Delete a product
                    q - Exit program..
                    """ + ANSI_RESET);
            System.out.print("Please choose the action from the list: ");

            //User input is sent for validation to the relevant method of Validator class in order to prevent invalid entries.
            //Exceptions thrown by Validator methods.
            try {
                userInput = Validator.validateWelcomeScreenInput(scanner.next());
            } catch (RuntimeException e) {
                System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
                continue;
            }

            switch (userInput) {
                case "1":
                    listProducts(ProductManagement.getListOfProducts());
                    break;
                case "2":
                    addProduct();
                    listProducts(ProductManagement.getListOfProducts());
                    break;
                case "3":
                    enterQuantity();
                    listProducts(ProductManagement.getListOfProducts());
                    break;
                case "4":
                    exitQuantity();
                    listProducts(ProductManagement.getListOfProducts());
                    break;
                case "5":
                    assignShelf();
                    listProducts(ProductManagement.getListOfProducts());
                    break;
                case "6":
                    deleteProduct();
                    listProducts(ProductManagement.getListOfProducts());
                    break;
            }

        } while (!userInput.equalsIgnoreCase("q"));
    }

    /*
     * This method receives a product list and displays the products in that list in a pattern.
     */
    private void listProducts(List<Product> productList) {
        //Formatting pattern that will keep the list items and list header in line.
        String listPattern = "%-8s %-10s %-15s %-12s %-10s %-5s\n";
        String listHeader = String.format(listPattern, "ID", "Name", "Manufacturer", "Quantity", "Unit", "Shelf");

        System.out.println("-".repeat(listHeader.length()));
        System.out.print(listHeader);
        System.out.println("-".repeat(listHeader.length()));

        productList.forEach(t -> System.out.printf(listPattern, t.getId(), t.getName(), t.getManufacturer(), t.getQuantity(), t.getUnit(), t.getShelf()));
    }

    /*
     * This method renders the "ADD NEW PRODUCT" menu and prompts user to enter property values of the product that will be created.
     * After validations, it creates the product and adds to the inventory list by calling the relevant method of ProductManagement class.
     */
    private void addProduct() {
        scanner.nextLine(); //dummy nextLine() to consume enter press which was left from the previous menu.
        System.out.println(ANSI_CYAN + "--- ADD NEW PRODUCT ---" + ANSI_RESET);
        System.out.println("Please enter the information of the product you want to add.");
        String name;
        String manufacturer;
        String unit;

        //Exceptions thrown by Validator methods.
        try {
            System.out.print("[Required] Name: ");
            name = Validator.validateNameManufacturerUnit(scanner.nextLine());

            System.out.print("[Required] Manufacturer: ");
            manufacturer = Validator.validateNameManufacturerUnit(scanner.nextLine());

            System.out.print("[Required] Unit: ");
            unit = Validator.validateNameManufacturerUnit(scanner.nextLine());

        } catch (RuntimeException e){
            System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
            return;
        }


        System.out.print("[Optional] Quantity: ");
        Double quantity;

        //Exceptions thrown by Validator methods.
        try {
            quantity = Validator.validateQuantity(scanner.nextLine());
        } catch (RuntimeException e){
            System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
            return;
        }

        System.out.print("[Optional] Shelf: ");
        String shelf = Validator.validateShelf(scanner.nextLine());

        //After validations, the Product objects is created and added to the inventory list.
        ProductManagement.addProduct(name, manufacturer, unit, quantity, shelf);
    }

    /*
     * This method renders the "ENTER QUANTITY" menu and prompts user to enter ID and quantity amount that will be added.
     * After validations, it updates the quantity property of the product by calling the relevant method of ProductManagement class.
     */
    private void enterQuantity(){
        scanner.nextLine(); //dummy nextLine() to consume enter press which was left from the previous menu.
        System.out.println(ANSI_CYAN + "--- ENTER QUANTITY ---" + ANSI_RESET);
        System.out.println("Please enter the id of the product you want to update its quantity.");

        //Exceptions are thrown by Validator methods and findById method in ProductRepository class.
        try {
            System.out.print("ID: ");
            Long idInput = Validator.validateID(scanner.next());
            System.out.print("Entry Quantity: ");
            Double entryQuantity = Validator.validateQuantity(scanner.next());
            //Updating the quantity
            ProductManagement.enterQuantity(idInput, entryQuantity);

        } catch (RuntimeException e){
            System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
        }
    }

    /*
     * This method renders the "EXIT QUANTITY" menu and prompts user to enter ID and quantity amount that will be removed.
     * After validations, it updates the quantity property of the product by calling the relevant method of ProductManagement class.
     */
    private void exitQuantity(){
        scanner.nextLine(); //dummy nextLine() to consume enter press which was left from the previous menu.
        System.out.println(ANSI_CYAN + "--- EXIT QUANTITY ---" + ANSI_RESET);
        System.out.println("Please enter the id of the product you want to update its quantity.");

        //Exceptions are thrown by Validator methods and findById method in ProductRepository class.
        try {
            System.out.print("ID: ");
            Long idInput = Validator.validateID(scanner.next());
            System.out.print("Entry Quantity: ");
            Double entryQuantity = Validator.validateQuantity(scanner.next());
            // Updating the quantity of the product
            ProductManagement.exitQuantity(idInput, entryQuantity);

        } catch (RuntimeException e){
            System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
        }
    }

    /*
     * This method renders the "ASSIGN SHELF" menu and prompts user to enter ID of the product and shelf value that will be assigned.
     * After validations, it updates the shelf property of the product by calling the relevant method of ProductManagement class.
     */
    private void assignShelf(){
        scanner.nextLine(); //dummy nextLine() to consume enter press which was left from the previous menu.
        System.out.println(ANSI_CYAN + "--- ASSIGN SHELF ---" + ANSI_RESET);
        System.out.println("Please enter the id of the product you want to assign shelf.");

        //Exceptions are thrown by Validator methods and findById method in ProductRepository class.
        try {
            System.out.print("ID: ");
            Long idInput = Validator.validateID(scanner.next());
            System.out.print("Shelf: ");
            String shelf = Validator.validateNameManufacturerUnit(scanner.next());
            //Assigning the shelf value of the product
            ProductManagement.assignShelf(idInput, shelf);

        } catch (RuntimeException e){
            System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
        }
    }

    /*
     * This method renders the "DELETE PRODUCT" menu and prompts user to enter ID of the product that will be removed from the inventory list.
     * After validation, it removes the product by calling the relevant method of ProductManagement class.
     */
    private void deleteProduct(){
        scanner.nextLine(); //dummy nextLine() to consume enter press which was left from the previous menu.
        System.out.println(ANSI_CYAN + "--- DELETE PRODUCT ---" + ANSI_RESET);
        System.out.println("Please enter the id of the product you want to remove.");

        //Exceptions are thrown by Validator method and findByProductById method in ProductRepository class.
        try {
            System.out.print("ID: ");
            Long idInput = Validator.validateID(scanner.next());
            //Removing product from the inventory list
            ProductManagement.deleteProduct(idInput);

        } catch (RuntimeException e){
            System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
        }
    }
}
