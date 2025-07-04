import java.lang.reflect.*;
import java.lang.annotation.*;;

public class AnnotationProcessor {

    static void findAnnotatedElements(Class<?> clazz, Class<? extends Annotation> annotationClass, String annotationParam, int counter) {
        
        if(clazz.isAnnotationPresent(annotationClass)) {
            Annotation classAnnotation = clazz.getAnnotation(annotationClass);
            formatAnnotationOutput("класс",clazz.getName(),classAnnotation,annotationParam,counter);
        }

        for(Method method: clazz.getDeclaredMethods()) {
            if(method.isAnnotationPresent(annotationClass)) {
                Annotation methodAnnotation = method.getAnnotation(annotationClass);
                formatAnnotationOutput("метод",method.getName(),methodAnnotation,annotationParam,counter);
            }
        }
    }

    private static void formatAnnotationOutput(String elemType, String elemName, Annotation annotation, String paramName, int counter) {
        try{
            Object paramValue = annotation.annotationType().getMethod(paramName).invoke(annotation);
            System.out.printf("(%d) Внимание: %s %s устарел. Альтернатива: %s%n",counter,elemType,elemName,paramValue);
        } catch(NoSuchMethodException e) {
            System.out.printf("(%d) Ошибка: у аннотации нет параметра '%s'%n",counter,paramName);
        } catch(Exception e) {
            System.out.printf("(%d) Ошибка при обработке %s %s: %s%n",counter,elemType,elemName,e.getMessage());
        }
    }
}
