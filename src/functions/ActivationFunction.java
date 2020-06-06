package functions;

import java.util.function.UnaryOperator;

public interface ActivationFunction {
    UnaryOperator<Double> activation();

    UnaryOperator<Double> derivative();
}
