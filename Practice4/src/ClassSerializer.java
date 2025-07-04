import java.lang.reflect.*;
import java.lang.annotation.*;
import java.util.*;

public class ClassSerializer {
    public static void consoleSerialize(Object obj, Class<? extends Annotation> myAnnotationClass, String annotationParam, int counter) {
        
        Class<?> clazz = obj.getClass();
        Map<String, Object> fields = new LinkedHashMap<>();

        for(Field field: clazz.getDeclaredFields()) {
            if(field.isAnnotationPresent(myAnnotationClass)) {
                Annotation annotation = field.getAnnotation(myAnnotationClass);

                try{
                    field.setAccessible(true);
                    String paramValue = annotation.annotationType().getMethod(annotationParam).invoke(annotation).toString();
                    Object value = field.get(obj);
                    fields.put(paramValue,value);
                } catch(IllegalAccessException e) {
                    System.out.println("Ошибка");
                } catch(NoSuchMethodException e) {
                    System.out.printf("(%d) Ошибка: у аннотации нет параметра '%s",counter,annotationParam);
                } catch(Exception e) {
                    System.out.printf("(%d) Ошибка при обработке %s %s",counter,field.getName(),e.getMessage());
                }
            }
        }

        System.out.println("(" + counter + ") " + toJsonString(fields));
    }

    private static String toJsonString(Map<String, Object> map) {
        StringBuilder jsonString = new StringBuilder("{");
        boolean afterElem = false;

        for(Map.Entry<String, Object> entry: map.entrySet()) {
            if(afterElem) {
                jsonString.append(", ");
            }
            afterElem = true;

            jsonString.append("\"").append(entry.getKey()).append("\":");

            Object value = entry.getValue();
            if (value instanceof String || value instanceof Character) {
                jsonString.append("\"").append(value).append("\"");
            } else if (value == null) {
                jsonString.append("null");
            } else {
                jsonString.append(value);
            }
        }

        return jsonString.append("}").toString(); 
    }
}
