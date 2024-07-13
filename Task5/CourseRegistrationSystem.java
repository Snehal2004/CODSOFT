import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseRegistrationSystem extends JFrame {
    private CourseDatabase courseDatabase;
    private StudentDatabase studentDatabase;

    private JTextArea courseListArea;
    private JTextArea studentInfoArea;
    private JTextField studentIdField;
    private JTextField courseCodeField;
    private JButton registerButton;
    private JButton dropButton;

    public CourseRegistrationSystem() {
        courseDatabase = new CourseDatabase();
        studentDatabase = new StudentDatabase();

        setTitle("Course Registration System");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        courseListArea = new JTextArea(10, 30);
        courseListArea.setEditable(false);
        displayCourses();

        studentInfoArea = new JTextArea(10, 30);
        studentInfoArea.setEditable(false);

        studentIdField = new JTextField(10);
        courseCodeField = new JTextField(10);

        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerCourse();
            }
        });

        dropButton = new JButton("Drop");
        dropButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropCourse();
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Student ID:"));
        inputPanel.add(studentIdField);
        inputPanel.add(new JLabel("Course Code:"));
        inputPanel.add(courseCodeField);
        inputPanel.add(registerButton);
        inputPanel.add(dropButton);

        add(new JScrollPane(courseListArea), BorderLayout.NORTH);
        add(new JScrollPane(studentInfoArea), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }

    private void displayCourses() {
        courseListArea.setText("Available Courses:\n");
        for (Course course : courseDatabase.getCourses()) {
            courseListArea.append(course.getCode() + " - " + course.getTitle() + " (" + course.getEnrolledStudents()
                    + "/" + course.getCapacity() + ")\n");
        }
    }

    private void displayStudentInfo(Student student) {
        studentInfoArea.setText("Student Info:\n");
        studentInfoArea.append("ID: " + student.getId() + "\n");
        studentInfoArea.append("Name: " + student.getName() + "\n");
        studentInfoArea.append("Registered Courses:\n");
        for (Course course : student.getRegisteredCourses()) {
            studentInfoArea.append(course.getCode() + " - " + course.getTitle() + "\n");
        }
    }

    private void registerCourse() {
        String studentId = studentIdField.getText();
        String courseCode = courseCodeField.getText();

        Student student = studentDatabase.getStudentById(studentId);
        Course course = courseDatabase.getCourseByCode(courseCode);

        if (student != null && course != null) {
            if (course.enrollStudent()) {
                student.registerCourse(course);
                JOptionPane.showMessageDialog(this, "Course registered successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Course is full!");
            }
            displayCourses();
            displayStudentInfo(student);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid student ID or course code!");
        }
    }

    private void dropCourse() {
        String studentId = studentIdField.getText();
        String courseCode = courseCodeField.getText();

        Student student = studentDatabase.getStudentById(studentId);
        Course course = courseDatabase.getCourseByCode(courseCode);

        if (student != null && course != null) {
            if (student.getRegisteredCourses().contains(course)) {
                course.dropStudent();
                student.dropCourse(course);
                JOptionPane.showMessageDialog(this, "Course dropped successfully!");
                displayCourses();
                displayStudentInfo(student);
            } else {
                JOptionPane.showMessageDialog(this, "Student is not registered in this course!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid student ID or course code!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CourseRegistrationSystem().setVisible(true);
            }
        });
    }
}
