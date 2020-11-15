public class Student {
    int id;
    String Name;
    String Surname;
    int AverageScore;

    public Student(int id, String name, String surname, int averageScore) {
        this.id = id;
        Name = name;
        Surname = surname;
        AverageScore = averageScore;
    }



    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", AverageScore=" + AverageScore +
                '}';
    }
}
