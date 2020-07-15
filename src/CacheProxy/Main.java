package CacheProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {

        Calculator calculator = new CalculatorImpl();

        Class<?> proxyClass = Proxy.getProxyClass(
                Calculator.class.getClassLoader(),
                Calculator.class
        );

        try {
            Calculator calculatorProxy = (Calculator) proxyClass.getConstructor(InvocationHandler.class)
                    .newInstance(new CacheHandler(calculator));

            int result;
            double resultD;
            result = calculatorProxy.calc(2);
            resultD = calculatorProxy.pow(2, 5);
            result = calculatorProxy.calc(4);
            result = calculatorProxy.calc(2);
            resultD = calculatorProxy.pow(3, 2);
            result = calculatorProxy.calc(1);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }

        Calculator calculator1 = new CalculatorImpl();
        int result;
        double resultD;
        result = calculator1.calc(2);
        resultD = calculator1.pow(2, 5);
        result = calculator1.calc(4);
        result = calculator1.calc(2);
        resultD = calculator1.pow(3, 2);
        result = calculator1.calc(1);
    }
}
