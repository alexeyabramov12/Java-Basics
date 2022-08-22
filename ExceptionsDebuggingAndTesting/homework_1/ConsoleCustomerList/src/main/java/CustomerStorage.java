import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;
    private Logger logger;

    public CustomerStorage() {
        storage = new HashMap<>();
        logger = LogManager.getRootLogger();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");
        String regexEmail1 = "[AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz.]+@[AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz.]+\\.com";
        String regexEmail2 = "[AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz.]+@[AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz.]+\\.ru";
        String regexPhone = "\\+7[0-9]{10}";
        String massage ="Wrong command! Available command examples: \n" +
                "add Василий Петров vasily.petrov@gmail.com +79215637722";
        if (components.length != 4) {
            logger.error("IllegalArgumentException: array length: " + components.length + ". Expected: 4");
            throw new IllegalArgumentException(massage);
        }
        if (!components[INDEX_EMAIL].matches(regexEmail1) && !components[INDEX_EMAIL].matches(regexEmail2)) {
            logger.error("Wrong format email");
            throw new IllegalArgumentException(massage);
        }
        if (!components[INDEX_PHONE].matches(regexPhone)) {
            logger.error("Wrong format phone");
            throw new IllegalArgumentException(massage);
        }

        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        if (storage.isEmpty()) {
            logger.error("List is empty");
            throw new IllegalArgumentException("List is empty");
        }
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        String message = "Wrong command! Available command examples: \nremove Василий Петров";
        String[] components = name.split("\\s+");
        if (components.length != 2){
            logger.error("IllegalArgumentException: array length: " + components.length + ". Expected: 2");
            throw new IllegalArgumentException(message);
        }
        if (!storage.containsKey(name)) {
            logger.error("IllegalArgumentException. Customer: \"" + name + "\" not found");
            throw new IllegalArgumentException("Customer: \"" + name + "\" not found");
        }
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}