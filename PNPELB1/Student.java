public class Student{
    public String studentName;
    public long factultyNumber;
    public static int totalStudents = 0;

    public Student(String studentName, long factultyNumber){
        this.studentName=studentName;
        this.factultyNumber=factultyNumber;
        totalStudents++;
    }

    public static int getTotalStudents(){
        return totalStudents;
    }
}
