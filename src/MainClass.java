import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainClass {

    public static void main(String[] args) {
        UIElements UI = new UIElements();

        while(true) {
            float value = ((Double)UI.starInput.getValue()).floatValue();
            System.out.println(value);
        }
    }
}
