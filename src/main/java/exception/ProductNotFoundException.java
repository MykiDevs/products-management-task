package exception;


public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException(Long id) {
        super("User with id " + id + "not found");
    }
}
