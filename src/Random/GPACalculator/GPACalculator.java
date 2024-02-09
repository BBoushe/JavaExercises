package Random.GPACalculator;

import java.util.*;

class Course {
    private String name;
    private int grade;

    public Course(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");
        sb.append("'").append(name).append('\'');
        sb.append(" -> ").append(grade);
        sb.append(']').append('\n');
        return sb.toString();
    }

    public int getGrade() {
        return grade;
    }
}

class GPA {
    private List<Course> coursesList;
    private int size;

    public GPA() {
        this.coursesList = new ArrayList<>();
        this.size = 0;
    }

    public void add(Course newCourse){
        coursesList.add(size++, newCourse);
    }

    public double calculateGPA(){
        double sum = 0;

        for (int i = 0; i < size; i++) {
            sum += coursesList.get(i).getGrade();
        }

        return sum / size;
    }

    public int totalCredits(){
        return size*6;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Courses: \n");
        for (int i = 0; i < size; i++) {
            sb.append("[").append(i+1).append("] <-->").append(coursesList.get(i));
        }
        return sb.toString();
    }
}

public class GPACalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GPA grades = new GPA();
        int i = 1;

        while(true){
           // System.out.print(i++ + ". ");
            String course = sc.nextLine();
            if(course.equals("KRAJ")) break;

            String[] pom = course.split(" ");
            grades.add(new Course(pom[0], Integer.parseInt(pom[1])));
        }

        System.out.println(grades);
        System.out.printf("Your GPA is: %.2f", grades.calculateGPA());
        System.out.println();
        System.out.println("Credits = " + grades.totalCredits());
    }
}
