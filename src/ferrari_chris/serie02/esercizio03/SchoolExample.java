package ferrari_chris.serie02.esercizio03;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

interface Visiting {
    String getOrigin();
}

class Person implements Comparable<Person> {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Person other) {
        return name.compareTo(other.getName());
    }
}

class Student extends Person {
    private int enrolmentYear;

    public Student(String name, int enrolmentYear) {
        super(name);
        this.enrolmentYear = enrolmentYear;
    }

    public int getEnrolmentYear() {
        return enrolmentYear;
    }
}

class VisitingStudent extends Student implements Visiting {
    private String origin;

    public VisitingStudent(String name, int enrolmentYear, String origin) {
        super(name, enrolmentYear);
        this.origin = origin;
    }

    @Override
    public String getOrigin() {
        return origin;
    }
}

class Instructor extends Person {
    private String subject;

    public Instructor(String name, String subject) {
        super(name);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
}

class VisitingInstructor extends Instructor implements Visiting {
    private String origin;


    public VisitingInstructor(String name, String subject, String origin) {
        super(name, subject);
        this.origin = origin;
    }

    @Override
    public String getOrigin() {
        return origin;
    }
}

class PersonUtils {
    public static void printNames(String title, List<? extends Person> people) {
        System.out.println("printNames " + title);
        for (Person person : people) {
            System.out.printf("- %s\n", person.getName());
        }
    }

    public static <T> void printSubjects(String title, List<? extends Instructor> people) {
        System.out.println("printSubjects " + title);
        // Sample "- Dave teaches Signal Processing"
        for (Instructor person : people) {
            System.out.printf("- %s teaches %s.\n", person.getName(), person.getSubject());
        }
    }

    public static void printStudents(String title, List<? extends Student> people) {
        System.out.println("printStudents " + title);

        // Sample " - Alice enrolled in 2022"
        for (Student person : people) {
            System.out.printf("- %s enrolled in %s.\n", person.getName(), person.getEnrolmentYear());
        }
    }

    public static <T extends Person & Visiting> void printVisitingPeople(String title, List<T> people) {
        System.out.println("printVisitingPeople " + title);
        // Sample " - Maria is coming from Mexico"
        for (T person : people) {
            System.out.printf("- %s is coming from %s.\n", person.getName(), person.getOrigin());
        }
    }

    public static void sortList(List<? extends Person> list) {
        Collections.sort(list);
    }

    public static <T extends Person & Visiting> void sortVisitingPeople(List<T> list) {
        list.sort(new VisitingPersonComparator<>());
    }
}

class VisitingPersonComparator<T extends Person & Visiting> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        if (o1.getOrigin().equals(o1.getOrigin())){
            return o1.getName().compareTo(o2.getName());
        }
        return o1.getOrigin().compareTo(o1.getOrigin());
    }
}

public class SchoolExample {
    public static void main(String[] args) {
        // Create student
        List<Student> students = new ArrayList<>();
        students.add(new Student("Felicity", 2022));
        students.add(new Student("Alice", 2023));
        students.add(new VisitingStudent("Paula", 2022, "Argentina"));
        students.add(new VisitingStudent("Frank", 2022, "Australia"));

        List<Instructor> instructors = new ArrayList<>();
        instructors.add(new Instructor("Carol", "Programming"));
        instructors.add(new Instructor("Dave", "Signal Processing"));

        List<VisitingStudent> allVisitingStudents = new ArrayList<>();
        allVisitingStudents.add(new VisitingStudent("Maria", 2022, "Mexico"));
        allVisitingStudents.add(new VisitingStudent("Asami", 2022, "Japan"));
        allVisitingStudents.add(new VisitingStudent("Juan", 2023, "Mexico"));

        List<VisitingInstructor> allVisitingInstructors = new ArrayList<>();
        allVisitingInstructors.add(new VisitingInstructor("Antonio", "Italian", "Italy"));
        allVisitingInstructors.add(new VisitingInstructor("Heinz", "German", "Austria"));

        List<Student> allStudents = new ArrayList<>();
        allStudents.addAll(students);
        allStudents.addAll(allVisitingStudents);

        List<Person> population = new ArrayList<>();
        population.addAll(students);
        population.addAll(allVisitingStudents);
        population.addAll(instructors);
        population.addAll(allVisitingInstructors);

        // Print population
        PersonUtils.printNames("population", population);

        // Sort population
        PersonUtils.sortList(population);

        // Print sorted population
        PersonUtils.printNames("sorted population", population);

        PersonUtils.printNames("students", students);
        PersonUtils.printNames("instructors", instructors);

        // Print instruction's subjects
        PersonUtils.printSubjects("instructors", instructors);
        PersonUtils.printSubjects("allVisitingInstructors", allVisitingInstructors);

        // Print students
        PersonUtils.printStudents("students", students);
        PersonUtils.printStudents("allVisitingStudents", allVisitingStudents);
        PersonUtils.sortVisitingPeople(allVisitingStudents);
        PersonUtils.printStudents("sorted allVisitingStudents", allVisitingStudents);

        // Print visiting people
        PersonUtils.printVisitingPeople("allVisitingStudents", allVisitingStudents);
        PersonUtils.printVisitingPeople("allVisitingInstructors", allVisitingInstructors);
    }
}
