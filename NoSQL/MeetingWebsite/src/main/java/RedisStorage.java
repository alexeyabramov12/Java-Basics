import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;


import static java.lang.System.out;

public class RedisStorage {


    private RedissonClient redisson;

    private RKeys rKeys;

    private RScoredSortedSet<String> users;

    private final static String KEY = "USERS";


    public void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");

            out.println(Exc.getMessage());
        }
        rKeys = redisson.getKeys();
        users = redisson.getScoredSortedSet(KEY);
    }

    public void addUser(int id) {
        users.add(System.currentTimeMillis(), Integer.toString(id));
    }

    public String getUser() {
        return users.last();
    }

    public void clear() {
        users.clear();
    }

}

