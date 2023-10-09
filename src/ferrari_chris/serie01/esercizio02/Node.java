package ferrari_chris.serie01.esercizio02;

public class Node <V> {
    private Node<V> nextNode;
    private final V value;

    public Node(Node<V> nextNode, V value) {
        this.nextNode = nextNode;
        this.value = value;
    }

    public Node<V> getNextNode() {

        return nextNode;
    }

    public void setNextNode(Node<V> nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
