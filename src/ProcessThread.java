import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ProcessThread extends Thread{
    JTextArea instructionTextArea;
    String folderPath;
    String[] beatmapFolders;

    public ProcessThread(String targetFolder, JTextArea textArea){
        this.instructionTextArea = textArea;
        this.folderPath = targetFolder.replace("\\", "/") + "/";
    }

    @Override
    public void run() {
        File mapsFolder = new File(this.folderPath);
        this.instructionTextArea.setText("STARTING BEATMAP ELABORATION");
        this.beatmapFolders = mapsFolder.list((dir, name) -> {
            File temp = new File(dir, name);
            if(temp.isDirectory()) {
                String beatmapIdString = null;
                int beatmapId = -1;
                try {
                    beatmapIdString = BeatmapLoader.bmIdLoader(temp);
                    if(beatmapIdString == null) beatmapId = -1;
                    else {
                        beatmapId = Integer.parseInt(beatmapIdString.substring(10));
                        BeatmapLoader.bmDifficultyCalculator(beatmapId);
                    }
                } catch (IOException | InterruptedException e) { e.printStackTrace(); }
                this.instructionTextArea.setText(instructionTextArea.getText() + "\n" + temp.getName());
                if(beatmapId > 0) this.instructionTextArea.setText(instructionTextArea.getText() + " ID: " + beatmapId);
                this.instructionTextArea.setCaretPosition(instructionTextArea.getDocument().getLength() - 1);
                return true;
            }
            return false; });

    }
}
