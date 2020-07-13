import java.io.*;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class BeatmapLoader {

    private static final String BEATMAP_ID_LINE = "BeatmapID:";
    private static URL LOCAL_SERVER_KEY = null;

    public static ArrayList<String> bmIdLoader(File folder) throws IOException {
        File[] bmFiles = folder.listFiles();
        ArrayList<String> result = new ArrayList<>();
        int i = 0;
        for(File x : bmFiles) {
            if(x.getName().endsWith(".osu")) {
                FileReader fTemp = new FileReader(x);
                BufferedReader bTemp = new BufferedReader(fTemp);
                String temp;
                while( (temp=bTemp.readLine()) != null) {
                    if(temp.startsWith(BEATMAP_ID_LINE))
                        result.add(temp);
                }
            }
            i++;
        }
        return result;
    }

    public static float bmDifficultyCalculator(int id) throws IOException, InterruptedException {
        float result = -1;
        try {
            String url = "http://localhost:3000/" + id;
            LOCAL_SERVER_KEY = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) LOCAL_SERVER_KEY.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            if (connection.getResponseCode() == 200) {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuffer responseContent = new StringBuffer();
                while ((line = buffer.readLine()) != null) {
                    responseContent.append(line);
                }
                buffer.close();
                System.out.println(responseContent.substring(9, responseContent.length()-1));
                result = Float.parseFloat(responseContent.substring(9, responseContent.length()-1));
                result = Float.parseFloat(new DecimalFormat("0.00").format(result).replace(",","."));
            }
        } catch (SocketTimeoutException e) { System.out.println(e.getMessage()); }
        Thread.sleep(20);
        return result;
    }
}
