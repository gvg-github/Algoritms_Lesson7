
/*
* Гурьевских В.Г.
*
* (Task1) Написать функции, которые считывают матрицу смежности из файла и выводят ее на экран.
* (Task2) Написать рекурсивную функцию обхода графа в глубину.
* (Task3) Написать функцию обхода графа в ширину.
* */

import com.sun.activation.registries.MailcapParseException;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.util.*;

public class MainClass {
    public static void main(String[] args) {

        //Создание матрицы смежности из файла для выполнения заданий
        CreateGrafFromFile();

    }

    private static void CreateGrafFromFile() {

        String fileName = "graf.txt";

        try {
            BufferedReader rd = new BufferedReader(new FileReader(fileName));
            char[] letters = createVertexListArray();

            try {

                if (rd.ready()) {
                    rd.mark(1);
                    int size = rd.readLine().length();
                    rd.reset();

                    int[][] matrix = CreateMatrix(size, rd);

                    //Вывод матрицы смежности на экран.
                    Task1(size, matrix, letters);

                    //Написать рекурсивную функцию обхода графа в глубину.
                    Task2(size, matrix, letters);

                    //Написать функцию обхода графа в ширину.
                    Task3(size, matrix, letters);

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Вывод матрицы смежности на экран.
    private static void Task1(int size, int[][] matrix, char[] letters) {

        System.out.println("Matrix:");
        System.out.print(" ");
        for (int i = 0; i < size; i++) {
            System.out.print(" " + letters[i]);
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(letters[i]);
            for (int j = 0; j < size; j++) {
                System.out.printf("%2d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    //Массив наименований вершин
    private static char[] createVertexListArray() {
        return new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N'};
    }

    //Создание матрицы смежности, заполнение из файла
    private static int[][] CreateMatrix(int size, BufferedReader rd) throws IOException {
        int[][] matrix = new int[size][size];
        int count = 0;
        while (rd.ready()) {
            String str = rd.readLine();
            char[] arr = str.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                matrix[count][i] = Character.getNumericValue(arr[i]);
            }
            count++;
        }
        return matrix;
    }

    //Обход графа в глубину рекурсией.
    private static void Task2(int size, int[][] matrix, char[] letters) {

        MyVertex[] vertexList = new MyVertex[size];
        for (int i = 0; i < size; i++) {
            vertexList[i] = new MyVertex(letters[i], false);
        }

        System.out.println("Select start vertex from: 1 to " + size);
        Scanner in = new Scanner(System.in);
        int x = in.nextInt() - 1;

        dfs(vertexList, matrix, x, size);

    }

    private static void dfs(MyVertex[] vertexList, int[][] matrix, int x, int size) {

        MyStack s = new MyStack(size);
        vertexList[x].setVisited(true);
        s.push(x);
        int i = 0;
        System.out.println(vertexList[x].getLabel());

        while (!s.isEmpty()) {
            int current = s.peek();
            int vertex = getNext(current, size, vertexList, matrix);
            if (vertex == -1)
                s.pop();
            else {
                vertexList[vertex].setVisited(true);
                System.out.println(vertexList[vertex].getLabel());

                s.push(vertex);
            }
        }

        for (int j = 0; j < size; j++) vertexList[j].setVisited(false);
    }

    //Обход графа в ширину.
    private static void Task3(int size, int[][] matrix, char[] letters) {

        MyVertex[] vertexList = new MyVertex[size];
        for (int i = 0; i < size; i++) {
            vertexList[i] = new MyVertex(letters[i], false);
        }

        System.out.println("Select start vertex from: 1 to " + size);
        Scanner in = new Scanner(System.in);
        int x = in.nextInt() - 1;

        bfs(vertexList, matrix, x, size);

    }

    //Обход графа в ширину.
    private static void bfs(MyVertex[] vertexList, int[][] matrix, int x, int size) {

        MyQueue q = new MyQueue(size);

        vertexList[x].setVisited(true);
        q.insert(x);

        int vertex;
        System.out.println(vertexList[x].getLabel());
        while (!q.isEmpty()) {
            int current = q.pop();
            while ((vertex = getNext(current, size, vertexList, matrix)) != -1) {
                vertexList[vertex].setVisited(true);
                q.insert(vertex);
                System.out.println(vertexList[vertex].getLabel());

            }
        }

        for (int i = 0; i < size; i++) {
            vertexList[i].setVisited(false);
        }


    }

    //Обход графа в ширину.
    private static int getNext(int i, int size, MyVertex[] vertexList, int[][] matrix) {
        for (int j = 0; j < size; j++) {
            if (matrix[i][j] == 1 && vertexList[j].isVisited() == false) {
                return j;
            }
        }
        return -1;
    }


}
