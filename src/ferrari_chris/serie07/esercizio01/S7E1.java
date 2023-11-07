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

        categorizedByOccupation.entrySet().forEach(list -> System.out.printf("%s : %d\n", list.getKey(), list.getValue().size()));

        // ------------------------------------------------------------------------------------------------------------
        // Categorize population by Age (in decades)
        // ------------------------------------------------------------------------------------------------------------

        Map<Integer, List<Person>> categorizedByAgeDecades = population.stream().collect(Collectors.groupingBy(p -> p.getAge()/10));

        System.out.println("----------------------------------");
        System.out.println("Population by decades");
        System.out.println("----------------------------------");

        categorizedByAgeDecades.entrySet().forEach(list -> System.out.println(String.format("%s : %d", list.getKey(), list.getValue().size())));

        // ------------------------------------------------------------------------------------------------------------
        // Search for people working in secondary sector having salary between 50k - 80k
        // ------------------------------------------------------------------------------------------------------------

        // TODO replace for iteration and evaluate logic using only stream operations and lambda functions

        List<Person> secondaryWorkers = population.stream().filter(person -> {
            if (person instanceof Worker){
                final Worker worker = (Worker) person;
                return worker.getWorkSector() == Worker.WorkSectorType.SECONDARY &&
                        worker.getSalary() >= 50_000 &&
                        worker.getSalary() <= 80_000;
            }
            return false;
        }).toList();

        System.out.println("----------------------------------");
        System.out.printf("Secondary sector workers with salary (50k - 80k) [%d elements]%n", secondaryWorkers.size());
        System.out.println("----------------------------------");

        secondaryWorkers.stream().forEach(person -> System.out.print(person + ", "));
        System.out.println();

        // ------------------------------------------------------------------------------------------------------------
        // Search for high school students
        // ------------------------------------------------------------------------------------------------------------

        // TODO replace for iteration and evaluate logic using only stream operations and lambda functions
        List<Person> highSchoolStudents = population.stream().filter(person -> person instanceof Student student &&
                student.getEducationalStage() == Student.EducationalStage.HIGH_SCHOOL).toList();

        System.out.println("----------------------------------");
        System.out.printf("HighSchool students [%d elements]%n", highSchoolStudents.size());
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
