package ferrari_chris.serie03.esercizio01.operations;

import ferrari_chris.serie03.esercizio01.person.Person;
import ferrari_chris.serie03.esercizio01.person.Student;

public class EvaluateHighschoolStudent implements EvaluateOperation {

	@Override
	public boolean evaluate(Person p) {
		return p instanceof Student &&
				((Student) p).getEducationalStage() == Student.EducationalStage.HIGH_SCHOOL;
	}
}
		
