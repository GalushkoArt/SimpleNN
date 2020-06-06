import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Perceptron {
    protected PrintStream logger;
    protected NeuralNetwork nn;
    protected String path;
    protected BufferedImage[] images;
    protected int[] digits;
    protected double[][] inputs;
    protected int samples;

    public Perceptron(NeuralNetwork nn, String path) {
        this.nn = nn;
        this.path = path;
    }

    protected void prepare() throws IOException {
        logger = new PrintStream(getFileName());
        File[] imagesFiles = new File(path).listFiles();
        samples = imagesFiles.length;
        images = new BufferedImage[samples];
        digits = new int[samples];
        for (int i = 0; i < samples; i++) {
            images[i] = ImageIO.read(imagesFiles[i]);
            digits[i] = Integer.parseInt(imagesFiles[i].getName().charAt(10) + "");
        }
        System.out.println("Images are loaded");

        inputs = loadInputs(images);
    }

    protected abstract void run();

    public String getFileName() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        return  "D:\\Java\\study\\SimpleNN\\log\\" + dtf.format(now) + ".csv";
    }

    public static double getErrorSum(double[] outputs, double[] targets) {
        double errorSum = 0;
        for (int k = 0; k < 10; k++) {
            errorSum += (targets[k] - outputs[k]) * (targets[k] - outputs[k]);
        }
        return errorSum;
    }

    public static int getAnswer(double[] outputs) {
        int maxDigit = 0;
        double maxDigitWeight = -1;
        for (int k = 0; k < 10; k++) {
            if(outputs[k] > maxDigitWeight) {
                maxDigitWeight = outputs[k];
                maxDigit = k;
            }
        }
        return maxDigit;
    }

    public static double[][] loadInputs(BufferedImage[] images) {
        double[][] inputs = new double[images.length][28*28];
        for (int i = 0; i < images.length; i++) {
            for (int x = 0; x < 28; x++) {
                for (int y = 0; y < 28; y++) {
                    inputs[i][x + y * 28] = (images[i].getRGB(x, y) & 0xff) / 255.0;
                }
            }
        }
        return inputs;
    }

}
