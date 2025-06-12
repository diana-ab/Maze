import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 550;
//    private static final int WINDOW_X = 0;
//    private static final int WINDOW_Y = 0;


    private MazePanel mazePanel;
    private JButton checkButton;


    public MainFrame() {
        this.setTitle("Maze Solver");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        this.mazePanel = new MazePanel( MazePointProvider.WIDTH, MazePointProvider.HEIGHT);
        this.checkButton = new JButton("Check Solution");

        this.add(this.mazePanel, BorderLayout.CENTER);
        this.add(checkButton, BorderLayout.SOUTH);


        this.setVisible(true);

    }
}
