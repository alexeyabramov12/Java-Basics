import java.io.File;

public class Main {

    private static int newWidth = 300;
    private static String srcFolder = "C:/Users/Decst/OneDrive/Рабочий стол/Src";
    private static String dstFolder = "C:/Users/Decst/OneDrive/Рабочий стол/Dst";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int numberOfCores = Runtime.getRuntime().availableProcessors();
        System.out.println(numberOfCores);
        try {
            File srcDir = new File(srcFolder);

            File[] files = srcDir.listFiles();

            if (files.length <= numberOfCores) {
                ImageResizer resizer = new ImageResizer(files,newWidth, dstFolder, start);
                new Thread(resizer).start();
            }
            else {
                int part = files.length / numberOfCores;
                for (int i = 0; i < numberOfCores; i++) {
                    File[] partOfFiles = new File[part + 1];
                    System.arraycopy(files,part * i, partOfFiles, 0, partOfFiles.length);
                    ImageResizer resizer = new ImageResizer(partOfFiles, newWidth, dstFolder, start);
                    new Thread(resizer).start();
                }
            }
            System.out.println( "fdfsf");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
