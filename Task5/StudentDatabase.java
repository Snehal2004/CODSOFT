import java.util.ArrayList;
import java.util.List;

public class StudentDatabase {
    private List<Student> students;

    public StudentDatabase() {
        students = new ArrayList<>();
        // Add some initial students
        students.add(new Student("S001", "Alice"));
        students.add(new Student("S002", "Bob"));
        students.add(new Student("S003", "Charlie"));
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student getStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }
}
