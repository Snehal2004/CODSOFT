import java.util.ArrayList;
import java.util.List;

public class CourseDatabase {
    private List<Course> courses;

    public CourseDatabase() {
        courses = new ArrayList<>();
        // Add some initial courses
        courses.add(new Course("CS101", "Introduction to Computer Science", "Basic concepts of computer science.", 30,
                "MWF 10-11 AM"));
        courses.add(new Course("MATH101", "Calculus I", "Introduction to calculus.", 40, "TTh 9-10:30 AM"));
        courses.add(new Course("ENG101", "English Literature", "Study of English literature.", 25, "MWF 11-12 PM"));
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Course getCourseByCode(String code) {
        for (Course course : courses) {
            if (course.getCode().equals(code)) {
                return course;
            }
        }
        return null;
    }
}
