package create;


	import javax.swing.*;
	import javax.swing.table.DefaultTableModel;
	import java.awt.*;
	import java.awt.event.*;
	import java.util.List;

	public class SearchWindow extends JFrame {

	    private JTextField nameField, deptField, minMarksField, maxMarksField;
	    private JTable resultTable;
	    private DefaultTableModel tableModel;

	    public SearchWindow() {
	        setTitle("Search Students");
	        setSize(800, 500);
	        setLayout(null);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        JLabel nameLabel = new JLabel("Search by Name:");
	        nameLabel.setBounds(30, 20, 120, 25);
	        add(nameLabel);

	        nameField = new JTextField();
	        nameField.setBounds(150, 20, 150, 25);
	        add(nameField);

	        JButton nameSearchBtn = new JButton("Search");
	        nameSearchBtn.setBounds(310, 20, 80, 25);
	        add(nameSearchBtn);

	        JLabel deptLabel = new JLabel("Search by Department:");
	        deptLabel.setBounds(30, 60, 150, 25);
	        add(deptLabel);

	        deptField = new JTextField();
	        deptField.setBounds(180, 60, 120, 25);
	        add(deptField);

	        JButton deptSearchBtn = new JButton("Search");
	        deptSearchBtn.setBounds(310, 60, 80, 25);
	        add(deptSearchBtn);

	        JLabel marksLabel = new JLabel("Search by Marks Range:");
	        marksLabel.setBounds(30, 100, 160, 25);
	        add(marksLabel);

	        minMarksField = new JTextField();
	        minMarksField.setBounds(190, 100, 50, 25);
	        add(minMarksField);

	        maxMarksField = new JTextField();
	        maxMarksField.setBounds(250, 100, 50, 25);
	        add(maxMarksField);

	        JButton marksSearchBtn = new JButton("Search");
	        marksSearchBtn.setBounds(310, 100, 80, 25);
	        add(marksSearchBtn);

	        // Table setup
	        tableModel = new DefaultTableModel();
	        resultTable = new JTable(tableModel);
	        JScrollPane scrollPane = new JScrollPane(resultTable);
	        scrollPane.setBounds(30, 150, 720, 280);
	        add(scrollPane);

	        tableModel.setColumnIdentifiers(new String[]{
	                "ID", "Name", "RollNo", "Department", "Email", "Phone", "Subject1", "Subject2", "Subject3", "Total", "Grade"
	        });

	        // Actions
	        nameSearchBtn.addActionListener(e -> searchByName());
	        deptSearchBtn.addActionListener(e -> searchByDepartment());
	        marksSearchBtn.addActionListener(e -> searchByMarks());
	        
	        setVisible(true);
	    }

	    private void searchByName() {
	        String name = nameField.getText().trim();
	        if (name.isEmpty()) return;

	        List<Student> students = new StudentDAO().searchByName(name);
	        updateTable(students);
	    }

	    private void searchByDepartment() {
	        String dept = deptField.getText().trim();
	        if (dept.isEmpty()) return;

	        List<Student> students = new StudentDAO().searchByDepartment(dept);
	        updateTable(students);
	    }

	    private void searchByMarks() {
	        try {
	            int min = Integer.parseInt(minMarksField.getText().trim());
	            int max = Integer.parseInt(maxMarksField.getText().trim());

	            List<Student> students = new StudentDAO().searchByMarksRange(min, max);
	            updateTable(students);
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(this, "Enter valid numbers for marks range.");
	        }
	    }

	    private void updateTable(List<Student> students) {
	        tableModel.setRowCount(0); // clear existing rows

	        for (Student s : students) {
	            tableModel.addRow(new Object[]{
	                    s.getId(), s.getName(), s.getRollNo(), s.getDepartment(),
	                    s.getEmail(), s.getPhone(), s.getSubject1(), s.getSubject2(),
	                    s.getSubject3(), s.getTotalMarks(), s.getGrade()
	            });
	        }
	    }
	}


