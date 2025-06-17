package create;


	import javax.swing.*;
	import java.awt.*;
	import java.util.Map;

	public class StatisticsWindow extends JFrame {

	    private JLabel totalCountLabel;
	    private JLabel topScorerLabel;
	    private JLabel lowScorerLabel;
	    private JTextArea deptCountArea;

	    public StatisticsWindow() {
	        setTitle("Student Statistics");
	        setSize(500, 400);
	        setLayout(null);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        JLabel title = new JLabel("Statistics");
	        title.setFont(new Font("Arial", Font.BOLD, 20));
	        title.setBounds(180, 20, 200, 30);
	        add(title);

	        totalCountLabel = new JLabel("Total Students: ");
	        totalCountLabel.setBounds(50, 70, 400, 25);
	        add(totalCountLabel);

	        topScorerLabel = new JLabel("Top Scorer: ");
	        topScorerLabel.setBounds(50, 110, 400, 25);
	        add(topScorerLabel);

	        lowScorerLabel = new JLabel("Lowest Scorer: ");
	        lowScorerLabel.setBounds(50, 150, 400, 25);
	        add(lowScorerLabel);

	        JLabel deptLabel = new JLabel("Department-wise Count:");
	        deptLabel.setBounds(50, 190, 200, 25);
	        add(deptLabel);

	        deptCountArea = new JTextArea();
	        deptCountArea.setBounds(50, 220, 380, 100);
	        deptCountArea.setEditable(false);
	        add(deptCountArea);

	        loadStatistics();
	        setVisible(true);
	    }

	    private void loadStatistics() {
	        StudentDAO dao = new StudentDAO();

	        int total = dao.getTotalStudentCount();
	        Student top = dao.getTopScorer();
	        Student low = dao.getLowestScorer();
	        Map<String, Integer> deptCounts = dao.getDepartmentWiseCount();

	        totalCountLabel.setText("Total Students: " + total);

	        if (top != null)
	            topScorerLabel.setText("Top Scorer: " + top.getName() + " - " + top.getTotalMarks());
	        else
	            topScorerLabel.setText("Top Scorer: No Data");

	        if (low != null)
	            lowScorerLabel.setText("Lowest Scorer: " + low.getName() + " - " + low.getTotalMarks());
	        else
	            lowScorerLabel.setText("Lowest Scorer: No Data");

	        StringBuilder sb = new StringBuilder();
	        for (Map.Entry<String, Integer> entry : deptCounts.entrySet()) {
	            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append(" students\n");
	        }
	        deptCountArea.setText(sb.toString());
	    }
	}


