package create;

import java.sql.*;
import java.util.*;

public class StudentDAO {

    // Add new student
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students1 (name, roll_no, department, email, phone, subject1, subject2, subject3, totalmarks, grade) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getRollNo());
            stmt.setString(3, student.getDepartment());
            stmt.setString(4, student.getEmail());
            stmt.setString(5, student.getPhone());
            stmt.setInt(6, student.getSubject1());
            stmt.setInt(7, student.getSubject2());
            stmt.setInt(8, student.getSubject3());
            stmt.setInt(9, student.getTotalMarks());
            stmt.setString(10, student.getGrade());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // View all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students1";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student student = mapResultSetToStudent(rs);
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    // Update student by roll number
    public boolean updateStudent(Student student) {
        String sql = "UPDATE students1 SET name=?, department=?, email=?, phone=?, subject1=?, subject2=?, subject3=?, totalmarks=?, grade=? WHERE roll_no=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getDepartment());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, student.getPhone());
            stmt.setInt(5, student.getSubject1());
            stmt.setInt(6, student.getSubject2());
            stmt.setInt(7, student.getSubject3());
            stmt.setInt(8, student.getTotalMarks());
            stmt.setString(9, student.getGrade());
            stmt.setString(10, student.getRollNo());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete student
    public boolean deleteStudent(String rollNo) {
        String sql = "DELETE FROM students1 WHERE roll_no=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, rollNo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Search by name
    public List<Student> searchByName(String name) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students1 WHERE name LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                students.add(mapResultSetToStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Search by department
    public List<Student> searchByDepartment(String dept) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students1 WHERE department=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dept);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                students.add(mapResultSetToStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Search by totalmarks range
    public List<Student> searchByMarksRange(int min, int max) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students1 WHERE totalmarks BETWEEN ? AND ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, min);
            stmt.setInt(2, max);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                students.add(mapResultSetToStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Map ResultSet to Student
    private Student mapResultSetToStudent(ResultSet rs) throws SQLException {
        Student student = new Student(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("roll_no"),
                rs.getString("department"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getInt("subject1"),
                rs.getInt("subject2"),
                rs.getInt("subject3")
        );
        student.setTotalMarks(rs.getInt("totalmarks"));
        student.setGrade(rs.getString("grade"));
        return student;
    }

    // Total students
    public int getTotalStudentCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM students1";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    // Top scorer
    public Student getTopScorer() {
        String sql = "SELECT * FROM students1 ORDER BY totalmarks DESC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return mapResultSetToStudent(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lowest scorer
    public Student getLowestScorer() {
        String sql = "SELECT * FROM students1 ORDER BY totalmarks ASC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return mapResultSetToStudent(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Department-wise count
    public Map<String, Integer> getDepartmentWiseCount() {
        Map<String, Integer> map = new HashMap<>();
        String sql = "SELECT department, COUNT(*) AS count FROM students1 GROUP BY department";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                map.put(rs.getString("department"), rs.getInt("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
}
