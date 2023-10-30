package ferrari_chris.serie06.esercizio01.person;

import ferrari_chris.serie06.esercizio01.person.Student.EducationalStage;
import ferrari_chris.serie06.esercizio01.person.Worker.WorkSectorType;

import java.util.Random;

public class PersonFactory {

    final static String F_NAMES[] = {"Mary", "Jennifer", "Lisa", "Sandra", "Michelle", "Patricia", "Maria", "Nancy",
            "Donna", "Laura", "Linda", "Susan", "Karen", "Carol", "Sarah", "Barbara", "Margaret", "Betty", "Ruth",
            "Kimberly", "Elizabeth", "Dorothy", "Helen", "Sharon", "Deborah"};

    final static String M_NAMES[] = {"James", "David", "Christopher", "George",
            "Ronald", "John", "Richard", "Daniel", "Kenneth", "Anthony", "Robert", "Charles", "Paul", "Steven", "Kevin",
            "Michael", "Joseph", "Mark", "Edward", "Jason", "William", "Thomas", "Donald", "Brian", "Jeff",};

    final static String SURNAMES[] = {"Jones", "Smith", "Garcia", "Edwards", "Brown", "Thompson", "Sullivan",
            "Rodriguez", "Williams", "Johnson", "Martinez", "Gregory", "Wilson", "Burke", "Hayden", "Lopez", "Wilkins",
            "Mullin", "Lee", "Campbell", "King", "White", "Perez", "Wang", "Park",};

    private static String getRandom(String data[]) {
        final Random r = new Random();
        return data[r.nextInt(data.length)];
    }

    private static int getRandomSalary(int min, int max) {
        final Random r = new Random();
        return (min + r.nextInt(max - min)) * 1000;
    }

    public static Person createRandomPerson() {
        final Random random = new Random();

        final boolean isFemale = random.nextBoolean();
        final String name = (isFemale) ? getRandom(F_NAMES) : getRandom(M_NAMES);
        final Person.Gender gender = (isFemale) ? Person.Gender.F : Person.Gender.M;

        final int age = random.nextInt(80) + 4;
        // Students
        if (age < 6) {
            return new Student(name, getRandom(SURNAMES), gender, age, EducationalStage.PRE_SCHOOL);
        } else if (age < 11) {
            return new Student(name, getRandom(SURNAMES), gender, age, EducationalStage.ELEMENTARY);
        } else if (age < 15) {
            return new Student(name, getRandom(SURNAMES), gender, age, EducationalStage.MIDDLE_SCHOOL);
        } else if (age < 19) {
            if (random.nextBoolean())
                return new Worker(name, getRandom(SURNAMES), gender, age, WorkSectorType.PRIMARY, getRandomSalary(3000, 12000));
            return new Student(name, getRandom(SURNAMES), gender, age, EducationalStage.HIGH_SCHOOL);
        } else if (age < 26) {
            if (random.nextBoolean())
                return new Worker(name, getRandom(SURNAMES), gender, age, WorkSectorType.PRIMARY, getRandomSalary(3000, 12000));
            return new Student(name, getRandom(SURNAMES), gender, age, EducationalStage.COLLEGE);
        } else if (age < 65) {
            // Workers
            final WorkSectorType workSector = WorkSectorType.random();
            switch (workSector) {
                case PRIMARY:
                    return new Worker(name, getRandom(SURNAMES), gender, age, workSector, getRandomSalary(3, 30));
                case SECONDARY:
                    return new Worker(name, getRandom(SURNAMES), gender, age, workSector, getRandomSalary(25, 100));
                case TERTIARY:
                default: // fall-through
                    return new Worker(name, getRandom(SURNAMES), gender, age, workSector, getRandomSalary(25, 2_000));
            }
        }
        // Retired
        return new Person(name, getRandom(SURNAMES), gender, age);
    }
}
