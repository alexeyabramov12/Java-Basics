
public class Loader {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();



        for (int regionCode = 1; regionCode < 199; regionCode++) {
            NumberThread numberThread = new NumberThread(regionCode);
            numberThread.start();
        }
        System.out.println((System.currentTimeMillis() - start) + " ms");
    }


}
