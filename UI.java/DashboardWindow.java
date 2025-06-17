package create;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class DashboardWindow extends JFrame {

    public DashboardWindow() {
        setTitle("Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton studentFormBtn = new JButton("Student Form");
        JButton searchBtn = new JButton("Search");
        JButton statsBtn = new JButton("Statistics");
        JButton exportBtn = new JButton("Export to CSV");
        JButton logoutBtn = new JButton("Logout");

        // Actions
        studentFormBtn.addActionListener(e -> new StudentFormWindow());
        searchBtn.addActionListener(e -> new SearchWindow());
        statsBtn.addActionListener(e -> new StatisticsWindow());

        exportBtn.addActionListener(e -> {
            List<Student> students = new StudentDAO().getAllStudents();
            String filePath =  "C:\\Users\\mohds\\OneDrive\\Documents\\student.csv";
            ExportUtility.exportToCSV(students, filePath);
        });

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginWindow(); // Go back to login
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));
        panel.add(studentFormBtn);
        panel.add(searchBtn);
        panel.add(statsBtn);
        panel.add(exportBtn);
        panel.add(logoutBtn);

        add(panel);
        setVisible(true);
    }
}

