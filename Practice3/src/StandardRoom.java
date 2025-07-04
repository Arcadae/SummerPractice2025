public class StandardRoom extends ProRoom {

    public StandardRoom(int roomCapacity, int roomPrice, boolean isBooked) {
        super(roomCapacity, roomPrice, isBooked);
    }
    
    public StandardRoom(int roomCapacity, int roomPrice, boolean isBooked, int roomNumber) {
        super(roomCapacity, roomPrice, isBooked, roomNumber);
    }
}
