package task_3;
import java.util.ArrayList;
import java.util.List;
public class Group {
    public String code;
    public List<Student> students = new ArrayList<>();

    public Group(String code){
        this.code = code;
    }

    public List<Student>  get_students(){
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addMarks(int week, int mark, String student_name) {
        synchronized (this) {
            for (Student student : students) {
                if (student.name == student_name){
                    student.addMarks(week, mark);
                }

            }
        }
    }
}
