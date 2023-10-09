package ferrari_chris.serie03.esercizio01.person;

public class Student extends Person {

	public static enum EducationalStage {
		PRE_SCHOOL, ELEMENTARY, MIDDLE_SCHOOL, HIGH_SCHOOL, COLLEGE
	}
	
	private EducationalStage educationalStage;
	
	public Student(String name, String surname, Gender gender, int age, final EducationalStage stage) {
		super(name, surname, gender, age);
		this.educationalStage = stage;
	}

	public EducationalStage getEducationalStage() {
		return educationalStage;
	}
	
	@Override
	public String toString() {
		return String.format("%s Student %s", super.toString(), educationalStage);
	}
}
