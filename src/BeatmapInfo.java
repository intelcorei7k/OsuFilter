import java.util.ArrayList;

public class BeatmapInfo {
    ArrayList<String> idInfo;
    ArrayList<Integer> id;
    ArrayList<Float> difficulty;

    public BeatmapInfo() {
        this.idInfo = new ArrayList<>();
        this.id = new ArrayList<>();
        this.difficulty = new ArrayList<>();
    }

    public ArrayList<String> getIdInfo() { return idInfo; }

    public ArrayList<Integer> getId() { return id; }

    public ArrayList<Float> getDifficulty() { return difficulty; }

}
