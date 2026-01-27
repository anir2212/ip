public class Todo extends Task{

    public Todo(int num, String s, boolean b) {
        super(num, s, b);
    }

    public String toString() {
        return "[T]" + super.toString();

    }
}
