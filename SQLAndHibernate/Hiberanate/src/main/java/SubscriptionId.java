import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubscriptionId implements Serializable {

    @Column(name = "student_id")
    private int studentId;
    @Column(name = "course_id")
    private int courseId;

    public SubscriptionId(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
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

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        if (!(o instanceof PurchaseId)) {
            return false;
        }
        SubscriptionId o1 = (SubscriptionId) o;
        return Objects.equals(getStudentId(), o1.getStudentId()) &&
                Objects.equals(getCourseId(), o1.getCourseId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentId(), getCourseId());
    }
}
