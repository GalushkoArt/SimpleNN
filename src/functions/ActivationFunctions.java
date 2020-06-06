package functions;

public class ActivationFunctions {
    private ActivationFunctions(){}

    public static ActivationFunction sigmoid() {
        return new Sigmoid();
    }

    public static ActivationFunction reLU() {
        return new ReLU();
    }
}
