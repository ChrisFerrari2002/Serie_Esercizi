package ferrari_chris.serie01.esercizio01;

class Tuple {
	final private Object first;
	final private Object second;
	
	public Tuple(Object first, Object second) {
		this.first = first;
		this.second = second;
	}

	public Object getFirst() {
		return first;
	}

	public Object getSecond() {
		return second;
	}
	
	public Tuple swap() {
		return new Tuple(second, first);
	}
	
	@Override
	public String toString() {
		return String.format("Tuple(%s, %s)", first.toString(), second.toString());
	}
}
class Tuple1<T> {
	final private T first;
	final private T second;

	public Tuple1(T first, T second) {
		this.first = first;
		this.second = second;
	}

	public T getFirst() {
		return first;
	}

	public T getSecond() {
		return second;
	}

	public Tuple1<T> swap() {
		return new Tuple1<T>(second, first);
	}

	@Override
	public String toString() {
		return String.format("Tuple(%s, %s)", first.toString(), second.toString());
	}
}
class Tuple2<T1, T2> {
	final private T1 first;
	final private T2 second;

	public Tuple2(T1 first, T2 second) {
		this.first = first;
		this.second = second;
	}

	public T1 getFirst() {
		return first;
	}

	public T2 getSecond() {
		return second;
	}

	public Tuple2<T2, T1> swap() {
		return new Tuple2<>(second, first);
	}

	@Override
	public String toString() {
		return String.format("Tuple(%s, %s)", first.toString(), second.toString());
	}
}
public class S1Es1 {
	
	private static void testTupleClass() {
		System.out.println("---------------------------");
		System.out.println("Testing Tuple class");
		System.out.println("---------------------------");
		Tuple test1 = new Tuple("First Value", "Second Value");
		System.out.println(test1);
		System.out.println(test1.swap());

		Tuple test2 = new Tuple(new Tuple(1,2), new Tuple(3,4));
		System.out.println(test2);
		System.out.println(new Tuple(
				((Tuple) test2.getFirst()).swap(), 
				((Tuple) test2.getSecond()).swap()
			).swap());

		Tuple test3 = new Tuple("Sample Value", 3);
		System.out.println(test3);
		System.out.println(test3.swap());
	}
	
	private static void testTuple1Class() {
		System.out.println("---------------------------");
		System.out.println("Testing Tuple1 class");
		System.out.println("---------------------------");
		Tuple1<String> test1 = new Tuple1<>("First Value", "Second Value");
		System.out.println(test1);
		System.out.println(test1.swap());

		Tuple1<Tuple1<Integer>> test2 = new Tuple1<>(new Tuple1<>(1,2), new Tuple1<>(3,4));
		System.out.println(test2);
		System.out.println(new Tuple1<>(
				(test2.getFirst()).swap(),
				(test2.getSecond()).swap()
		).swap());

		Tuple1<Object> test3 = new Tuple1<>("Sample Value", 3);
		System.out.println(test3);
		System.out.println(test3.swap());
	}
	
	private static void testTuple2Class() {
		System.out.println("---------------------------");
		System.out.println("Testing Tuple2 class");
		System.out.println("---------------------------");
		Tuple2<String, String> test1 = new Tuple2<>("First Value", "Second Value");
		System.out.println(test1);
		System.out.println(test1.swap());

		Tuple2<Tuple2<Integer, Integer>, Tuple2<Integer, Integer>> test2 = new Tuple2<>(new Tuple2<>(1,2), new Tuple2<>(3,4));
		System.out.println(test2);
		System.out.println(new Tuple2<>(
				(test2.getFirst()).swap(),
				(test2.getSecond()).swap()
		).swap());

		Tuple2<String, Integer> test3 = new Tuple2<>("Sample Value", 3);
		System.out.println(test3);
		System.out.println(test3.swap());
	}
	
	public static void main(String[] args) {
		testTupleClass();

		testTuple1Class();

		testTuple2Class();
	}
}
