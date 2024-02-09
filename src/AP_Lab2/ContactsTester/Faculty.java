package AP_Lab2.ContactsTester;

import java.util.Arrays;

public class Faculty {
    private String name;
    private Student[] students;

    public Faculty(String name, Student[] students) {
        this.name = name;
        this.students = students;
    }

    public int countStudentsFromCity(String cityName){
        int result = 0;

        for (Student student : students){
            if(student.getCity().equals(cityName))  result++;
        }

        return result;
    }

    public Student getStudent(long index){
        for (Student student : students) {
            if(student.getIndex() == index) return student;
        }

        return null;
    }

    public double getAverageNumberOfContacts() {
        int n = students.length;
        int sum = 0;

        for(Student student : students) {
            sum += student.getNumOfEmail() + student.getNumOfPhone();
        }

        return sum / (double) n;
    }

    public Student getStudentWithMostContacts(){
        if(students.length == 1)
            return students[0];

        Student mostStudent = students[0];

        for (int i = 1; i < students.length; i++) {
            if(students[i].getNumOfContacts() > mostStudent.getNumOfContacts()){
                mostStudent = students[i];
            } else if(students[i].getNumOfContacts() == mostStudent.getNumOfContacts()){
                mostStudent = (students[i].getIndex() > mostStudent.getIndex()) ?  students[i] : mostStudent;
            }
        }

        return mostStudent;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"fakultet\":\"").append(name).append("\"");
        sb.append(", \"studenti\":").append(Arrays.toString(students));
        sb.append("}");
        return sb.toString();
    }
}
