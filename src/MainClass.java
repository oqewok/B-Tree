import javax.swing.*;

public class MainClass {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(false);
                ConsoleWindow consoleWindow = new ConsoleWindow();
                consoleWindow.setLocationRelativeTo(null);
                consoleWindow.setResizable(false);
                consoleWindow.setVisible(true);
            }
        });
    }
}
