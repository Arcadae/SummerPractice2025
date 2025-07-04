public class LuxRoom extends ProRoom{
    
    public LuxRoom(int roomCapacity, int roomPrice, boolean isBooked) {
         super(roomCapacity, roomPrice, isBooked);
    }

    public LuxRoom(int roomCapacity, int roomPrice, boolean isBooked, int roomNumber) {
        super(roomCapacity, roomPrice, isBooked, roomNumber);
    }
}
