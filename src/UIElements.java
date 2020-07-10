import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;

public class UIElements {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final Color LABEL_FONT_COLOR = new Color(225,123,171);
    private static final Color LABEL_ERROR_FONT_COLOR = new Color(234, 32, 39);
    private static final Color TEXTFIELD_BACK_COLOR = new Color(44, 62, 80);
    private static final Color WINDOW_BACK_COLOR = new Color(52, 73, 94);
    private static final Font DEAFAULT_FONT = new Font("Segoe UI", Font.BOLD, 14);

    public Window window;
    public JLabel applicationName;
    public JLabel pathInputLabel;
    public JLabel starInputLabel;
    public JLabel instructionOutputLabel;
    public JLabel starInputErrorLabel;
    public JButton open;
    public JButton start;
    public JSpinner starInput;
    public JTextArea instructionOutput;
    public JTextField pathInput;

    public UIElements() {
        this.window = new Window(WIDTH, HEIGHT, "Osu Map Filter by ikki");
        window.setBackColor(WINDOW_BACK_COLOR);

        // UI Program name
        this.applicationName = new JLabel("OSU! MAP FILTER");
        applicationName.setForeground(LABEL_FONT_COLOR);
        applicationName.setFont(DEAFAULT_FONT);
        applicationName.setHorizontalAlignment(SwingConstants.CENTER);
        applicationName.setBounds((WIDTH/2)-100,30,200,20);

        // UI display path input info
        this.pathInputLabel = new JLabel("MAP FOLDER");
        pathInputLabel.setForeground(LABEL_FONT_COLOR);
        pathInputLabel.setFont(DEAFAULT_FONT);
        pathInputLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        pathInputLabel.setBounds(WIDTH/6, 80, 100, 25);

        // UI input path
        this.pathInput = new JTextField();
        pathInput.setForeground(Color.WHITE);
        pathInput.setFont(DEAFAULT_FONT);
        pathInput.setHorizontalAlignment(SwingConstants.LEFT);
        pathInput.setBorder(null);
        pathInput.setBackground(TEXTFIELD_BACK_COLOR);
        pathInput.setBounds(WIDTH/6+130, 80, 280, 25);

        // UI Folder chooser button
        this.open = new JButton("BROWSE");
        open.setFont(DEAFAULT_FONT);
        open.setFocusPainted(false);
        open.setBorder(null);
        open.setBackground(TEXTFIELD_BACK_COLOR);
        open.setForeground(Color.WHITE);
        open.setBounds(WIDTH/6+450, 80, 80, 25);
        open.addActionListener(e -> {
            JFileChooser folderChooser = new JFileChooser();
            folderChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));
            folderChooser.setDialogTitle("Choose the beatmap folder you want to filter");
            folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(folderChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
                pathInput.setText(folderChooser.getSelectedFile().getAbsolutePath());
        });

        // UI display star rating filter info
        this.starInputLabel = new JLabel("STAR DIFFICULTY");
        starInputLabel.setForeground(LABEL_FONT_COLOR);
        starInputLabel.setFont(DEAFAULT_FONT);
        starInputLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        starInputLabel.setBounds(WIDTH/6-20, 150, 135, 25);

        // UI input star rating
        this.starInput = new JSpinner();
        starInput.setModel(new SpinnerNumberModel((float)0, (float)0, (float)10, (float)0.01));
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor)starInput.getEditor();
        DecimalFormat format = editor.getFormat();
        format.setMinimumFractionDigits(2);
        editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        editor.getComponent(0).setBackground(TEXTFIELD_BACK_COLOR);
        editor.getComponent(0).setForeground(Color.WHITE);
        editor.getComponent(0).setFont(DEAFAULT_FONT);
        starInput.setBorder(null);
        starInput.setBounds((WIDTH/2)-50,150,100,25);

        // UI input star rating error label
        this.starInputErrorLabel = new JLabel("INVALID INPUT, MAX = 10.00");
        starInputErrorLabel.setForeground(LABEL_ERROR_FONT_COLOR);
        starInputErrorLabel.setFont(DEAFAULT_FONT);
        starInputErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
        starInputErrorLabel.setBounds((WIDTH/2)+100,150,200,25);

        // UI display program instruction info
        this.instructionOutputLabel = new JLabel("↓ WHAT I'M DOING ↓");
        instructionOutputLabel.setForeground(LABEL_FONT_COLOR);
        instructionOutputLabel.setFont(DEAFAULT_FONT);
        instructionOutputLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionOutputLabel.setBounds(WIDTH/3,225,WIDTH/3,25);

        // UI display program instruction
        this.instructionOutput = new JTextArea();
        instructionOutput.setBackground(TEXTFIELD_BACK_COLOR);
        instructionOutput.setForeground(LABEL_FONT_COLOR);
        instructionOutput.setFocusable(false);
        instructionOutput.setBounds(WIDTH/10,260,(WIDTH/10)*8,HEIGHT-(260+(2*(WIDTH/12))));

        // UI start process button
        this.start = new JButton("START");
        start.setFont(DEAFAULT_FONT);
        start.setFocusPainted(false);
        start.setBorder(null);
        start.setBackground(TEXTFIELD_BACK_COLOR);
        start.setForeground(Color.WHITE);
        start.setBounds((WIDTH/10)*8,HEIGHT-(WIDTH/8),80,25);

        this.window.add(open);
        this.window.add(start);
        this.window.add(starInput);
        this.window.add(pathInput);
        this.window.add(starInputLabel);
        this.window.add(pathInputLabel);
        this.window.add(applicationName);
        this.window.add(instructionOutput);
        this.window.add(starInputErrorLabel);
        this.window.add(instructionOutputLabel);
        this.window.setVisible(true);
    }
}
