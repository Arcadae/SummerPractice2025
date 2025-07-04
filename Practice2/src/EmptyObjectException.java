public class EmptyObjectException extends RuntimeException {
    public EmptyObjectException() {
        super();
    }
    
    public EmptyObjectException(String errorMessage) {
        super(errorMessage);
    }
}
