import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class MazePanel extends JPanel {
    private static final int SCALE_UP = 5;

    private MazeImageCreator mazeImage;
    private MazePointProvider mazePointProvider;
    private BufferedImage newMaze;
    private List<Point> solutionPath;


    public MazePanel(int width, int height) {
        this.setPreferredSize(new Dimension(width * SCALE_UP, height * SCALE_UP));
        this.mazePointProvider = new MazePointProvider();
        this.mazeImage = new MazeImageCreator(this.mazePointProvider);
        this.newMaze = this.mazeImage.getMazeImage();
    }
    public void setSolutionPath(List<Point> solutionPath) {
        this.solutionPath = solutionPath;
        repaint();
    }
    public MazePointProvider getMazePointProvider() {
        return mazePointProvider;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.newMaze,
                this.getX(),
                this.getY(),
                this.newMaze.getWidth() * SCALE_UP,
                this.newMaze.getHeight() * SCALE_UP,
                null);
        if (this.solutionPath != null) {
            g.setColor(Color.GREEN);
            for (Point point : this.solutionPath) {
                g.fillOval(point.x * SCALE_UP, point.y * SCALE_UP, SCALE_UP, SCALE_UP);
            }
        }
    }
}
