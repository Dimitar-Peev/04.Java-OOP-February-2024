package _01_WorkingWithAbstraction._01_Lab._03_StudentSystem;

import java.util.HashMap;
import java.util.Map;

public class StudentRepository {
    private Map<String, Student> studentsByName;

    public StudentRepository() {
        this.studentsByName = new HashMap<>();
    }

    public void add(Student student) {
        this.studentsByName.put(student.getName(), student);
    }

    public Map<String, Student> getStudentsByName() {
        return studentsByName;
    }

    public Student get(String name) {
        return this.studentsByName.get(name);
    }
}
