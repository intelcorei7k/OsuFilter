import javax.swing.*;
import java.awt.*;

public class MainClass {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public static void main(String[] args) {
        Window window = new Window(WIDTH, HEIGHT, "Osu Map Filter by ikki");
        window.setBackColor(new Color(52, 73, 94));

        // Program name
        JLabel ApplicationName = new JLabel("OSU! MAP FILTER");
        ApplicationName.setForeground(new Color(225,123,171));
        ApplicationName.setFont(new Font("Segoe UI", Font.BOLD, 14));
        ApplicationName.setHorizontalAlignment(SwingConstants.CENTER);
        ApplicationName.setBounds((WIDTH/2)-100,30,200,20);

        window.add(ApplicationName);
        window.setVisible(true);
    }
}
