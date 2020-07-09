import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window(int Width, int Height, String title) {
        super(title);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(dim.width/2-Width/2, dim.height/2-Height/2, Width, Height);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }

    public void setBackColor(Color color) { this.getContentPane().setBackground(color); }
}
