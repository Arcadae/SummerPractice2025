import java.util.*;

public abstract class Room implements RoomService<Room> {
    
    //Поля данных
    private int roomNumber;
    private int roomCapacity;
    private int roomPrice;
    private boolean isBooked;

    //Конструкторы
    public Room(int roomCapacity, int roomPrice, boolean isBooked) {
        this.roomCapacity = roomCapacity;
        this.roomPrice = roomPrice;
        this.isBooked = isBooked;
        this.roomNumber = new Random().nextInt(0,251);
    }

    public Room(int roomCapacity, int roomPrice, boolean isBooked, int roomNumber) {
        this.roomCapacity = roomCapacity;
        this.roomPrice = roomPrice;
        this.isBooked = isBooked;
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "\n" + "==Ваша комната==" + "\n" +
                "Тип вашей комнаты - " + getClass().getSimpleName() + "\n" +
                "Номер комнаты " + roomNumber + "\n" +
                "Вместительностью в " + roomCapacity + " человек(а)" + "\n" +
                "Ценою в " + roomPrice + " золотых" + "\n" + 
                "Забронирована: " + isBooked + "\n";
    }

    @Override
    public void reserve() {
        if(this.isBooked) {
            throw new RoomAlreadyBookedException(
              "Комната уже забронирована"   
            );
        }
        isBooked = true;
    }

    @Override
    public void free() {
        if(this.isBooked){
            isBooked = false;
        } else {
            System.out.println("Комната уже свободна");
        }
    }

    //Геттеры
    public int getRoomNumber() {
        return roomNumber;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public boolean isBooked() {
        return isBooked;
    }

    //Сеттер
    public boolean setReserve(boolean reserve) {
        return isBooked = reserve;
    }
}