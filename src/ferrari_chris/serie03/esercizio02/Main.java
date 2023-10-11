package ferrari_chris.serie03.esercizio02;

public class Main {
    public static void main(String[] args) {
        CustomStack stack = new CustomStack();
        stack.push("Uno");
        System.out.println(stack.peek());
        stack.pop();
    }
}
