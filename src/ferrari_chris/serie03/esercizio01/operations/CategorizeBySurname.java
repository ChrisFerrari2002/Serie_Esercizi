package ferrari_chris.serie03.esercizio01.operations;

import ferrari_chris.serie03.esercizio01.person.Person;

public class CategorizeBySurname implements CategorizeOperation<String> {

	@Override
	public String getCategory(Person p) {
		return p.getSurname();
	}
}
