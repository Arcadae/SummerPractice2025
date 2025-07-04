public enum Prices {
    
    ECONOMY(50),
    STANDARD(100),
    LUX(400),
    ULTRALUX(900);

    private final int price;

    Prices(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
