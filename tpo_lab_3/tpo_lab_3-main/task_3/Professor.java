package task_3;
import java.util.ArrayList;
import java.util.List;
public class Professor implements Runnable {
    private  String name;
    private  List<String> j_groups;
    private  Journal journal;

    public Professor(String name, List<String> groups,  Journal journal){
        this.name = name;
        this.j_groups = groups;
        this.journal = journal;
    }

    @Override
    public void run() {
        for (int i = 0; i < 32; i++) {
            for (String code : j_groups) {

                Group g;
                for (Group group : this.journal.groups) {
                    if (group.code == code) {
                      List<Student> students = group.students;


                    for (Student student : students) {
                        String student_name = student.name;
                        int mark = (int) (Math.round(100 * Math.random() * 100)) / 100;
                        journal.addMarks(i, mark, code, student_name);
                    }
                }
                }
            }
        }
    }

}
