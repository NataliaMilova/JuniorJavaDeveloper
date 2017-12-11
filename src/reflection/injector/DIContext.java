package reflection.injector;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by evami on 24.11.17.
 */

public class DIContext {
    private static HashMap<Class<?>, Object> instances = new HashMap<>();
    private static final DIContext INSTANCE = new DIContext();

    public static DIContext instance(){
        return INSTANCE;
    }

    private DIContext(){
    }


    public <T> T getObject(String className) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new DependencyInjectionException("Can't find class by this path: " + className, e);
        }
        Object obj = null;
        try {
            obj = clazz.newInstance();
        } catch (InstantiationException e) {
            throw new DependencyInjectionException("Can't create exemplar of class: " + clazz, e);
        } catch (IllegalAccessException e) {
            throw new DependencyInjectionException("Don't have access to call newInstance() of class: " + clazz.getSimpleName(), e);
        }
        return (T) inject(clazz, obj);
    }

    private static Object inject(Class<?> clazz, Object obj) {
        Class<?> fieldClass;
        Object fieldObject;
        boolean singleton;
        if (checkInstance(clazz) != null)
            return checkInstance(clazz);
        for (Field field: clazz.getDeclaredFields()){
            field.setAccessible(true);
            Resource ann = field.getAnnotation(Resource.class);
            if (ann != null){
                fieldClass = ann.value();
                if (fieldClass == DefaultClass.class)
                    fieldClass = field.getType();
                singleton = ann.singleton();
                try {
                    fieldObject = getInstance(fieldClass);
                    field.set(obj, inject(fieldClass, fieldObject));
                } catch (IllegalAccessException e) {
                    throw new DependencyInjectionException("Don't have access to field: ", e);
                } catch (InstantiationException e) {
                    throw new DependencyInjectionException("Can't create exemplar of class: " + fieldClass.getSimpleName(), e);
                }
                if (singleton)
                    instances.putIfAbsent(fieldClass, fieldObject);
            }
        }
        return obj;
    }

    private static Object getInstance(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        Object obj;
        if (instances.containsKey(clazz))
            obj = instances.get(clazz);
        else
            obj = clazz.newInstance();
        return obj;
    }

    private static Object checkInstance(Class<?> clazz){
        return instances.get(clazz);
    }
}
