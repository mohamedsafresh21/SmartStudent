package create;

public class Student {
    private int id;
    private String name;
    private String rollNo;
    private String department;
    private String email;
    private String phone;
    private int subject1;
    private int subject2;
    private int subject3;
    private int totalmarks;
    private String grade;

    // Constructor with ID
    public Student(int id, String name, String rollNo, String department, String email, String phone,
                   int subject1, int subject2, int subject3) {
        this.id = id;
        this.name = name;
        this.rollNo = rollNo;
        this.department = department;
        this.email = email;
        this.phone = phone;
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
        calculateTotalMarks();
        calculateGrade();
    }
public Student() {
	
}
    // Constructor without ID
    public Student(String name, String rollNo, String department, String email, String phone,
                   int subject1, int subject2, int subject3) {
        this.name = name;
        this.rollNo = rollNo;
        this.department = department;
        this.email = email;
        this.phone = phone;
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
        calculateTotalMarks();
        calculateGrade();
    }

    // 🔧 Fix: Set totalmarks correctly
    public void calculateTotalMarks() {
        this.totalmarks = subject1 + subject2 + subject3;
    }

    public void calculateGrade() {
        int avg = totalmarks / 3;
        if (avg >= 90) grade = "A+";
        else if (avg >= 80) grade = "A";
        else if (avg >= 70) grade = "B";
        else if (avg >= 60) grade = "C";
        else grade = "F";
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getRollNo() { return rollNo; }
    public String getDepartment() { return department; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public int getSubject1() { return subject1; }
    public int getSubject2() { return subject2; }
    public int getSubject3() { return subject3; }
    public int getTotalMarks() { return totalmarks; }
    public String getGrade() { return grade; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }
    public void setDepartment(String department) { this.department = department; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setSubject1(int subject1) { this.subject1 = subject1; calculateTotalMarks(); calculateGrade(); }
    public void setSubject2(int subject2) { this.subject2 = subject2; calculateTotalMarks(); calculateGrade(); }
    public void setSubject3(int subject3) { this.subject3 = subject3; calculateTotalMarks(); calculateGrade(); }
    public void setTotalMarks(int totalmarks) { this.totalmarks = totalmarks; }  // Not used normally
    public void setGrade(String grade) { this.grade = grade; }                    // Not used normally

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Roll No: " + rollNo +
                ", Dept: " + department + ", Email: " + email +
                ", Phone: " + phone + ", Marks: [" + subject1 + ", " + subject2 + ", " + subject3 + "]" +
                ", Total Marks: " + totalmarks + ", Grade: " + grade;
    }
}



