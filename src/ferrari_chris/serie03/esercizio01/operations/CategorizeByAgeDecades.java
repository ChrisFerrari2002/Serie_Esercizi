package ferrari_chris.serie03.esercizio01.operations;

import ferrari_chris.serie03.esercizio01.person.Person;

public class CategorizeByAgeDecades implements CategorizeOperation<Integer> {

	@Override
	public Integer getCategory(Person p) {
		return p.getAge() / 10;
	}
}
