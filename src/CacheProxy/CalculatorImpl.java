package CacheProxy;

public class CalculatorImpl implements Calculator {
    @Override
    public int calc(int val) {
        return val * val;
    }

    @Override
    public double pow(double base, double exponent) {
        return Math.pow(base, exponent);
    }
}