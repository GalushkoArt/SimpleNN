import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PerceptronLearn extends Perceptron {
    protected int sets = 600;

    public PerceptronLearn(NeuralNetwork nn, String path) {
        super(nn, path);
    }

    public void learn() throws IOException {
        prepare();
        run();
    }

    public void learn(int sets) throws IOException {
        this.sets = sets;
        prepare();
        run();
    }

    @Override
    protected void run() {
        logger.println("set,correct,error sum");
        for (int i = 0; i < sets; i++) {
            int correct = 0;
            double[] errors;
            double errorSum = 0;
            int batchSize = 100;
            for (int j = 0; j < batchSize; j++) {
                int imgIndex = (batchSize * i + j) % samples;
                double[] targets = new double[10];
                int digit = digits[imgIndex];
                targets[digit] = 1;

                double[] outputs = nn.feedForward(inputs[imgIndex]);
                int answer = getAnswer(outputs);
                if(digit == answer) correct++;

                errors = getErrors(outputs, targets);
                errorSum += getErrorSum(outputs, targets);
                nn.backpropagation(errors);
            }
            System.out.println("set: " + i + ". correct: " + correct + ". error: " + errorSum);
            logger.println(i + "," + correct + "," + errorSum);
        }
        logger.close();
    }

    @Override
    public  String getFileName() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        return  "D:\\Java\\study\\SimpleNN\\log\\" + dtf.format(now) + "-learn.csv";
    }
}
