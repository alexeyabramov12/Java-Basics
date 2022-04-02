public class Arithmetic {

    private int nomber1;
    private int nomber2;

    public Arithmetic(int nomber1, int nomber2) {
        this.nomber1 = this.nomber1 + nomber1;
        this.nomber2 = this.nomber2 + nomber2;
    }

    public int getSum() {
        System.out.print("сумма: ");
        int sum = nomber1 + nomber2;
        return sum;
    }

    public int getProductOfNumbers() {
        System.out.print("произведение: ");
        int productOfNumbers = nomber1 * nomber2;
        return productOfNumbers;
    }

    public int getMaxNomber() {
        System.out.print("Максимальное число: ");
        if (nomber1 > nomber2) {
            return nomber1;
        }
        return nomber2;
    }

    public int getMinNomber() {
        System.out.print("Минимальное число: ");
        if (nomber1 < nomber2) {
            return nomber1;
        }
        return nomber2;
    }

    public void print(){
        System.out.println();
        System.out.println(getSum());
        System.out.println(getProductOfNumbers());
        System.out.println(getMaxNomber());
        System.out.println(getMinNomber());

    }
}
