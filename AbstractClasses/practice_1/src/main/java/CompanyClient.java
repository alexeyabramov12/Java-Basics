public class CompanyClient extends Client {

    @Override
    public void take(double amount) {
        double commission = amount * 1 / 100;
        if (amount < amountMoney + commission) {
            amountMoney -= amount + commission;
        }
    }
}
