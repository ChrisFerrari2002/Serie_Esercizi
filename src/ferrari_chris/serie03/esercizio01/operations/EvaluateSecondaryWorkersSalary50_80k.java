package ferrari_chris.serie03.esercizio01.operations;

import ferrari_chris.serie03.esercizio01.person.Worker;
import ferrari_chris.serie03.esercizio01.person.Person;

public class EvaluateSecondaryWorkersSalary50_80k implements EvaluateOperation {

	@Override
	public boolean evaluate(Person p) {
		if (!(p instanceof Worker))
			return false;
		final Worker worker = (Worker) p;
		return worker.getWorkSector() == Worker.WorkSectorType.SECONDARY && 
				worker.getSalary() >= 50_000 && 
				worker.getSalary() <= 80_000;
	}
}
		
