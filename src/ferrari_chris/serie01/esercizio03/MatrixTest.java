package ferrari_chris.serie01.esercizio03;

public class MatrixTest {
    public static void Test1(){
        System.out.println("---------------------------");
        System.out.println("Testing Matrix1");
        System.out.println("---------------------------");
        Matrix<Integer> matrix = new Matrix<>(2, 2);
        matrix.set(1, 1, 1 );
        matrix.set(1, 2, 2 );
        matrix.set(2, 1, 3 );
        matrix.set(2, 2, 4 );
        matrix.get(1,1);
        matrix.get(1,2);
        matrix.get(2,1);
        matrix.get(2,2);
        System.out.println(matrix);
        System.out.println(matrix.transpose());
    }
    public static void Test2(){
        System.out.println("---------------------------");
        System.out.println("Testing Matrix2");
        System.out.println("---------------------------");
        Matrix<String> matrix = new Matrix<>(2, 2);
        matrix.set(1, 1, "a" );
        matrix.set(1, 2, "b" );
        matrix.set(2, 1, "c" );
        matrix.set(2, 2, "d" );
        matrix.get(1,1);
        matrix.get(1,2);
        matrix.get(2,1);
        matrix.get(2,2);
        System.out.println(matrix);
        System.out.println(matrix.transpose());
    }
    public static void main(String[] args) {
        Test1();
        Test2();
    }
}
