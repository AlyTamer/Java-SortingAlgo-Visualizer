import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Ensure the GUI is created on the Event Dispatch Thread (EDT)
        // This creates and shows the GUI
        SwingUtilities.invokeLater(GUI::new);
    }
}
