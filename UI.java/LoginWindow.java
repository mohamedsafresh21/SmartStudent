package create;


	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.*;
	import java.sql.*;

	public class LoginWindow extends JFrame {
	    private JTextField userField;
	    private JPasswordField passField;

	    public LoginWindow() {
	        setTitle("Student Login");
	        setSize(350, 200);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);

	        userField = new JTextField(15);
	        passField = new JPasswordField(15);

	        JButton loginButton = new JButton("Login");

	        loginButton.addActionListener(e -> login());

	        JPanel panel = new JPanel(new GridLayout(3, 2));
	        panel.add(new JLabel("Username:"));
	        panel.add(userField);
	        panel.add(new JLabel("Password:"));
	        panel.add(passField);
	        panel.add(new JLabel());
	        panel.add(loginButton);

	        add(panel);
	        setVisible(true);
	    }

	    private void login() {
	        String username = userField.getText();
	        String password = String.valueOf(passField.getPassword());

	        try (Connection conn = DatabaseConnection.getConnection()) {
	            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, username);
	            stmt.setString(2, password);

	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                JOptionPane.showMessageDialog(this, "Login successful!");
	                new DashboardWindow();  // Open dashboard
	                this.dispose();         // Close login window
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}


