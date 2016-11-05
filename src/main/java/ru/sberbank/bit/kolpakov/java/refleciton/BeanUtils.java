package ru.sberbank.bit.kolpakov.java.refleciton;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Georgii Kolpakov on 05.11.16.
 */
public class BeanUtils {
    public static void assign(Object to, Object from) {
        Map<String, Method> getters = getAllGetters(from.getClass());
        Map<String, Method> setters = getAllSetters(to.getClass());
        setters.keySet()
                .stream()
                .filter(variable -> getters.containsKey(variable)
                        && isSameClazzOrSuperClass(getters.get(variable).getReturnType(),
                        setters.get(variable).getParameterTypes()[0]))
                .forEach(variable -> {
                    try {
                        Method setter = setters.get(variable);
                        Method getter = getters.get(variable);
                        getter.setAccessible(true);
                        setter.setAccessible(true);
                        setter.invoke(to, getter.invoke(from));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
    }

    private static boolean isSameClazzOrSuperClass(Class<?> clazz, Class<?> superClazz) {
        Class<?> tmpClazz = clazz;
        do {
            if (superClazz.equals(tmpClazz)) {
                return true;
            }
            tmpClazz = clazz.getSuperclass();
        } while (tmpClazz != null);
        return false;
    }

    private static Map<String, Method> getMethodsIndex(Class<?> clazz, Predicate<Method> filterPredicate) {
        Class<?> tmpClazz = clazz;
        Map<String, Method> result = new HashMap<>();
        while (tmpClazz.getSuperclass() != null) {
            result.putAll(Arrays.stream(tmpClazz.getDeclaredMethods())
                    .filter(filterPredicate)
                    .collect(Collectors.toMap(
                            method -> method.getName().substring(3),
                            method -> method)));
            tmpClazz = clazz.getSuperclass();
        }
        return result;
    }

    private static Map<String, Method> getAllGetters(Class<?> clazz) {
        return getMethodsIndex(clazz, method -> method.getName().startsWith("get")
                && !"void".equals(method.getReturnType().getName())
                && method.getParameterCount() == 0);
    }

    private static Map<String, Method> getAllSetters(Class<?> clazz) {
        return getMethodsIndex(clazz, method -> method.getName().startsWith("set")
                && "void".equals(method.getReturnType().getName())
                && method.getParameterCount() == 1);
    }


}
