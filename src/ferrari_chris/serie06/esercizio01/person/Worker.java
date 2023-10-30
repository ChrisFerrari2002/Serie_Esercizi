package ferrari_chris.serie06.esercizio01.person;

import java.util.Random;

public class Worker extends Person {

    public enum WorkSectorType {
        PRIMARY, SECONDARY, TERTIARY;

        public static WorkSectorType random() {
            final Random r = new Random();
            return WorkSectorType.values()[r.nextInt(WorkSectorType.values().length)];
        }
    }

    private WorkSectorType workSector;

    private int salary;

    public Worker(String name, String surname, Gender gender, int age, WorkSectorType sector, int salary) {
        super(name, surname, gender, age);
        this.workSector = sector;
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public WorkSectorType getWorkSector() {
        return workSector;
    }

    @Override
    public String toString() {
        return String.format("%s Worker sector=%s salary=%d", super.toString(), workSector, salary);
    }
}
