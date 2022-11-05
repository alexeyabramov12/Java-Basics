import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int value = new Scanner(System.in).nextInt();
        int result = 1;
        String string = "";


        for (int i = 1; i <= value; i++) {
            result = result * i;
            string = "3!" + 12  + "*" + 3 + "=";
        }
        System.out.println(result);
    }
}
