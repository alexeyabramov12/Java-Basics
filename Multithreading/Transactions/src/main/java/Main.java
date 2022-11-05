public class Main {

    public static void main(String[] args) {
        try {
            Bank kaspiBank = new Bank();
            int countAccounts = 100000;
            for (int i = 1; i <= countAccounts; i++) {
                Account account = new Account(Integer.toString(i), 123456);
                kaspiBank.addAccount(Integer.toString(i), account);
            }

            System.out.println(kaspiBank.getSumAllAccounts());


            for (int i = 1; i <= countAccounts; i++) {
                String from = Integer.toString(i);
                String to = Integer.toString(countAccounts);
                long amount = 1000;
                if (i % 1000 == 0) {
                    new Thread(() -> kaspiBank.transfer(from, to, 55555));
                    continue;
                }
                new Thread(() -> kaspiBank.transfer(from, to, amount));
                countAccounts--;
            }

            System.out.println(kaspiBank.getSumAllAccounts());

        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
