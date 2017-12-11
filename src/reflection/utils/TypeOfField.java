package reflection.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by evami on 23.11.17.
 */
public enum TypeOfField {
    NULL,
    PRIVITIVE,
    OBJECT,
    ARRAY;

    protected static ArrayList<Class<?>> classes = new ArrayList<>();

    static  {
        classes.add(String.class);
        classes.add(Integer.class);
        classes.add(Double.class);
        classes.add(Short.class);
        classes.add(Character.class);
        classes.add(Long.class);
        classes.add(Byte.class);
        classes.add(Byte.class);
        classes.add(Float.class);
        classes.add(Boolean.class);
        classes.add(Collection.class);
        classes.add(Map.class);
    }


    public static TypeOfField type(Class<?> clazz){
        if (clazz == null)
            return TypeOfField.NULL;
        if (clazz.isArray())
            return TypeOfField.ARRAY;
        if(classes.indexOf(clazz) != -1)
            return PRIVITIVE;
        if (interfaces(clazz, Collection.class))
            return PRIVITIVE;
        if (interfaces(clazz, Map.class))
            return PRIVITIVE;
        return TypeOfField.OBJECT;
    }

    private static boolean interfaces(Class<?> clazz, Class<?> clazz2){
        Class<?>[] inters = clazz.getInterfaces();
        for (Class<?> inter: inters){
            if (inter == clazz2)
                return true;
            Class<?> superInter = inter.getSuperclass();
            while (superInter != null){
                if (interfaces(superInter, clazz2))
                    return true;
                superInter = superInter.getSuperclass();
            }
        }
        Class<?> superClass = clazz.getSuperclass();
        while (superClass != null){
            if (interfaces(superClass, clazz2))
                return true;
            superClass = superClass.getSuperclass();
        }
       return false;
    }
}
