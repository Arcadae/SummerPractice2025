import java.math.BigDecimal;
import java.util.Objects;
import java.util.Random;

public class Car implements Comparable<Car> {

    //VIN машин
    private int VIN;

    //Модель автомобиля
    private String modelOfCar;

    //Производитель
    private String manufacturer;

    //Год выпуска автомобиля
    private int productionYear;

    //Пробег
    private int mileage;

    //Цена
    private BigDecimal price;

    //Тип машины
    private String carType;

    //Конструктор
    public Car (int VIN, int mileage, BigDecimal price, String manufacturer, String carType) {
        this.VIN = VIN;
        this.mileage = mileage;
        this.price = price;
        this.manufacturer = manufacturer;
        this.carType = carType;
    }

    //Блок инициализатор полей по умолчанию
    {
        this.productionYear = new Random().nextInt(2000,2025);
    }

    @Override
    public int compareTo(Car c) {
        return c.getProductionYear() - this.productionYear;
    }

    @Override
    public String toString() {
        return "{" +
                "VIN:" + VIN +
                ", model of car:" + modelOfCar +
                ", manufacturer:" + manufacturer +
                ", production year:" + productionYear +
                ", mileage:" + mileage +
                ", price:" + price +
                ", type:" + carType +
                "}";
    }
    
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;

        if(o == null || getClass() != o.getClass()) return false;

        Car otherCar = (Car) o;

        return Objects.equals(this.VIN, otherCar.VIN);
    }

    @Override
    public int hashCode() {
        return 31 * VIN;
    }

    //Геттеры
    public int getVin() {
        return VIN;
    }

    public int getMileage() {
        return mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public String getType() {
        return carType;
    }
}