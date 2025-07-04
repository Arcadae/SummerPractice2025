public class App {
    public static void main(String[] args) throws Exception {
        StandardRoom room = new StandardRoom(2, Prices.STANDARD.getPrice(), false);
        LuxRoom luxRoom = new LuxRoom(1,Prices.LUX.getPrice(),true);
        UltraLuxRoom ultraRoom = new UltraLuxRoom(1, Prices.ULTRALUX.getPrice(), false);
        PremiumService premiumService = new PremiumService();

        ultraRoom.clean(); // Пример работы интерфейса RoomService
        premiumService.foodDelivery(ultraRoom, "арбуз");
        premiumService.foodDelivery(luxRoom, "какао");

        System.out.println(room);
        room.reserve(); // Бронирование свободной комнаты 
        System.out.println(room);

        ultraRoom.free();//Освобождение свободной комнаты
        System.out.println(ultraRoom);

        luxRoom.reserve(); // Пример бронирования занятой комнаты
    }
}
