import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class CourseRegistrationSystem {
    static class Course {
        int courseCode;
        String courseName;
        int enrollmentCount;

        public Course(int courseCode, String courseName) {
            this.courseCode = courseCode;
            this.courseName = courseName;
            this.enrollmentCount = 0;
        }
    }

    static class Student {
        int studentNumber;
        String firstName;
        String lastName;
        List<String> enrolledCourses;

        public Student(int studentNumber, String firstName, String lastName) {
            this.studentNumber = studentNumber;
            this.firstName = firstName;
            this.lastName = lastName;
            this.enrolledCourses = new ArrayList<>();
        }
    }

    static class RegistrationSystem {
        Map<Integer, Course> courses;
        Map<Integer, Student> students;

        public RegistrationSystem() {
            this.courses = new HashMap<>();
            this.students = new HashMap<>();

            // Öğrencileri başlangıçta tanımla
            addStudent(1, "John", "Doe");
            addStudent(2, "Jane", "Smith");
            addStudent(3, "Bob", "Johnson");
            addStudent(4, "Alice", "Williams");
            addStudent(5, "Charlie", "Brown");

            // Kursları başlangıçta tanımla
            addCourse(101, "Mathematics");
            addCourse(102, "Physics");
            addCourse(103, "Computer Science");
            addCourse(104, "History");
            addCourse(105, "English");
            addCourse(106, "Biology");
            addCourse(107, "Chemistry");
            addCourse(108, "Economics");
            addCourse(109, "Art");
            addCourse(110, "Physical Education");
        }

        public void addCourse(int courseCode, String courseName) {
            Course course = new Course(courseCode, courseName);
            courses.put(courseCode, course);
        }

        public void addStudent(int studentNumber, String firstName, String lastName) {
            Student student = new Student(studentNumber, firstName, lastName);
            students.put(studentNumber, student);
        }

        public void displayCourseList() {
            System.out.println("Available Courses:");
            for (Course course : courses.values()) {
                System.out.println(course.courseCode + ": " + course.courseName +
                        " - Enrolled Students: " + course.enrollmentCount);
            }
        }

        public void enrollStudent(int studentNumber, int courseCode) {
            if (students.containsKey(studentNumber) && courses.containsKey(courseCode)) {
                Student student = students.get(studentNumber);
                Course course = courses.get(courseCode);

                student.enrolledCourses.add(course.courseName);
                course.enrollmentCount++;

                System.out.println("Enrollment successful for " + student.firstName + " in " + course.courseName);
            } else {
                System.out.println("Invalid student number or course code.");
            }
        }

        public void displayStatistics() {
            System.out.println("Course Enrollment Statistics:");
            for (Course course : courses.values()) {
                System.out.println(course.courseName + " - Enrolled Students: " + course.enrollmentCount);
            }
        }
    }

    public static void main(String[] args) {
        RegistrationSystem registrationSystem = new RegistrationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            // Kullanıcı girişi
            System.out.print("Enter student number: ");
            int studentNumber = scanner.nextInt();

            // Kurs listesini göster
            registrationSystem.displayCourseList();

            // Kurs seçimi
            System.out.print("Enter the course code you want to enroll in (0 to exit): ");
            int courseCode = scanner.nextInt();

            if (courseCode == 0) {
                break;
            }

            // Öğrenciyi kursa kaydet
            registrationSystem.enrollStudent(studentNumber, courseCode);
        }

        // İstatistikleri göster
        registrationSystem.displayStatistics();
    }
}