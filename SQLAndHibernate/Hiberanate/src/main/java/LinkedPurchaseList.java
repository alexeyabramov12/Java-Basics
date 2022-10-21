import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "linked_purchase_list")
public class LinkedPurchaseList {
    @EmbeddedId
    private KeyStudentIdAndCourseId id;
    @Column(name = "student_id", insertable = false, updatable = false)
    public int studentId;
    @Column(name = "course_id", insertable = false, updatable = false)
    public int courseId;

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

    public KeyStudentIdAndCourseId getId() {
        return id;
    }

    public void setId(KeyStudentIdAndCourseId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LinkedPurchaseList{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
}
