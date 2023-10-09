package ferrari_chris.serie01.esercizio02;

public class MyQueueMain<V> {
    public static <V> void addNode(MyQueue<V> queue, V value){
        if (queue.add(value)) {
            System.out.println("Node added!");
        } else {
            System.out.println("Error! Node not added!");
        }
    }
    public static <V> void removeNode(MyQueue<V> queue){
        if (queue.remove()) {
            System.out.println("Node removed!");
        } else {
            System.out.println("Error! Node not removed!");
        }
    }
    public static void TestQueue1(){
        System.out.println("----------------------------");
        System.out.println("Testing Queue1");
        System.out.println("----------------------------");
        MyQueue<Integer> queue = new MyQueue<>();
        System.out.println("Adding 5 nodes");
        addNode(queue, 1);
        addNode(queue, 2);
        addNode(queue, 3);
        addNode(queue, 4);
        addNode(queue, 5);
        System.out.println(queue);
        System.out.println("Removing 2 nodes");
        removeNode(queue);
        removeNode(queue);
        System.out.println(queue);
    }
    public static void TestQueue2(){
        System.out.println("---------------------------");
        System.out.println("Testing Queue2");
        System.out.println("---------------------------");
        MyQueue<String> queue = new MyQueue<>();
        System.out.println("Adding 5 nodes");
        addNode(queue, "Value1");
        addNode(queue, "Value2");
        addNode(queue, "Value3");
        addNode(queue, "Value4");
        addNode(queue, "Value5");
        System.out.println(queue);
        System.out.println("Removing 2 nodes");
        removeNode(queue);
        removeNode(queue);
        System.out.println(queue);
    }
    public static void main(String[] args) {
        TestQueue1();
        TestQueue2();
    }
}
