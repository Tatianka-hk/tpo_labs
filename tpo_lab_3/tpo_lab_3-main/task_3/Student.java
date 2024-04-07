package task_3;
import java.util.ArrayList;
import java.util.List;
public class Student {
    public String name;
    public List<Mark> marks = new ArrayList<>();

    public Student(String name){
        this.name=name;
    }

    public void addMarks(int week, int mark) {
        synchronized (this) {
            marks.add(new Mark(week, mark));
        }
    }
}
