import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MazePointProvider {
    private static final String API_URL = "https://app.seker.live/fm1/get-points";
    private static final String X = "x";
    private static final String Y = "y";
    private static final String KEY_WIDTH = "width";
    private static final String KEY_HEIGHT = "height";
    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;

    private List<Point> pointList;

    public MazePointProvider() {
        this.pointList = new ArrayList<>();
        this.fetchMazePointsFromAPI();

    }

    private void fetchMazePointsFromAPI() {
        try {
            HttpResponse<String> response = Unirest.get(API_URL)
                    .queryString(KEY_WIDTH, WIDTH).queryString(KEY_HEIGHT, HEIGHT)
                    .asString();
            JSONArray array = new JSONArray(response.getBody());
            for (int i = 0; i < array.length(); i++) {
                JSONObject point = array.getJSONObject(i);
                int x = point.getInt(X);
                int y = point.getInt(Y);
                Point point1 = new Point(x, y);
                this.pointList.add(point1);
                System.out.println("x: " + x + ", y: " + y);
            }

        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Point> getPointList() {
        return this.pointList;
    }
}
