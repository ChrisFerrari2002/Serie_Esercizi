package ferrari_chris.serie01.esercizio02;

public class MyQueue<V>{
    private Node<V> firstNode;
    private Node<V> lastNode;
    private int count;

    public MyQueue() {
    }

    public boolean add(V value){
        Node<V> newNode = new Node<>(null, value);
        if (count == 0) {
            firstNode = newNode;
            lastNode = firstNode;
            count++;
            return true;
        }
        if (count+1 < 0)
            return false; // check per vedere se si supera il num max di int
        
        lastNode.setNextNode(newNode);
        lastNode = newNode;
        count++;
        return true;
    }
    public boolean remove(){
        if(count < 1)
            return false;
        if (count == 1){
            count--;
            return true;
        }
        setFirstNode(firstNode.getNextNode());
        count--;
        return true;
    }
    private void setFirstNode(Node<V> firstNode) {
        this.firstNode = firstNode;
    }

    @Override
    public String toString(){
        if (count == 0)
            return "0 Nodes found.";

        StringBuilder elements = new StringBuilder();
        Node<V> currentNode = firstNode;
        for (int i = 0; i < count; i++) {
            elements.append(currentNode.toString()).append(" ");
            currentNode = currentNode.getNextNode();
        }
        return elements.toString();
    }
}
