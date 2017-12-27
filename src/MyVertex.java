public class MyVertex {
    private char label;
    private boolean isVisited;

    public MyVertex(char label, boolean isVisited) {
        this.label = label;
        this.isVisited = isVisited;
    }

    public char getLabel() {
        return label;
    }

    public void setLabel(char label) {
        this.label = label;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }
}

