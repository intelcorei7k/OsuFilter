import javax.swing.*;
import java.io.File;

public class ProcessThread extends Thread{
    JTextArea instructionTextArea;
    String folderPath;

    public ProcessThread(String targetFolder, JTextArea textArea){
        this.instructionTextArea = textArea;
        this.folderPath = targetFolder.replace("\\", "/") + "/";
    }

    @Override
    public void run() {
        File mapsFolder = new File(this.folderPath);
        this.instructionTextArea.setText("STARTING BEATMAP ELABORATION");
        String[] subDirectories = mapsFolder.list((dir, name) -> {
            File temp = new File(dir, name);
            if(temp.isDirectory()) {
                this.instructionTextArea.setText(instructionTextArea.getText() + "\n" + temp.getName().toUpperCase());
                this.instructionTextArea.setCaretPosition(instructionTextArea.getDocument().getLength() - 1);
                return true;
            }
            return false; });
        for (String s : subDirectories) {
            System.out.println(s);
        }
    }
}
