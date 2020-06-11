import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PerceptronTest extends Perceptron {
    public PerceptronTest(NeuralNetwork nn, String path) {
        super(nn, path);
    }

    public void test() throws IOException {
        prepare();
        run();
    }


    @Override
    protected void run() {
        int correct = 0;
        double errorSum = 0;
        for (int imgIndex = 0; imgIndex < samples; imgIndex++) {
            double[] targets = new double[10];
            int digit = digits[imgIndex];
            targets[digit] = 1;

            double[] outputs = nn.feedForward(inputs[imgIndex]);
            int answer = getAnswer(outputs);
            if(digit == answer) correct++;

            errorSum += getErrorSum(outputs, targets);
        }
        System.out.println("total tests: " + samples + ". correct: " + correct + ". total error: " + errorSum);
        logger.println("total tests,correct,total error\n" + samples + "," + correct + "," + errorSum);
        logger.close();
    }

    @Override
    public  String getFileName() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        return  "D:\\Java\\study\\SimpleNN\\log\\" + dtf.format(now) + "-test.csv";
    }
}
