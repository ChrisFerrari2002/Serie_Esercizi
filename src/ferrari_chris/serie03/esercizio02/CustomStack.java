package ferrari_chris.serie03.esercizio02;

public class CustomStack implements AdvancedStack{
    private StackElement firstElement;
    private int size = 0;
    private class StackElement{
        private StackElement nextElement;
        private final Object value;

        public StackElement(Object value) {
            this.value = value;
        }

        public void setNextElement(StackElement nextElement) {
            this.nextElement = nextElement;
        }

        public Object getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Value: " + value;
        }
    }

    @Override
    public void push(Object o) {
        StackElement newElement =  new StackElement(o);
        if (firstElement == null){
            firstElement = newElement;
            size++;
            return;
        }
        newElement.setNextElement(firstElement);
        firstElement = newElement;
        size++;
    }

    @Override
    public Object pop() {
        if (size == 0){
            System.out.println("Stack empty.");
            return null;
        }
        StackElement deletedElement = firstElement;
        firstElement = firstElement.nextElement;
        size--;
        return deletedElement;
    }

    @Override
    public Object peek() {
        if (size == 0){
            System.out.println("Stack empty.");
            return null;
        }
        return firstElement;
    }

    @Override
    public long size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public long count(Evaluate testLogic) {
        long cnt = 0L;
        StackElement currentElement = firstElement;
        for (int i = 0; i < size; i++) {
            if (testLogic.verify(currentElement.getValue())){
                cnt++;
            }
            currentElement = currentElement.nextElement;
        }
        return cnt;
    }

    @Override
    public String toString() {
        StackElement currentElement = firstElement;
        StringBuilder value = new StringBuilder();
        for (int i = size; i > 0; i--) {
            value.append("Element ").append(i).append(": ").append(currentElement.getValue().toString()).append("\n");
            currentElement = currentElement.nextElement;
        }
        return value.toString();
    }
}
