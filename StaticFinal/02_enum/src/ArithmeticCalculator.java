public class ArithmeticCalculator {

    private final int number1;
    private final int number2;
    private Operation operation;
    private int result;

    public ArithmeticCalculator(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    public void calculate(Operation operation) {
        this.operation = operation;
        switch (operation) {
            case ADD:
                result = number1 + number2;
                break;
            case SUBTRACT:
                result = number1 - number2;
                break;
            case MULTIPLY:
                result = number1 * number2;
        }
    }

    public int getResult() {
        return result;
    }

}
