public class RoomAlreadyBookedException extends RuntimeException {
    public RoomAlreadyBookedException(String errorMessage) {
        super(errorMessage);
    }
}
