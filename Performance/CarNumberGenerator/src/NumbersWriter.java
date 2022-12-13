import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class NumbersWriter implements Runnable {

    private int regionCode;

    public NumbersWriter(int regionCode) {
        this.regionCode = regionCode;
    }

    @Override
    public void run() {
        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        try {
            PrintWriter writer = new PrintWriter("res/regionCode" + Integer.toString(regionCode) + ".txt");
            StringBuilder builder = new StringBuilder();
            for (int number = 1; number < 1000; number++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            builder.append(firstLetter);
                            builder.append(padNumber(number, 3));
                            builder.append(secondLetter);
                            builder.append(thirdLetter);
                            builder.append(padNumber(regionCode, 2));
                            builder.append("\n");
                        }
                    }
                }
            }
            writer.write(builder.toString());
            writer.flush();
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static String padNumber(int number, int numberLength) {
        String zero = "0";
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            numberStr = zero.concat(numberStr);
        }

        return numberStr;
    }


}

