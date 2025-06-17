package create;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class StudentFormWindow extends JFrame {

    private JTextField nameField, rollField, deptField, emailField, phoneField;
    private JTextField sub1Field, sub2Field, sub3Field;
    private JTable table;
    private DefaultTableModel model;

    private StudentDAO dao = new StudentDAO();

    public StudentFormWindow() {
        setTitle("Student Form");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel for form fields
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel[] labels = {
            new JLabel("Name:"), new JLabel("Roll Number:"), new JLabel("Department:"),
            new JLabel("Email:"), new JLabel("Phone:"), new JLabel("Subject 1 Marks:"),
            new JLabel("Subject 2 Marks:"), new JLabel("Subject 3 Marks:")
        };

        nameField = new JTextField(15);
        rollField = new JTextField(15);
        deptField = new JTextField(15);
        emailField = new JTextField(15);
        phoneField = new JTextField(15);
        sub1Field = new JTextField(5);
        sub2Field = new JTextField(5);
        sub3Field = new JTextField(5);

        JTextField[] fields = {
            nameField, rollField, deptField, emailField,
            phoneField, sub1Field, sub2Field, sub3Field
        };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            formPanel.add(labels[i], gbc);
            gbc.gridx = 1;
            formPanel.add(fields[i], gbc);
        }

        // Buttons
        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");

        addBtn.addActionListener(e -> addStudent());
        updateBtn.addActionListener(e -> updateStudent());
        deleteBtn.addActionListener(e -> deleteStudent());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);

        // Table
        String[] columnNames = {"ID", "Name", "Roll No", "Department", "Email", "Phone",
                                "Sub1", "Sub2", "Sub3", "Total", "Grade"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        loadStudents();

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    nameField.setText(model.getValueAt(row, 1).toString());
                    rollField.setText(model.getValueAt(row, 2).toString());
                    deptField.setText(model.getValueAt(row, 3).toString());
                    emailField.setText(model.getValueAt(row, 4).toString());
                    phoneField.setText(model.getValueAt(row, 5).toString());
                    sub1Field.setText(model.getValueAt(row, 6).toString());
                    sub2Field.setText(model.getValueAt(row, 7).toString());
                    sub3Field.setText(model.getValueAt(row, 8).toString());
                }
            }
        });

        JScrollPane tableScroll = new JScrollPane(table);

        // Layout
        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(tableScroll, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadStudents() {
        model.setRowCount(0);
        List<Student> students = dao.getAllStudents();
        for (Student s : students) {
            model.addRow(new Object[]{
                s.getId(), s.getName(), s.getRollNo(), s.getDepartment(),
                s.getEmail(), s.getPhone(), s.getSubject1(), s.getSubject2(),
                s.getSubject3(), s.getTotalMarks(), s.getGrade()
            });
        }
    }

    private void addStudent() {
        Student s = getStudentFromForm();
        if (dao.addStudent(s)) {
            JOptionPane.showMessageDialog(this, "Student added successfully.");
            loadStudents();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add student.");
        }
    }

    private void updateStudent() {
        Student s = getStudentFromForm();
        if (dao.updateStudent(s)) {
            JOptionPane.showMessageDialog(this, "Student updated successfully.");
            loadStudents();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update student.");
        }
    }

    private void deleteStudent() {
        String rollNo = rollField.getText();
        if (dao.deleteStudent(rollNo)) {
            JOptionPane.showMessageDialog(this, "Student deleted.");
            loadStudents();
        } else {
            JOptionPane.showMessageDialog(this, "Delete failed.");
        }
    }

    private Student getStudentFromForm() {
        Student s = new Student();
        s.setName(nameField.getText());
        s.setRollNo(rollField.getText());
        s.setDepartment(deptField.getText());
        s.setEmail(emailField.getText());
        s.setPhone(phoneField.getText());
        s.setSubject1(Integer.parseInt(sub1Field.getText()));
        s.setSubject2(Integer.parseInt(sub2Field.getText()));
        s.setSubject3(Integer.parseInt(sub3Field.getText()));
        s.calculateTotalMarks();        // Calculate total
        s.calculateGrade();    // Calculate grade
        return s;
    }
}




