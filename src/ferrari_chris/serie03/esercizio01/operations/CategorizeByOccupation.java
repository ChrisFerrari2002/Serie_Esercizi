package ferrari_chris.serie03.esercizio01.operations;

import ferrari_chris.serie03.esercizio01.person.Person;
import ferrari_chris.serie03.esercizio01.person.Student;
import ferrari_chris.serie03.esercizio01.person.Worker;

public class CategorizeByOccupation implements CategorizeOperation<String> {

	@Override
	public String getCategory(Person p) {
		if (p instanceof Student)
			return ((Student) p).getEducationalStage().toString();
		if (p instanceof Worker)
			return ((Worker) p).getWorkSector().toString();
		return "RETIRED";
	}
}
