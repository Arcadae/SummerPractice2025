import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.*;

public class CarDealership {
    private List<Car> cars;

    //Конструктор
    public CarDealership() {
        this.cars = new ArrayList<>();
    } 

    //Метод для добавления машин
    public void addCar(Car car) {
        if(car != null) {
            cars.add(car);
            System.out.println("Вы добавили машину " + car.getVin());
        } else {
            System.out.println("Вы не можете добавить ничего");
        }
    }

    //Метод для отображения машин по марке
    public void displayCarsByBrand(String brand) {
        if(brand == null || brand.trim().isEmpty()) {
            System.out.println("Марка машины не указана");
            return;
        }

        List<Car> brandCar = cars.stream()
                                .filter(n -> n.getManufacturer().equalsIgnoreCase(brand.trim()))
                                .collect(Collectors.toList());

        if(brandCar.isEmpty()) {
            System.out.println("Машины марки " + brand + " нет в списке");
        } else {
            System.out.println("Машины марки " + brand + " :");
            brandCar.stream().forEach(System.out::println);
        }
    }

    //Получение средней цены за все машины одного типа
    public BigDecimal averagePriceByType(String carType) {
        if(carType == null || carType.trim().isEmpty()) {
            throw new IllegalArgumentException("Тип автомобиля не может быть пустым");
        }

        List<BigDecimal> prices =  cars.stream()
                                    .filter(n -> n.getType().equalsIgnoreCase(carType.trim()))
                                    .map(Car::getPrice)
                                    .collect(Collectors.toList());
        
        if(prices.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal sum = prices.stream()
                                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(new BigDecimal(prices.size()),2,RoundingMode.HALF_UP);
    }

    //Сортировка машин по годам: от старых к новым
    public List<Car> sortCarsInReverseYear() {
        if(cars.isEmpty()) {
            throw new IllegalArgumentException("Список не может быть пустым");
        }

        return cars.stream()
                    .sorted(Comparator.comparing(Car::getProductionYear).reversed())
                    .collect(Collectors.toList());
    }

    //Колличество машин каждого типа
    public Map<String, Long> countCarsByType() {
         if(cars.isEmpty()) {
            throw new IllegalArgumentException("Список не может быть пустым");
        }
        
        return cars.stream()
                .collect(Collectors.groupingBy(
                    Car::getType,
                    Collectors.counting()
                ));
    }
    
    //Вывод самой старой машины
    public void displayOldestCar() {
        Optional<Car> oldestCar = cars.stream()
                                    .min(Comparator.comparing(Car::getProductionYear));
        oldestCar.ifPresentOrElse(
            car -> System.out.println("Самая старая машина " + car),
            () -> System.out.println("В списке пусто")
        );
    }

    //Вывод самой новой машины
    public void displayNewestCar() {
          Optional<Car> oldestCar = cars.stream()
                                    .max(Comparator.comparing(Car::getProductionYear));
        oldestCar.ifPresentOrElse(
            car -> System.out.println("Самая новая машина " + car),
            () -> System.out.println("В списке пусто")
        );
    }
}
