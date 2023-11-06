package ferrari_chris.serie07.esercizio01;

import ferrari_chris.serie07.esercizio01.person.Person;
import ferrari_chris.serie07.esercizio01.person.PersonFactory;
import ferrari_chris.serie07.esercizio01.person.Student;
import ferrari_chris.serie07.esercizio01.person.Worker;

import java.util.*;
import java.util.stream.Collectors;

public class S7E1 {

    /**
     * An interface that defines a method to categorize a person.
     *
     * @param <K> The type of category to assign to the person.
     */
    private interface CategorizeOperation<K> {

        /**
         * Categorizes a person into a specific category.
         *
         * @param person The person to categorize.
         * @return The category assigned to the person.
         */
        K getCategory(Person person);
    }

    /**
     * An interface that defines a method to evaluate a person.
     */
    private interface EvaluateOperation {

        /**
         * Evaluates whether a person meets certain criteria.
         *
         * @param person The person to evaluate.
         * @return {@code true} if the person meets the criteria, {@code false} otherwise.
         */
        boolean evaluate(Person person);
    }

    public static void main(String[] args) {
        List<Person> population = init(100);

        // ------------------------------------------------------------------------------------------------------------
        // All surnames in descending order
        // ------------------------------------------------------------------------------------------------------------


        List<String> allSurnames = population.stream().map(Person::getSurname).toList();
        List<String> uniqueSurnames = allSurnames.stream().distinct().sorted(Comparator.reverseOrder()).toList();


        System.out.println("----------------------------------");
        System.out.println("Descending surnames");
        System.out.println("----------------------------------");

        System.out.println(uniqueSurnames.stream().collect(Collectors.joining("\n")));


        // ------------------------------------------------------------------------------------------------------------
        // Categorize population by surname
        // ------------------------------------------------------------------------------------------------------------


        Map<String, List<Person>> categorizedBySurname = population.stream().collect(Collectors.groupingBy(Person::getSurname));

        System.out.println("----------------------------------");
        System.out.println("Population by surname");
        System.out.println("----------------------------------");

        categorizedBySurname.entrySet().stream().forEach(list -> System.out.printf("%s : %d\n", list.getKey(), list.getValue().size()));


        // ------------------------------------------------------------------------------------------------------------
        // Categorize population by Occupation
        // ------------------------------------------------------------------------------------------------------------

        // TODO replace for iteration and categorize logic using only stream operations and lambda functions

        Map<String, List<Person>> categorizedByOccupation = population.stream().collect(Collectors.groupingBy(person -> {
            if (person instanceof Student)
                return ((Student) person).getEducationalStage().toString();
            if (person instanceof Worker)
                return ((Worker) person).getWorkSector().toString();
            return "RETIRED";
        }));

        System.out.println("----------------------------------");
        System.out.println("Population by occupation");
        System.out.println("----------------------------------");

        // TODO replace iteration and output generation using only stream operation and lambda functions
        categorizedByOccupation.entrySet().forEach(list -> System.out.printf("%s : %d\n", list.getKey(), list.getValue().size()));;

        // ------------------------------------------------------------------------------------------------------------
        // Categorize population by Age (in decades)
        // ------------------------------------------------------------------------------------------------------------

        // TODO replace for iteration and categorize logic using only stream operations and lambda functions
        final CategorizeOperation<Integer> categorizeByAgeOperation = new CategorizeOperation<Integer>() {
            @Override
            public Integer getCategory(Person p) {
                return p.getAge() / 10;
            }
        };
        Map<Integer, List<Person>> categorizedByAgeDecades = new HashMap<>();
        for (Person person : population) {
            Integer category = categorizeByAgeOperation.getCategory(person);
            if (categorizedByAgeDecades.containsKey(category) == false)
                categorizedByAgeDecades.put(category, new ArrayList<>());
            categorizedByAgeDecades.get(category).add(person);
        }

        System.out.println("----------------------------------");
        System.out.println("Population by decades");
        System.out.println("----------------------------------");

        // TODO replace iteration and output generation using only stream operation and lambda functions
        for (Integer category : categorizedByAgeDecades.keySet())
            System.out.println(String.format("%d : %d", category, categorizedByAgeDecades.get(category).size()));


        // ------------------------------------------------------------------------------------------------------------
        // Search for people working in secondary sector having salary between 50k - 80k
        // ------------------------------------------------------------------------------------------------------------

        // TODO replace for iteration and evaluate logic using only stream operations and lambda functions
        final EvaluateOperation evaluateSecondarySalary50k_80kOperation = new EvaluateOperation() {
            @Override
            public boolean evaluate(Person p) {
                if (!(p instanceof Worker))
                    return false;
                final Worker worker = (Worker) p;
                return worker.getWorkSector() == Worker.WorkSectorType.SECONDARY &&
                        worker.getSalary() >= 50_000 &&
                        worker.getSalary() <= 80_000;
            }
        };
        List<Person> secondaryWorkers = new ArrayList<>();
        for (Person person : population) {
            if (evaluateSecondarySalary50k_80kOperation.evaluate(person))
                secondaryWorkers.add(person);
        }

        System.out.println("----------------------------------");
        System.out.println(String.format("Secondary sector workers with salary (50k - 80k) [%d elements]", secondaryWorkers.size()));
        System.out.println("----------------------------------");

        // TODO replace iteration and output generation using only stream operation and lambda functions
        for (Person p : secondaryWorkers)
            System.out.print(p + ", ");
        System.out.println();


        // ------------------------------------------------------------------------------------------------------------
        // Search for high school students
        // ------------------------------------------------------------------------------------------------------------

        // TODO replace for iteration and evaluate logic using only stream operations and lambda functions
        final EvaluateOperation evaluateHighSchoolStudentOperation = new EvaluateOperation() {
            @Override
            public boolean evaluate(Person person) {
                return person instanceof Student student &&
                        student.getEducationalStage() == Student.EducationalStage.HIGH_SCHOOL;
            }
        };
        List<Person> highSchoolStudents = new ArrayList<>();
        for (Person person : population) {
            if (evaluateHighSchoolStudentOperation.evaluate(person))
                highSchoolStudents.add(person);
        }

        System.out.println("----------------------------------");
        System.out.println(String.format("HighSchool students [%d elements]", highSchoolStudents.size()));
        System.out.println("----------------------------------");

        // TODO replace iteration and output generation using only stream operation and lambda functions
        for (Person p : highSchoolStudents)
            System.out.print(p + ", ");
        System.out.println();
    }

    // Utility methods

    /**
     * Initializes a list of random persons with the given population size.
     *
     * @param populationSize The number of persons to generate.
     * @return A list of randomly generated persons.
     */
    private static List<Person> init(int populationSize) {
        // REMARK: no need to transform using stream operations!
        // Generate random dataset
        List<Person> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++)
            population.add(PersonFactory.createRandomPerson());

        // Print all persons
        for (Person p : population)
            System.out.println(p);

        return population;
    }
}
