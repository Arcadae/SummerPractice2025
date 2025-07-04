@DeprecatedEx(message = "новый класс")
public class TestClass {    
    
    @JsonField(name = "Имя")
    private String name = "Alyosha";

    @JsonField(name = "Фамилия")
    private String secondName = "Михайлов";

    @JsonField(name = "Возраст")
    private int age = 30;

    @JsonField(name = "В браке")
    private boolean isMaried = true;

    @DeprecatedEx(message = "новый метод")
    void annotatedMethod() {
        System.out.println("Yes");
    }

    @DeprecatedEx(message = "another new method")
    void anotherAnnotatedMethod() {
        System.out.println("Another yes");
    }

    void notAnnotatedMethod() {
        System.out.println("No");
    }

    @DeprecatedEx(message = "другой новый метод")
    void otherAnnotationMethod() {
        System.out.println("Other yes");
    }
}
