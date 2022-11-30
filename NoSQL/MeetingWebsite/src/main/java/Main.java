import java.util.Random;

public class Main {

    public static void main(String[] args) {
        RedisStorage redisStorage = new RedisStorage();
        redisStorage.init();
        while (true) {
            int randomUser = new Random().nextInt(20-1) + 1;
            int randomPurchase = new  Random().nextInt(10-1) + 1;
            for (int i = 1; i <= 20; i++) {
                if (i != randomUser) {
                    redisStorage.addUser(i);
                    print(redisStorage);
                }
                if (i == randomPurchase) {
                    System.out.println("> Пользователь " + randomUser + " оплатил платную услугу");
                    redisStorage.addUser(randomUser);
                    print(redisStorage);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    private static void print(RedisStorage redisStorage) {
        System.out.println("— На главной странице показываем пользователя " + redisStorage.getUser());
    }

}
