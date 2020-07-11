import java.io.*;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class BeatmapLoader {

    private static final String BEATMAP_ID_LINE = "BeatmapID:";
    private static URL LOCAL_SERVER_KEY = null;

    public static String bmIdLoader(File folder) throws IOException {
        File[] bmFiles = folder.listFiles();
        for(File x : bmFiles) {
            if(x.getName().endsWith(".osu")) {
                FileReader fTemp = new FileReader(x);
                BufferedReader bTemp = new BufferedReader(fTemp);
                String result;
                while( (result=bTemp.readLine()) != null) {
                    if(result.startsWith(BEATMAP_ID_LINE))
                        return result;
                }
            }
        }
        return null;
    }

    public static void bmDifficultyCalculator(int id) throws IOException, InterruptedException {

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
                System.out.println(responseContent);
            }
        } catch (SocketTimeoutException e) { System.out.println(e.getMessage()); }
        Thread.sleep(20);
    }
}