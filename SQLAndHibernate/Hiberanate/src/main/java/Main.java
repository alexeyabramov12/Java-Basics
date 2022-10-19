import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;


public class Main {

    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        Course course = session.get(Course.class,1);
        List<Student> students = course.getStudents();

        Purchase purchase = session.get(Purchase.class,
                new PurchaseId("Фуриков Эрнст", "Мобильный разработчик с нуля"));

        Subscription subscription = session.get(Subscription.class, new SubscriptionId(1,2));
        students.forEach(System.out::println);
        System.out.println(course.getTeacher().getName());
        System.out.println(subscription.getSubscriptionDate());
        System.out.println(purchase.getStudentName() + " - " + purchase.getCourseName() + " - " +  purchase.getPrice());
        session.close();

    }

}
