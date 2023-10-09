package ferrari_chris.serie01.esercizio03;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Matrix<E>{
    private final Object[][] array;
    private final int rows;
    private final int columns;

    public Matrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0){
            rows = 5;
            columns = 5;
        }
        this.array = new Object[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Object[][] getArray() {
        return array;
    }

    public void set(int row, int col, E value){
        if (row <= 0 || col <= 0 || row > rows || col > columns){
            System.out.println("The matrix is smaller. Value " + value + " not inserted.");
            return;
        }
        array[row-1][col-1] = value;
    }
    public E get(int row, int col){
        if (row <= 0 || col <= 0 || row > rows || col > columns){
            System.out.println("The matrix is smaller. Value impossible to retrieve.");
            return null;
        }
        if (array[row-1][col-1] == null){
            System.out.println("There is nothing in this position.");
        }
        @SuppressWarnings("unchecked")
        E val = (E) array[row-1][col-1];
        System.out.println("Value in row " + row + " and column " + col + " = " + val);
        return val;
    }
    public Matrix<E> transpose(){
        Matrix<E> transpMatrix = new Matrix<>(getColumns(), getRows());
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                transpMatrix.getArray()[i][j] = array[j][i];
            }
        }
        return transpMatrix;
    }

    @Override
    public String toString() {
        StringBuilder value = new StringBuilder();
        for (Object[] rows : array){
            value.append(Arrays.toString(rows)).append("\n");
        }
        return value.toString();
    }
}
