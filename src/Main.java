import functions.ActivationFunction;

import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static functions.ActivationFunctions.sigmoid;

public class Main {

    public static void main(String[] args) throws IOException {
        NeuralNetwork.setRandom(186237);
        digits(0.002, 784, 500, 300, 100, 10);
    }

    private static void digits(double learningRate, int... sizes) throws IOException {
        ActivationFunction function = sigmoid();
        NeuralNetwork nn = new NeuralNetwork(learningRate, function, sizes);
        PerceptronLearn learn = new PerceptronLearn(nn,"src\\resources\\train");
        PerceptronTest test = new PerceptronTest(nn, "src\\resources\\test");
        makeLog(learningRate, function.toString(), sizes);

        learn.learn(3000);
        test.test();

        FormDigits f = new FormDigits(nn);
        new Thread(f).start();
    }

    private static void makeLog(double learningRate, String activationFunction, int... sizes) throws IOException {
        PrintStream logger = new PrintStream(getFileName());
        logger.println("learning rate,activation function,layers");
        logger.println(learningRate + "," + activationFunction + "," + Arrays.toString(sizes));
        logger.close();
    }

    public static String getFileName() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        return  "D:\\Java\\study\\SimpleNN\\log\\" + dtf.format(now) + "-config.csv";
    }

}