import java.util.function.*;
import java.util.Random;
import java.lang.reflect.*;

public class App {
    public static void main(String[] args) throws Exception {
        int counter = 1;
        
        Printable myPrint = () -> System.out.println("Printable!");
        System.out.print(String.format("(%d) ",counter));
        myPrint.print();
        System.out.println("<".repeat(20) + ">".repeat(20));

        Predicate<String> isNotNull = str -> str != null;
        System.out.println(String.format("(%d) %b",++counter,isNotNull.test("Not null")));
        System.out.println("<".repeat(20) + ">".repeat(20));

        Predicate<String> isNotEmpty = str -> !str.isEmpty();
        System.out.println(String.format("(%d) %b",++counter,isNotEmpty.test("Not empty")));
        System.out.println("<".repeat(20) + ">".repeat(20));

        Predicate<String> isNotNullAndEmpty = new Predicate<String>() {

            @Override
            public boolean test(String str) {
                return isNotNull.and(isNotEmpty).test(str);
            }
        };
        System.out.println(String.format("(%d) %b",++counter,isNotNullAndEmpty.test("Not null and not empty")));
        System.out.println("<".repeat(20) + ">".repeat(20));

        Predicate<String> stringWith = new Predicate<String>() {

            @Override
            public boolean test(String str) {
                return (str.startsWith("J") || str.startsWith("N")) && str.endsWith("A");
            }
        };
        System.out.println(String.format("(%d) %b",++counter,stringWith.test("JacondA")));
        System.out.println("<".repeat(20) + ">".repeat(20));

        Consumer<HeavyBox> firstBoxConsumer = yourBox -> System.out.print(
            String.format("Отгрузили ящик с весом %d ===> ",yourBox.getWeight())
            );
        Consumer<HeavyBox> secondBoxConsumer = yourBox -> System.out.println(
            String.format("Отправляем ящик с весом %d",yourBox.getWeight())
            );
        System.out.print(String.format("(%d) ",++counter));
        firstBoxConsumer.andThen(secondBoxConsumer).accept(new HeavyBox(25));
        System.out.println("<".repeat(20) + ">".repeat(20));

        Function<Integer, String> isPositive = number -> number > 0 
                ? "Положительное число"
                : (number < 0 ? "Отрицательное число" : "Ноль");
        System.out.println(String.format("(%d) %s",++counter,isPositive.apply(0)));
        System.out.println("<".repeat(20) + ">".repeat(20));

        Supplier<Integer> randomNumber = () -> new Random().nextInt(0,11);
        System.out.println(String.format("(%d) %d",++counter,randomNumber.get()));
        System.out.println("<".repeat(20) + ">".repeat(20));

        //Решение - Задание 1: Кастомная аннотация @DeprecatedEx

        // TestClass testClass = new TestClass();
        // Class<?> clazz = testClass.getClass();
        // Method[] methods = clazz.getDeclaredMethods();
        
        // int otherCounter = counter + 1;

        // if(clazz.isAnnotationPresent(DeprecatedEx.class)) {
        //     String className = clazz.getName();
        //     String classAlternative = clazz.getAnnotation(DeprecatedEx.class).message();
        //     System.out.println(String.format("(%d) Внимание: класс %s устарел. Альтернатива: %s",otherCounter,className,classAlternative));
        // }

        // for(Method method: methods) {
        //     if(method.isAnnotationPresent(DeprecatedEx.class)) {
        //         String methodName = method.getName();
        //         String methodAlternative = method.getAnnotation(DeprecatedEx.class).message();
        //         System.out.println(String.format("(%d) Внимание: метод %s устарел. Альтернатива: %s",otherCounter,methodName,methodAlternative));
        //     }
        // }
        // System.out.println("<".repeat(20) + ">".repeat(20));

        //Решение через класс - Задание 1: Кастомная аннотация @DeprecatedEx
        AnnotationProcessor.findAnnotatedElements(
            new TestClass().getClass(),
            DeprecatedEx.class,
            "message",
            ++counter
        );
        System.out.println("<".repeat(20) + ">".repeat(20));

        TestClass testClass = new TestClass();
        ClassSerializer.consoleSerialize(
            testClass,
            JsonField.class,
            "name",
            ++counter
        );
        System.out.println("<".repeat(20) + ">".repeat(20));
    }
}
