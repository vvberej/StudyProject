package CacheProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Динамический прокси
 */
public class CacheHandler implements InvocationHandler {
    private final Object original;
    private final Map<Integer, Integer> cache;

    public CacheHandler(Object original) {
        this.original = original;
        this.cache = new LinkedHashMap<>();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(args == null || args.length == 0)
            throw new IllegalArgumentException("Parameter required");

        if(method.isAnnotationPresent(Cache.class)) {
            int arg = (int) args[0];
            Integer result = cache.get(arg);
            if (result == null) {
                result = (Integer) method.invoke(original, arg);
                cache.put(arg, result);
            }
            else {
                System.out.println("Value from cache");
            }
            return result;
        }
        return method.invoke(original, args);
    }
}