package create;

import java.util.*;
import create.StudentDAO;
import create.Student;
import create.ExportUtility;




public class Main {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Welcome to SmartStudent System ===");
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        if (!user.equals("admin") || !pass.equals("admin123")) {
            System.out.println("Invalid login. Exiting...");
            return;
        }

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Searching");
            System.out.println("6. View Statistics");
            System.out.println("7. Export to CSV");
            System.out.println("8. Exit");
            System.out.print("Option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Roll No: ");
                    String roll = sc.nextLine();
                    System.out.print("Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Phone: ");
                    String phone = sc.nextLine();

                    System.out.print("Subject 1 Marks: ");
                    int s1 = sc.nextInt();
                    System.out.print("Subject 2 Marks: ");
                    int s2 = sc.nextInt();
                    System.out.print("Subject 3 Marks: ");
                    int s3 = sc.nextInt();
                    sc.nextLine();

                    Student s = new Student(name, roll, dept, email, phone, s1, s2, s3);
                    s.calculateTotalMarks();
                    
                    s.calculateGrade();

                    if (dao.addStudent(s))
                        System.out.println("Student added successfully.");
                    else
                        System.out.println("Error adding student.");
                    break;

                case 2:
                    List<Student> students = dao.getAllStudents();
                    if (students.isEmpty()) {
                        System.out.println("No student records found.");
                    } else {
                        for (Student st : students)
                            System.out.println(st);
                    }
                    break;

                case 3:
                    System.out.print("Enter Roll No to Update: ");
                    String rollUpdate = sc.nextLine();

                    System.out.print("New Name: ");
                    String nName = sc.nextLine();
                    System.out.print("New Department: ");
                    String nDept = sc.nextLine();
                    System.out.print("New Email: ");
                    String nEmail = sc.nextLine();
                    System.out.print("New Phone: ");
                    String nPhone = sc.nextLine();
                    System.out.print("Subject 1 Marks: ");
                    int ns1 = sc.nextInt();
                    System.out.print("Subject 2 Marks: ");
                    int ns2 = sc.nextInt();
                    System.out.print("Subject 3 Marks: ");
                    int ns3 = sc.nextInt();
                    sc.nextLine();

                    Student updated = new Student(nName, rollUpdate, nDept, nEmail, nPhone, ns1, ns2, ns3);
                    updated.calculateTotalMarks();
                    updated.calculateGrade();

                    if (dao.updateStudent(updated))
                        System.out.println("Student updated.");
                    else
                        System.out.println("Error updating student.");
                    break;

                case 4:
                    System.out.print("Enter Roll No to Delete: ");
                    String rollDel = sc.nextLine();
                    if (dao.deleteStudent(rollDel))
                        System.out.println("Student deleted.");
                    else
                        System.out.println("Error deleting student.");
                    break;

                case 5:
                    System.out.println("\nSearch Options:");
                    System.out.println("1. By Name");
                    System.out.println("2. By Department");
                    System.out.println("3. By Marks Range (Average)");
                    System.out.print("Choose option: ");
                    int choice2 = sc.nextInt();
                    sc.nextLine();

                    List<Student> result = new ArrayList<>();
                    switch (choice2) {
                        case 1:
                            System.out.print("Enter name to search: ");
                            String name2 = sc.nextLine();
                            result = dao.searchByName(name2);
                            break;
                        case 2:
                            System.out.print("Enter department to search: ");
                            String dept2 = sc.nextLine();
                            result = dao.searchByDepartment(dept2);
                            break;
                        case 3:
                            System.out.print("Enter min marks: ");
                            int min = sc.nextInt();
                            System.out.print("Enter max marks: ");
                            int max = sc.nextInt();
                            sc.nextLine();
                            result = dao.searchByMarksRange(min, max);
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }

                    if (!result.isEmpty()) {
                        for (Student sa : result)
                            System.out.println(sa);
                    } else {
                        System.out.println("No matching records found.");
                    }
                    break;

                case 6:
                    System.out.println("\n📊 Statistics Module");
                    int totalStudents = dao.getTotalStudentCount();
                    System.out.println("Total Students: " + totalStudents);

                    Student topStudent = dao.getTopScorer();
                    System.out.println("\n🏅 Top Scorer:");
                    if (topStudent != null)
                        System.out.println(topStudent);

                    Student lowStudent = dao.getLowestScorer();
                    System.out.println("\n🔻 Lowest Scorer:");
                    if (lowStudent != null)
                        System.out.println(lowStudent);

                    Map<String, Integer> deptCount = dao.getDepartmentWiseCount();
                    System.out.println("\n🏫 Department-wise Count:");
                    for (Map.Entry<String, Integer> entry : deptCount.entrySet())
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    break;
                
                case 7:
                    List<Student> sa = dao.getAllStudents();
                    String filePath =  "C:\\Users\\mohds\\OneDrive\\Documents\\student.csv";
                    ExportUtility.exportToCSV(sa, filePath);
                    break;

                	
                case 8:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
