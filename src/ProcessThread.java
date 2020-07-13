import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ProcessThread extends Thread{
    JTextArea instructionTextArea;
    String folderPath;
    String[] beatmapFolders;
    Float maxAcceptedDiffuculty;
    int validBeatmapCounter;

    public ProcessThread(String targetFolder, JTextArea textArea, Float maxAcceptedDiffuculty){
        this.instructionTextArea = textArea;
        this.folderPath = targetFolder.replace("\\", "/") + "/";
        this.maxAcceptedDiffuculty = maxAcceptedDiffuculty;
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
                        this.instructionTextArea.setText(instructionTextArea.getText() + "\n" + temp.getName());
                        for(String s : data.idInfo) {
                            data.id.add(Integer.parseInt(s.substring(10)));
                            data.difficulty.add(BeatmapLoader.bmDifficultyCalculator(data.id.get(data.id.size()-1)));
                            if(data.id != null && data.difficulty.get(data.difficulty.size()-1) > 0)
                                this.instructionTextArea.setText(instructionTextArea.getText() + "\n\t" + " ID: "
                                        + data.id.get(data.id.size()-1) + "\tSTAR RATING: "
                                        + data.difficulty.get(data.difficulty.size()-1));
                            this.instructionTextArea.setCaretPosition(instructionTextArea.getDocument().getLength() - 1);
                        }
                    }
                } catch (IOException | InterruptedException e) { e.printStackTrace(); }
                for(Float f : data.difficulty) {
                    if (f >= this.maxAcceptedDiffuculty) {
                        validBeatmapCounter++;
                        return true;
                    }
                }
            }
            return false; });
        for(String s : this.beatmapFolders)
            System.out.println(s);
        System.out.println("Number of valid maps: " + this.validBeatmapCounter);
        this.instructionTextArea.setText(instructionTextArea.getText() + "\n\n" +
                "Number of valid ranked beatmap found: " + this.validBeatmapCounter);
    }
}
