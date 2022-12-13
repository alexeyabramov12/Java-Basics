
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Loader {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(12);
        for (int regionCode = 1; regionCode <= 199; regionCode++) {
            executor.execute(new NumbersWriter(regionCode));
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println(System.currentTimeMillis() - start);
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
