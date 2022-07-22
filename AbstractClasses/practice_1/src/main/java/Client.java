public abstract class Client {

    protected double amountMoney;

    public double getAmount() {
        //TODO: реализуйте метод и удалите todo
        return amountMoney;
    }

    public void put(double amount) {
        if (amount > 0) {
            amountMoney += amount;
        }
        //TODO: реализуйте метод и удалите todo
    }

    public void take(double amount) {
        //TODO: реализуйте метод и удалите
        if (amount < amountMoney) {
            amountMoney -= amount;
        }
    }

    public String getNAme() {
        return getClass().getName();
    }
}
