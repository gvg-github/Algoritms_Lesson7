public class MyStack {

    private int[] array;
    private int size;
    private int top;

    public MyStack(int queueSize) {
        size = queueSize;
        array = new int[size];
        top = -1;
    }

    public void push(int value) {
        array[++top] = value;
    }

    public int pop() {
        return array[top--];
    }

    public int peek() {
        return array[top];
    }

//    public int size() {
//        return count;
//    }

    public boolean isEmpty() {
        return (top == -1);
    }
}

