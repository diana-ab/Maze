import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MazePanel extends JPanel {
    private static final int SCALE_UP = 5;

    private MazeImageCreator mazeImage;
    private MazePointProvider mazePointProvider;
    private BufferedImage newMaze;


    public MazePanel( int width, int height) {
        //this.setBounds(x, y, width, height);
        this.setPreferredSize(new Dimension(width * SCALE_UP, height * SCALE_UP));

        this.mazePointProvider = new MazePointProvider();
        this.mazeImage = new MazeImageCreator(this.mazePointProvider);
        this.newMaze = this.mazeImage.getMazeImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.newMaze,
                this.getX(),
                this.getY(),
                this.newMaze.getWidth() * SCALE_UP,
                this.newMaze.getHeight()* SCALE_UP,
                null);
    }

}
