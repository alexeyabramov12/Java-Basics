import java.util.*;

public class Bank {

    private Map<String, Account> accounts;
    private final Random random = new Random();

    public Bank() {
        accounts = new HashMap<>();
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        if (!accounts.containsKey(fromAccountNum) && !accounts.containsKey(toAccountNum)) {
            System.out.println("error ;(");
            return;
        }

        Account fromAccount = accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);

        if (fromAccount.isBlocked() || toAccount.isBlocked() || fromAccount.getMoney() < amount || amount <= 0) {
            System.out.println("error :(");
            return;
        }

        if (amount > 50_000) {
            boolean examination = false;
            try {
                examination = isFraud(fromAccountNum, toAccountNum, amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fromAccount.setBlocking(examination);
            toAccount.setBlocking(examination);
        }

        if ((!fromAccount.isBlocked() || !toAccount.isBlocked())) {
            synchronized (fromAccount) {
                fromAccount.setMoney(fromAccount.getMoney() - amount);
                toAccount.setMoney(toAccount.getMoney() + amount);
            }
        }

    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        if (accounts.containsKey(accountNum)) {
            Account account = accounts.get(accountNum);
            return account.getMoney();
        }
        return 0L;
    }

    public long getSumAllAccounts() {
        long sum = 0l;
        Collection<Account> accountList = accounts.values();
        for (Account account : accountList) {
            sum += account.getMoney();
        }
        return sum;
    }

    public void addAccount(String accNumber, Account account) {
        accounts.put(accNumber, account);
    }

    public Account getAccount(String accNumber) {
        if (accounts.containsKey(accNumber)) {
           return accounts.get(accNumber);
        }
        return null;
    }

}
