package reflection.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;



/**
 * Created by evami on 23.11.17.
 */
public class Utils {
    private static boolean objectFlag = true;
    private static int tabs = 0;

    private static String tab() {
        StringBuilder t = new StringBuilder();
        for(int i = 0; i < tabs; i++)
            t.append("\t");
        return t.toString();
    }

    public static String toString(Object obj) throws IllegalAccessException {
        Class<?> objClass = obj.getClass();
        StringBuilder stringBuilder = new StringBuilder();
        toString(objClass, stringBuilder, obj);
        return stringBuilder.toString();
    }

    private static void toString(Class<?> objClass, StringBuilder stringBuilder, Object object) throws IllegalAccessException {
        switch(TypeOfField.type(objClass)) {
            case PRIVITIVE:
                stringBuilder.append(stringBuilder.charAt(stringBuilder.length()-1) == '=' ? "" : tab()).append(" ").append(object.toString()).append(" ").append("\n");
                break;
            case ARRAY:
                stringBuilder.append(" [").append("\n");
                tabs++;
                Class<?> clazz = object.getClass();
                Class<?> type = clazz.getComponentType();
                for (int i = 0; i < Array.getLength(object); ++i)
                    toString(type.isInterface() ? Array.get(object, i).getClass() : type, stringBuilder, Array.get(object, i));
                tabs--;
                stringBuilder.append(tab()).append("]").append("\n");
                break;
            case OBJECT:
                if (objectFlag == true) {
                    stringBuilder.append(tab()).append(object.getClass().getSimpleName());
                    stringBuilder.append(" {").append("\n");
                    tabs++;
                }
                    for (Field field : objClass.getDeclaredFields()) {
                        if (field.getAnnotation(Exclude.class) == null) {
                            field.setAccessible(true);
                            stringBuilder.append(tab()).append(field.getName()).append(" =");
                            Object objectField = field.get(object);
                            if (objectField == null)
                                stringBuilder.append(" null").append("\n");
                            else
                                toString(objectField.getClass(), stringBuilder, objectField);
                        }
                    }
                    objectFlag = false;
                    toString(objClass.getSuperclass(), stringBuilder, object);
                break;
            case NULL:
                objectFlag = true;
                tabs--;
                stringBuilder.append(tab()).append("}").append("\n");
                return;
        }
    }
}
