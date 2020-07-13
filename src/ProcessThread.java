import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
                ArrayList<String> beatmapIdString;
                ArrayList<Integer> beatmapId = new ArrayList<>();
                try {
                    beatmapIdString = BeatmapLoader.bmIdLoader(temp);
                    if(beatmapIdString == null || beatmapIdString.size() == 0) beatmapId = null;
                    else {
                        for(String s : beatmapIdString) {
                            beatmapId.add(Integer.parseInt(s.substring(10)));
                            BeatmapLoader.bmDifficultyCalculator(beatmapId.get(beatmapId.size()-1));
                        }
                    }
                } catch (IOException | InterruptedException e) { e.printStackTrace(); }
                this.instructionTextArea.setText(instructionTextArea.getText() + "\n" + temp.getName());
                if(beatmapId != null)
                    for(Integer i : beatmapId)
                        if(i > 0) this.instructionTextArea.setText(instructionTextArea.getText() + "\n\t" + " ID: " + i);
                this.instructionTextArea.setCaretPosition(instructionTextArea.getDocument().getLength() - 1);
                return true;
            }
            return false; });

    }
}
