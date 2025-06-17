package create;


import java.io.*;
import java.util.List;

public class ExportUtility {
    public static void exportToCSV(List<Student> students, String fileName) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            writer.println("ID,Name,RollNo,Department,Email,Phone,Subject1,Subject2,Subject3,TotalMarks,Grade");

            for (Student s : students) {
                writer.printf("%d,%s,%s,%s,%s,%s,%d,%d,%d,%d,%s%n",
                        s.getId(), s.getName(), s.getRollNo(), s.getDepartment(), s.getEmail(),
                        s.getPhone(), s.getSubject1(), s.getSubject2(), s.getSubject3(),
                        s.getTotalMarks(), s.getGrade());
            }

            System.out.println("Export successful: " + fileName);

        } catch (IOException e) {
            System.err.println("Error writing CSV: " + e.getMessage());
        }
    }
}



