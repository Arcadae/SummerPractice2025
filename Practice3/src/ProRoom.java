public abstract class ProRoom extends Room {

    public ProRoom(int roomCapacity, int roomPrice, boolean isBooked) {
        super(roomCapacity, roomPrice, isBooked);
    }

    public ProRoom(int roomCapacity, int roomPrice, boolean isBooked, int roomNumber) {
        super(roomCapacity, roomPrice, isBooked, roomNumber);
    }
    
}
