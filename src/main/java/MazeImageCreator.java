import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class MazeImageCreator {
    private BufferedImage mazeImage;
    private MazePointProvider mazePointProvider;
    private List<Point> pointList;

    public MazeImageCreator(MazePointProvider mazePointProvider) {
        this.mazePointProvider = mazePointProvider;
        this.pointList = mazePointProvider.getPointList();

        this.mazeImage = new BufferedImage(
                mazePointProvider.WIDTH,
                mazePointProvider.HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        this.drawMaze();
    }
    private boolean ifPointExists(Point point) {
        if (this.pointList.contains(point)) {
            return true;
        } else {
            return false;
        }
    }
    private void drawMaze() {
        for (int x = 0; x < this.mazeImage.getWidth(); x++) {
            for (int y = 0; y < mazeImage.getHeight(); y++) {
                Point tempPoint = new Point(x, y);
                Color color = new Color(Color.BLACK.getRGB());
                if (ifPointExists(tempPoint)) {
                    color = new Color(Color.WHITE.getRGB());
                }
                this.mazeImage.setRGB(x, y, color.getRGB());
            }
        }
    }

    public BufferedImage getMazeImage() {
        return mazeImage;
    }
}
