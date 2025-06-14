import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class MainFrame extends JFrame {
    private MazePanel mazePanel;
    private JButton checkButton;
    private MazeSolver mazeSolver;
    private MazePointProvider pointProvider;


    public MainFrame() {
        this.setTitle("Maze Solver");
        this.setLayout(new BorderLayout());
        this.mazePanel = new MazePanel(MazePointProvider.WIDTH, MazePointProvider.HEIGHT);
        this.checkButton = new JButton("Check Solution");
        this.add(this.mazePanel, BorderLayout.CENTER);
        this.add(checkButton, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.checkButton.addActionListener(buttonListener());
    }

    private ActionListener buttonListener() {
        return e -> {
            this.pointProvider = this.mazePanel.getMazePointProvider();
            List<Point> whitePoints = this.pointProvider.getPointList();
            int width = MazePointProvider.WIDTH;
            int height = MazePointProvider.HEIGHT;
            this.mazeSolver = new MazeSolver(whitePoints, width, height);
            List<Point> solution = this.mazeSolver.solve();
            if (solution != null) {
                this.mazePanel.setSolutionPath(solution);
            } else {
                JOptionPane.showMessageDialog(this, "No path found from start to end.", "Maze Solver", JOptionPane.WARNING_MESSAGE);
            }
        };

    }
}

