import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions")
public class Subscription {
    @EmbeddedId
    private KeyStudentIdAndCourseId id;
    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;
    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;
    @Column(name = "subscription_date")
    private LocalDateTime subscriptionDate;

    public KeyStudentIdAndCourseId getId() {
        return id;
    }

    public void setId(KeyStudentIdAndCourseId id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public LocalDateTime getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDateTime subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
