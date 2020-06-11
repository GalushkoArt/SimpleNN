package functions;

import java.util.function.UnaryOperator;

import static java.lang.Math.max;

public class ReLU implements ActivationFunction {  // todo fix ReLU
    @Override
    public UnaryOperator<Double> activation() {
        return x -> max(0.0, x);
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
