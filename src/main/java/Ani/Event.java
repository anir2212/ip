
package Ani;

public class Event extends Task{

    private String start;
    private String end;

    Event(int num, String s, boolean b, String start, String end) {

        super(num, s, b);
        this.start = start;
        this.end = end;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    public String toStringForFile() {
        return "E " + super.toStringForFile() + " | from: " + start + " to: " + end;

    }



}
