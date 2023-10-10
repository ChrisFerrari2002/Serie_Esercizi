package ferrari_chris.serie03.esercizio01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ferrari_chris.serie03.esercizio01.person.Person;
import ferrari_chris.serie03.esercizio01.person.PersonFactory;
import ferrari_chris.serie03.esercizio01.person.Student;
import ferrari_chris.serie03.esercizio01.person.Worker;

public class S3E1V3 {


	public static void main(String[] args) {
		interface CategorizeOperation<K> {

			/**
			 * Return the category for the person {@param p}
			 * @param p
			 * @return
			 */
			K getCategory(Person p);
		}
		interface EvaluateOperation {
			/**
			 *
			 * @param p
			 * @return
			 */
			boolean evaluate(Person p);
		}
		List<Person> population = init(100);
		
		CategorizeOperation<String> stringCategorizationOperation = new CategorizeOperation<String>() {
			@Override
			public String getCategory(Person p) {
				return p.getSurname();
			}
		};
		
		// Categorize population by surname
		Map<String, List<Person>> categorizedBySurname = new HashMap<>();
		for (Person p : population) {
			String category = stringCategorizationOperation.getCategory(p);
			if (categorizedBySurname.containsKey(category) == false)
				categorizedBySurname.put(category, new ArrayList<>());
			categorizedBySurname.get(category).add(p);
		}
		
		// Categorize population by Occupation
		stringCategorizationOperation = new CategorizeOperation<String>() {
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
		for (Person p : population) {
			String category = stringCategorizationOperation.getCategory(p);
			if (categorizedByOccupation.containsKey(category) == false)
				categorizedByOccupation.put(category, new ArrayList<>());
			categorizedByOccupation.get(category).add(p);
		}

		// Categorize population by Age (in decades)
		CategorizeOperation<Integer> integerCategorizationOperation = new CategorizeOperation<Integer>() {
			@Override
			public Integer getCategory(Person p) {
				return p.getAge() / 10;
			}
		};
		Map<Integer, List<Person>> categorizedByAgeDecades = new HashMap<>();
		for (Person p : population) {
			Integer category = integerCategorizationOperation.getCategory(p);
			if (categorizedByAgeDecades.containsKey(category) == false)
				categorizedByAgeDecades.put(category, new ArrayList<>());
			categorizedByAgeDecades.get(category).add(p);
		}
		
		// Search for people working in secondary sector having salary between 50k - 80k 
		EvaluateOperation eval = new EvaluateOperation() {
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
		for (Person p : population) {
			if (eval.evaluate(p))
				secondaryWorkers.add(p);
		}
	
		// Search for high school students
		eval = new EvaluateOperation() {
			@Override
			public boolean evaluate(Person p) {
				return p instanceof Student &&
						((Student) p).getEducationalStage() == Student.EducationalStage.HIGH_SCHOOL;
			}
		};
		List<Person> highSchoolStudents = new ArrayList<>();
		for (Person p : population) {
			if (eval.evaluate(p))
				highSchoolStudents.add(p);
		}

		
		// Print results
		printCategorizedString("Population by surname", categorizedBySurname);
		printCategorizedString("Population by occupation", categorizedByOccupation);
		printCategorizedInteger("Population by decades", categorizedByAgeDecades);
		printPersonList("HighSchool students", highSchoolStudents);
		printPersonList("Secondary sector workers with salary (50k - 80k)", secondaryWorkers);
	}
	
	// Utility methods
	
	// Generate random dataset
	private static List<Person> init(int populationSize) {
		// Generate random dataset
		List<Person> population = new ArrayList<>();
		for (int i = 0; i < 100; i++)
			population.add(PersonFactory.createRandomPerson());
		
		// Print all persons
		for (Person p : population)
			System.out.println(p);

		return population;
	}
	
	private static void printCategorizedString(String title, Map<String, List<Person>> categorized) {
		System.out.println("----------------------------------");
		System.out.println(title);
		System.out.println("----------------------------------");
		for (String surname : categorized.keySet())
			System.out.println(String.format("%s : %d", surname, categorized.get(surname).size()));
	}
	
	private static void printCategorizedInteger(String title, Map<Integer, List<Person>> categorized) {
		System.out.println("----------------------------------");
		System.out.println(title);
		System.out.println("----------------------------------");
		for (Integer category : categorized.keySet())
			System.out.println(String.format("%d : %d", category, categorized.get(category).size()));
	}
	
	private static void printPersonList(String title, List<Person> people) {
		System.out.println("----------------------------------");
		System.out.println(String.format("%s [%d]", title, people.size()));
		System.out.println("----------------------------------");
		for (Person p : people)
			System.out.print(p + ", ");
		System.out.println();
	}
	
}
