import java.util.*;
import java.util.stream.*;
import java.math.*;

public class App {

    //<=========================================== Задание номер 5 =============================================================>
    private static CarDealership cars = new CarDealership();
    private static Scanner scanner = new Scanner(System.in);

    //Заготовленные значения для списка
    private static void dataForDefault() {
        cars.addCar(new Car(5,350,new BigDecimal("599.99"),"BMW","SEDAN"));
        cars.addCar(new Car(10,450,new BigDecimal("399.99"),"BMW","SEDAN"));
        cars.addCar(new Car(15,650,new BigDecimal("299.99"),"Renault","SUV"));
        cars.addCar(new Car(20,950,new BigDecimal("199.99"),"Renault","SUV"));
        cars.addCar(new Car(25,1_350,new BigDecimal("799.99"),"Toyota","ELECTRIC"));
    }

    //Запустить меню
    private static void runMenu() throws InputMismatchException {
        while(true) {
            try {
                printMenu();
                int choice = readIntInput("Выберете пункт: ");
                
                switch(choice) {
                    case 1 -> addCar();
                    case 2 -> showCarsByBrand();
                    case 3 -> showAveragePriceByType();
                    case 4 -> showCarsInReverseYear();
                    case 5 -> showCarsByType();
                    case 6 -> viewOldestCar();
                    case 7 -> viewNewestCar();
                    case 0 -> { 
                        System.out.println("Выход из меню"); 
                        return;
                    }
                    default -> {System.out.println("Нет такого метода");}
                }
            } catch(InputMismatchException e) {
                System.out.println("Можно вводить только цифры");
                System.out.print("Введите любой символ, чтобы вернуться к программе ==> ");
                scanner.nextLine();
            }
        }
    }

    //Вывести меню
    private static void printMenu() {
        System.out.println("\n Меню салона");
        System.out.println("1. Добавить машину");
        System.out.println("2. Найти машины по маркам");
        System.out.println("3. Средняя цена машин одной марки");
        System.out.println("4. Отсортировать машины от старых к новым");
        System.out.println("5. Количество машин по типам");
        System.out.println("6. Самая старая машина");
        System.out.println("7. Самая новая машина");
        System.out.println("0. Выход");
    }

    //Добавить автомобиль
    private static void addCar() {
        System.out.println("\n Добавить автомобиль");
        int Vin = readIntInput("VIN: ");
        String manufacturer = readStringInput("Производитель: ");
        int mileage = readIntInput("Пробег: ");
        BigDecimal price = readBigDecimalInput("Цена: ");
        String carType = readStringInput("Тип: ");

        cars.addCar(new Car(Vin,mileage,price,manufacturer,carType));
        System.out.println("Машина добавлена!");
    }

    //Промпт для типа Int
    private static int readIntInput(String prompt) {
        System.out.print(prompt);
        try {
            return scanner.nextInt();
        } finally {
            scanner.nextLine();
        }
    }

    //Промпт для типа String
    private static String readStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    //Промпт для типа BigDecimal
    private static BigDecimal readBigDecimalInput(String prompt) {
        System.out.print(prompt);
        try {
            return new BigDecimal(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Некорректная цена! Используется 0.00");
            return BigDecimal.ZERO;
        }
    }

    //Вывести все машины одной марки
     private static void showCarsByBrand() {
        String brand = readStringInput("Введите марку авто: ");
        cars.displayCarsByBrand(brand);
    }

    //Вывести среднюю стоимость машин одного типа
    private static void showAveragePriceByType() {
        String carType = readStringInput("Введите тип авто: ");
        System.out.println(cars.averagePriceByType(carType));
    }

    //Вывести список машин от новых к старым
    public static void showCarsInReverseYear() {
        System.out.println(cars.sortCarsInReverseYear());
    }

    //Отобразить количество машин всех типов
    public static void showCarsByType() {
        Map<String, Long> counts = cars.countCarsByType();
        counts.forEach((carType,count) ->
            System.out.printf("%s: %d машина(ы)\n",carType,count)
        );
    }

    //Отобразить самую старую машину в списке
    public static void viewOldestCar() {
        cars.displayOldestCar();
    }

    //Отобразить самую новую машину в списке
    public static void viewNewestCar() {
        cars.displayNewestCar();
    }

    //<================================= Задание номер 1 =======================================================================>
    //Случайно(псевдо) создаёт массив с годами выпуска машин
    public static int[] getRandomYears() {
        return new Random().ints(50,2000,2026).toArray();
    }

    //Выводит в терминал все года после 2015
    public static void getYearsAfter2015(int[] myArray) {
        if (myArray.length == 0 || myArray == null) {
            throw new EmptyObjectException("У вас пустой массив");
        } else {
            System.out.println(Arrays.stream(myArray)
                                    .filter(year -> year > 2015)
                                    .mapToObj(Integer::toString)
                                    .collect(Collectors.joining(";")));
        }
    }

    //Выводит в терминал среднее значение массива с годами выпуска машин
    public static void displayAverageYear(int[] myArray) {
        if (myArray.length == 0 || myArray == null) {
            throw new EmptyObjectException("У вас пустой массив");
        } else {
            System.out.println("Средний год машин равен: " + Math.round(Arrays.stream(myArray)
                                                                .average()
                                                                .orElse(0)));
        }
    }

    //<============================ Задание номер 2 ============================================================================>
    //Создаёт список из 10 марок автомобилей
    public static List<String> listOfCars() {
        String[] typeOfCars = {"BMW","Lada","Toyota","Datsun","Tesla"};
        return IntStream.range(0,11)
                        .mapToObj(i -> typeOfCars[new Random().nextInt(typeOfCars.length)])
                        .collect(Collectors.toList());
    }

    //Убирает дубликаты из списка
    public static List<String> getListWithoutDuplicates(List<String> myList) {
        if(myList.isEmpty()) {  
            throw new EmptyObjectException("У вас пустой список");
        } else {
            return myList.stream()
                        .distinct()
                        .collect(Collectors.toList());
        }
    }

    //Сортирует список в обратном алфавитном порядке
    public static List<String> sortListInReverseOrder (List<String> myList) {
        if(myList.isEmpty()) {
            throw new EmptyObjectException("У вас пустой список");
        } else {
            return myList.stream()
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
        }
    }

    //Конвертирует List в Set
    public static Set<String> convertListToSet (List<String> myList) {
            if(myList.isEmpty()) {
                throw new EmptyObjectException("У вас пустой список");
            } else {
                return myList.stream()
                        .collect(Collectors.toCollection(LinkedHashSet::new));
            }
    }

    //Заменяет элемент "Tesla" на "ELECTRO_CAR" в списке
    public static List<String> changeTesla (List<String> myList) {
        if(myList.isEmpty()) {
            throw new EmptyObjectException("У вас пустой список");
        } else {
            return myList.stream()
                        .map(s -> s.equals("Tesla") ? "ELCETRO_CAR" : s)
                        .collect(Collectors.toList());
        }
    }
  

    public static void main(String[] args) throws Exception {

        int counter = 1;
        String lineSeparator = "<".repeat(50) + ">".repeat(50);

        //Задание номер 1
        int[] myYears = getRandomYears();
        System.out.printf("Задание №%d\n",counter);
        getYearsAfter2015(myYears);
        displayAverageYear(myYears);
        System.out.println(lineSeparator);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Задание номер 2
        /* List<String> myCarsList = listOfCars();

        myCarsList = getListWithoutDuplicates(myCarsList);
        myCarsList = sortListInReverseOrder(myCarsList);
        myCarsList = changeTesla(myCarsList);
        
        Set<String> myCarsSet = convertListToSet(myCarsList);
        System.out.printf("Задание №%d\n",++counter);
        System.out.println("СПИСОК МАШИН - " + myCarsList + "\n" + "СЕТ МАШИН - " + myCarsSet);
        System.out.println(lineSeparator); */
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Задание номер 3
        /* Set<Car> carsSet = new HashSet<Car>();

        carsSet.add(new Car(15,50_000,new BigDecimal("2000"),"BMW","SEDAN"));
        carsSet.add(new Car(20,25_000,new BigDecimal("3500.999"),"Mercedes","GEEP"));
        carsSet.add(new Car(15,350,new BigDecimal("5500"),"Tesla","ELECTRIC"));

        System.out.printf("Задание №%d\n",++counter);

        System.out.println("СПИСОК МАШИН В Set " + carsSet + "\n");

        Car[] carsArray = { new Car(5,350,new BigDecimal("500"),"Datsun","GEEP"),
                            new Car(10,550,new BigDecimal("600"),"Geely","GEEP"),
                            new Car(15,700,new BigDecimal("550.55"),"Porshe","CABRIOLET")};

        Arrays.sort(carsArray);
        System.out.println("ВАШ МАССИВ, ОТСОРТИРОВАННЫЙ ПО УБЫВАНИЮ ГОДА: " + Arrays.toString(carsArray));
        System.out.println(lineSeparator); */
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Задание номер 4
        /* List<Car> carsList = List.of(new Car(15,50_000,new BigDecimal("2000"),"BMW","SEDAN"),
                                new Car(35,20_000,new BigDecimal("2500"),"BMW","SEDAN"),
                                new Car(20,25_000,new BigDecimal("3500.999"),"Mercedes","SUV"),
                                new Car(30,30_000,new BigDecimal("300"),"Lada","SEDAN"),
                                new Car(10,40_000,new BigDecimal("4600.850"),"Lamborghini","CABRIOLET"));

        System.out.printf("Задание №%d\n",++counter);

        System.out.println("СПИСОК МАШИН С ПРОБЕГОМ МЕНЬШЕ 50_000: " + carsList.stream()
                                .filter(n -> n.getMileage() < 50_000)
                                .collect(Collectors.toList()) + "\n");

        System.out.println("СПИСОК МАШИН ПО УБЫВАЮЩЕЙ ЦЕНЕ: " + carsList.stream()
                                .sorted(Comparator.comparing(Car::getPrice).reversed())
                                .collect(Collectors.toList()) + "\n");

        System.out.println("3 САМЫХ ДОРОГИХ МАШИНЫ:");
        carsList.stream()
            .sorted(Comparator.comparing(Car::getPrice).reversed())
            .limit(3)
            .forEach(System.out::println);

        System.out.println("\n" + "СРЕДНИЙ ПРОБЕГ = " + carsList.stream()
                                                                .mapToInt(Car::getMileage)
                                                                .average()
                                                                .orElse(0) + "\n");

        Map<String,List<Car>> carsByManufacturer = carsList.stream()
                                                        .collect(Collectors.groupingBy(Car::getManufacturer));
        carsByManufacturer.forEach((manufacturer,car) ->
            System.out.printf("%s: %s\n",manufacturer,car)
        );
        System.out.println(lineSeparator); */
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        //Задание номер 5
        /* dataForDefault();
        runMenu(); */
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
    }
}
