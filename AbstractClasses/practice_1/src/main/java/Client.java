public abstract class Client {

    protected double amountMoney;

    public double getAmount() {
        return amountMoney;
    }

    public void put(double amount) {
        if (amount > 0) {
            amountMoney += amount;
        }
    }

    public void take(double amount) {
        if (amount < amountMoney) {
            amountMoney -= amount;
        }
    }

    public String getNAme() {
        return getClass().getName();
    }
}
