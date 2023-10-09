package ferrari_chris.serie03.esercizio01.operations;

import ferrari_chris.serie03.esercizio01.person.Person;

public interface CategorizeOperation<K> {
	
	/**
	 * Return the category for the person {@param p}
	 * @param p
	 * @return
	 */
	K getCategory(Person p);
}
