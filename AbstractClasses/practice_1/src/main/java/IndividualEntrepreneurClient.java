public class IndividualEntrepreneurClient extends Client {

    @Override
    public void put(double amount) {
        if (amount > 0 && amount < 1000) {
            double commission1 = amount * 1 / 100;
            amountMoney += amount - commission1;
        }
        if (amount >= 1000) {
            double commission1 = amount * 0.5 / 100;
            amountMoney += amount - commission1;
        }
    }
}
