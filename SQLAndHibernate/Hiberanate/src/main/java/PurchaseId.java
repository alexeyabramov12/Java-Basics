import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PurchaseId implements Serializable {

    @Column(name = "student_name")
    private String studentName;
    @Column(name = "course_name")
    private String courseName;

    public PurchaseId(String studentName, String courseName) {
        this.studentName = studentName;
        this.courseName = courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        if (!(o instanceof PurchaseId)) {
            return false;
        }
        PurchaseId o1 = (PurchaseId) o;
        return Objects.equals(getStudentName(), o1.getStudentName()) &&
        Objects.equals(getStudentName(), o1.getCourseName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentName(), getCourseName());
    }

}
