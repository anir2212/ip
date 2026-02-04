package ani;

public class Deadlines extends Task{
    private String date;
    Deadlines(int num, String s, boolean b, String date) {
        super(num, s, b);
        this.date = date;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }

    public String toStringForFile() {
        return "D " + super.toStringForFile() + " | " + date;
    }
}
