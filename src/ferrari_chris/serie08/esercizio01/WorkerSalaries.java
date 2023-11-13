package ferrari_chris.serie08.esercizio01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Payroll {
    private int amount;

    public Payroll(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void increaseAmount(final float factor) {
        this.amount = (int) (this.amount * factor);
    }

    @Override
    public String toString() {
        return String.format("Payroll: %6d", amount);
    }
}

class Worker {
    private String name;
    private String surname;
    private Payroll payroll;

    public Worker(String name, String surname, int salary) {
        this.name = name;
        this.surname = surname;
        this.payroll = new Payroll(salary);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Payroll getPayroll() {
        return payroll;
    }

    @Override
    public String toString() {
        return String.format("%s %s, %s", name, surname, payroll);
    }
}

public class WorkerSalaries {

    static double avgIncreasedSalaries(List<Worker> workers, final int threshold, final float factor) {
        final List<Payroll> payrollsToPromote = new ArrayList<>();

        // Find payrolls to increase
        return workers.stream().map(Worker::getPayroll).filter(payroll -> payroll.getAmount() <= threshold).mapToDouble(payroll -> {
            payroll.increaseAmount(factor);
            return payroll.getAmount();
        }).average().orElse(0);
    }

    public static void main(String[] args) {
        List<Worker> workers = init(10);

        printAll(workers);

        final int threshold = 12000;
        System.out.println(String.format("Increase by %f -> %.2f", 1.03f, avgIncreasedSalaries(workers, threshold, 1.03f)));
        printAll(workers);
        System.out.println(String.format("Increase by %f -> %.2f", 1.05f, avgIncreasedSalaries(workers, threshold, 1.05f)));
        printAll(workers);
        System.out.println(String.format("Increase by %f -> %.2f", 1.01f, avgIncreasedSalaries(workers, threshold, 1.01f)));
        printAll(workers);
    }

    // Utilities

    final static String F_NAMES[] = {"Maria", "Nancy", "Donna", "Laura", "Linda", "Susan", "Karen", "Carol", "Sarah", "Betty", "Helen"};

    final static String M_NAMES[] = {"James", "David", "Kevin", "Jason", "Brian"};

    final static String SURNAMES[] = {"Burke", "Lopez", "Perez", "White", "Jones", "Smith", "Brown",};

    private static String getRandom(final Random r, String data[]) {
        return data[r.nextInt(data.length)];
    }

    private static List<Worker> init(final int populationSize) {
        final Random random = new Random();
        return Stream
                .generate(() -> {
                    final boolean isFemale = random.nextBoolean();
                    final String name = (isFemale) ? getRandom(random, F_NAMES) : getRandom(random, M_NAMES);
                    return new Worker(name, getRandom(random, SURNAMES), 4000 + (random.nextInt(5) * 2000));
                })
                .limit(populationSize)
                .toList();
    }

    private static void printAll(List<Worker> workers) {
        System.out.println("-------------------------------");
        workers.forEach(System.out::println);
        System.out.println("-------------------------------");
    }
}
