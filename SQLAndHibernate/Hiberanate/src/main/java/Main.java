import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<LinkedPurchaseList> linkedPurchaseLists = getLinkedPurchaseList(session);
        linkedPurchaseLists.forEach(session::persist);
        transaction.commit();
        session.close();
    }

    private static List<LinkedPurchaseList> getLinkedPurchaseList(Session session) {
        List<Course> courses = getListCourse(session);
        List<Student> students = getListStudent(session);
        List<Purchase> purchases = getListPurchase(session);
        List<LinkedPurchaseList> linkedPurchaseLists = new ArrayList<>();
        purchases.forEach(purchase -> {
            students.forEach(student -> {
                courses.forEach(course -> {
                    if (purchase.getStudentName().equals(student.getName()) && purchase.getCourseName().equals(course.getName())) {
                        LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
                        linkedPurchaseList.setStudentId(student.getId());
                        linkedPurchaseList.setCourseId(course.getId());
                        linkedPurchaseList.setId(new KeyStudentIdAndCourseId(student.getId(), course.getId()));
                        linkedPurchaseLists.add(linkedPurchaseList);
                    }
                });
            });
        });
        return linkedPurchaseLists;
    }

    private static List<Course> getListCourse(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    private static List<Student> getListStudent(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    private static List<Purchase> getListPurchase(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Purchase> query = builder.createQuery(Purchase.class);
        Root<Purchase> root = query.from(Purchase.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }
}
