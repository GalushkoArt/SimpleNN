package functions;

import java.util.function.UnaryOperator;

public class Sigmoid implements ActivationFunction {
    @Override
    public UnaryOperator<Double> activation() {
        return x -> 1 / (1 + Math.exp(-x));
    }

    @Override
    public UnaryOperator<Double> derivative() {
        return y -> y * (1 - y);
    }

    @Override
    public String toString() {
        return "Sigmoid";
    }
}
