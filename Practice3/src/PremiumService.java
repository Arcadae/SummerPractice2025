public class PremiumService implements LuxRoomService<LuxRoom> {
    public void foodDelivery(LuxRoom room, String food) {
        System.out.println("Ваш заказ: " + food.toLowerCase() + " был доставлен в комнату");
    }
}
