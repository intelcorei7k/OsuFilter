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
                BeatmapInfo data = new BeatmapInfo();
                try {
                    data.idInfo = BeatmapLoader.bmIdLoader(temp);
                    if(data.idInfo == null || data.idInfo.size() == 0) data.id = null;
                    else {
                        for(String s : data.idInfo) {
                            data.id.add(Integer.parseInt(s.substring(10)));
                            data.difficulty.add(BeatmapLoader.bmDifficultyCalculator(data.id.get(data.id.size()-1)));
                        }
                    }
                } catch (IOException | InterruptedException e) { e.printStackTrace(); }
                this.instructionTextArea.setText(instructionTextArea.getText() + "\n" + temp.getName());
                if(data.id != null)
                    for(Integer i : data.id)
                        if(i > 0) this.instructionTextArea.setText(instructionTextArea.getText() + "\n\t" + " ID: " + i
                            + "\tSTAR RATING: " + data.difficulty.get(data.id.indexOf(i)));
                this.instructionTextArea.setCaretPosition(instructionTextArea.getDocument().getLength() - 1);
                return true;
            }
            return false; });

    }
}
