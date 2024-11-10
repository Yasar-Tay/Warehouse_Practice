package warehouse;

/**
 * This class manages the validation checks for user inputs that was sent by UserInterfaceProvider class.
 * Every validate method returns the input as they are if the input is valid.
 * If the input is invalid, methods throw exception which are handled in UserInterfaceProvider class.
 * */
public class Validator {

    /** Validating if the user enters the option numbers as expected or not. */
    public static String validateWelcomeScreenInput(String input) {
        switch (input) {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "q":
            case "Q":
                return input;
            default:
                throw new RuntimeException("Invalid Input. Please try again.");
        }
    }

    /** Validation for the fields required for new Product object creation. */
    public static String validateNameManufacturerUnit(String input) {
        if (input.isBlank() || input.isEmpty())
            throw new RuntimeException("Required fields can not be empty.. Please start over.");
        else
            return input;
    }

    /** Validation for quantity value. This method checks for circumstances that if non-numeric data is entered or if it is left empty or blank. */
    public static Double validateQuantity(String quantity) {
        //checking If user enters non-numeric data.
        boolean nonNumericDataEntered = quantity.trim().replaceAll("[0-9.]", "").length() != 0;

        //checking If user left it empty.
        if (quantity.isEmpty() || quantity.isBlank())
            return null;    //Since user is allowed to leave quantity and shelf field blank, it doesn't throw exception.
        else if (nonNumericDataEntered)
            throw new RuntimeException("Please enter only numeric values");

        return Double.parseDouble(quantity);
    }

    /** Validation of shelf field by checking if it is left empty or blank */
    public static String validateShelf(String shelfInput) {
        if (shelfInput.isBlank() || shelfInput.isEmpty())
            return null;

        return shelfInput;
    }

    /** Validation of ID field in case of non-numeric data is entered of it is left empty or blank. */
    public static Long validateID(String id){
        //checking If user enters non-numeric data.
        boolean nonNumericDataEntered = id.trim().replaceAll("[0-9.]", "").length() != 0;

        //checking If user left it empty.
        if (id.isEmpty() || id.isBlank())
            throw new RuntimeException("ID cannot be empty");
        else if (nonNumericDataEntered)
            throw new RuntimeException("Please enter only numeric values");

        return Long.parseLong(id);
    }
}
