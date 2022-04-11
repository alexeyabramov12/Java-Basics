public class Main {
    public static void main(String[] args) {
        ArithmeticCalculator calculator = new ArithmeticCalculator(4,5);
        calculator.calculate(Operation.ADD);
        System.out.println(calculator.getResult());
    }
}
