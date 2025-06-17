package create;

public class MainGui {
	
	    public static void main(String[] args) {
	        // Launch the GUI on the Event Dispatch Thread
	        javax.swing.SwingUtilities.invokeLater(() -> {
	            new LoginWindow();  // Create and show the GUI window
	        });
	    }
	}


