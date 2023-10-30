package ferrari_chris.serie06.esercizio01;

import ferrari_chris.serie06.esercizio01.person.Person;
import ferrari_chris.serie06.esercizio01.person.PersonFactory;
import ferrari_chris.serie06.esercizio01.person.Student;
import ferrari_chris.serie06.esercizio01.person.Worker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S6E1 {

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

    static List<Person> search(List<Person> population, EvaluateOperation op){
        List<Person> newPopulation = new ArrayList<>();
        for (Person person : population) {
            if (op.evaluate(person))
                newPopulation.add(person);
        }
        return newPopulation;
    }
    static Map<String, List<Person>> categorizeString(List<Person> population, CategorizeOperation<String> op){
        Map<String, List<Person>> categorizedList = new HashMap<>();
        for (Person person : population) {
            String category = op.getCategory(person);
            if (!categorizedList.containsKey(category))
                categorizedList.put(category, new ArrayList<>());
            categorizedList.get(category).add(person);
        }
        return categorizedList;
    }
    static Map<Integer, List<Person>> categorizeInteger(List<Person> population, CategorizeOperation<Integer> op){
        Map<Integer, List<Person>> categorizedList = new HashMap<>();
        for (Person person : population) {
            Integer category = op.getCategory(person);
            if (!categorizedList.containsKey(category))
                categorizedList.put(category, new ArrayList<>());
            categorizedList.get(category).add(person);
        }
        return categorizedList;
    }
    public static void main(String[] args) {
        List<Person> population = init(100);

        // Categorize population by surname
        Map<String, List<Person>> categorizedBySurname = categorizeString(population, Person::getSurname);

/*
        final CategorizeOperation<String> categorizeBySurnameOperation = new CategorizeOperation<String>() {
            @Override
            public String getCategory(Person p) {
                return p.getSurname();
            }
        };

        Map<String, List<Person>> categorizedBySurname = new HashMap<>();
        for (Person person : population) {
            String category = categorizeBySurnameOperation.getCategory(person);
            if (categorizedBySurname.containsKey(category) == false)
                categorizedBySurname.put(category, new ArrayList<>());
            categorizedBySurname.get(category).add(person);
        }

*/

        // Categorize population by Occupation
        Map<String, List<Person>> categorizedByOccupation = categorizeString(population, person -> {
            if (person instanceof Student)
                return ((Student) person).getEducationalStage().toString();
            if (person instanceof Worker)
                return ((Worker) person).getWorkSector().toString();
            return "Retired";
        });
        /*
        final CategorizeOperation<String> categorizeByOccupationOperation = new CategorizeOperation<String>() {
            @Override
            public String getCategory(Person p) {
                if (p instanceof Student)
                    return ((Student) p).getEducationalStage().toString();
                if (p instanceof Worker)
                    return ((Worker) p).getWorkSector().toString();
                return "RETIRED";
            }
        };
        Map<String, List<Person>> categorizedByOccupation = new HashMap<>();
        for (Person person : population) {
            String category = categorizeByOccupationOperation.getCategory(person);
            if (categorizedByOccupation.containsKey(category) == false)
                categorizedByOccupation.put(category, new ArrayList<>());
            categorizedByOccupation.get(category).add(person);
        }
         */

        // Categorize population by Age (in decades)
        Map<Integer, List<Person>> categorizedByAgeDecades = categorizeInteger(population, person -> person.getAge() / 10);
        /*
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
        */
        // Search for people working in secondary sector having salary between 50k - 80k
        List<Person> secondaryWorkers = search(population, (Person p) -> {
            if (!(p instanceof Worker))
                return false;
            final Worker worker = (Worker) p;
            return worker.getWorkSector() == Worker.WorkSectorType.SECONDARY &&
                    worker.getSalary() >= 50_000 &&
                    worker.getSalary() <= 80_000;
        });
        /*
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
        */


        // Search for high school students
        List<Person> highSchoolStudents = search(population, (Person p) -> {
            return p instanceof Student student &&
                    student.getEducationalStage() == Student.EducationalStage.HIGH_SCHOOL;
        });
        /*

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
        */
        // Print results
        printCategorizedString("Population by surname", categorizedBySurname);
        printCategorizedString("Population by occupation", categorizedByOccupation);
        printCategorizedInteger("Population by decades", categorizedByAgeDecades);
        printPersonList("HighSchool students", highSchoolStudents);
        printPersonList("Secondary sector workers with salary (50k - 80k)", secondaryWorkers);
    }

    // Utility methods

    /**
     * Initializes a list of random persons with the given population size.
     *
     * @param populationSize The number of persons to generate.
     * @return A list of randomly generated persons.
     */
    private static List<Person> init(int populationSize) {
        // Generate random dataset
        List<Person> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++)
            population.add(PersonFactory.createRandomPerson());

        // Print all persons
        for (Person p : population)
            System.out.println(p);

        return population;
    }

    /**
     * Prints a categorized list of persons by their category.
     *
     * @param title       The title for the categorized list.
     * @param categorized A map of categorized lists of persons.
     */
    private static void printCategorizedString(String title, Map<String, List<Person>> categorized) {
        System.out.println("----------------------------------");
        System.out.println(title);
        System.out.println("----------------------------------");
        for (String category : categorized.keySet())
            System.out.println(String.format("%s : %d", category, categorized.get(category).size()));
    }

    /**
     * Prints a categorized list of persons by their category.
     *
     * @param title       The title for the categorized list.
     * @param categorized A map of categorized lists of persons.
     */
    private static void printCategorizedInteger(String title, Map<Integer, List<Person>> categorized) {
        System.out.println("----------------------------------");
        System.out.println(title);
        System.out.println("----------------------------------");
        for (Integer category : categorized.keySet())
            System.out.println(String.format("%d : %d", category, categorized.get(category).size()));
    }

    /**
     * Prints a list of persons.
     *
     * @param title  The title for the list.
     * @param people The list of persons to be printed.
     */
    private static void printPersonList(String title, List<Person> people) {
        System.out.println("----------------------------------");
        System.out.println(String.format("%s [%d elements]", title, people.size()));
        System.out.println("----------------------------------");
        for (Person p : people)
            System.out.print(p + ", ");
        System.out.println();
    }
}
