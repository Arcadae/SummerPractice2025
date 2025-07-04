public class EconomyRoom extends Room {

    public EconomyRoom(int roomCapacity, int roomPrice, boolean isBooked) {
        super(roomCapacity, roomPrice, isBooked);
    }
    
    public EconomyRoom(int roomCapacity, int roomPrice, boolean isBooked, int roomNumber) {
        super(roomCapacity, roomPrice, isBooked, roomNumber);
    }
}
