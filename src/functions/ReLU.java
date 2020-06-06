package functions;

import java.util.function.UnaryOperator;

public class ReLU implements ActivationFunction {
    @Override
    public UnaryOperator<Double> activation() {
        return x -> x > 0 ? x : 0.0;
    }

    @Override
    public UnaryOperator<Double> derivative() {
        return x -> x > 0 ? 1.0 : 0.0;
    }

    @Override
    public String toString() {
        return "ReLU";
    }
}
