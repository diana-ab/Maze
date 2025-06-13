import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    //private static final int WINDOW_WIDTH = 514;
    //private static final int WINDOW_HEIGHT = 564;

    private MazePanel mazePanel;
    private JButton checkButton;
    private MazeSolver mazeSolver;
    private MazePointProvider pointProvider;


    public MainFrame() {
        this.setTitle("Maze Solver");
        //this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT); //<- מגדיר את גודל החלון באופן ידני
        this.setLayout(new BorderLayout());

        this.mazePanel = new MazePanel(MazePointProvider.WIDTH, MazePointProvider.HEIGHT);
        this.checkButton = new JButton("Check Solution");

        this.add(this.mazePanel, BorderLayout.CENTER);
        this.add(checkButton, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //  הגדרות כלליות שלא מושפעות מגודל החלון אבל מומלץ לקרוא לפני PACK כדי שהמערכת תחשב את הגודל האופטימלי בהיחשב בזה שהחלון לא ניתן לשינוי גודל
        this.setResizable(false);
        this.pack(); //חישוב גודל לפי תוכן -> צריך להיקרא אחרי שמוסיפים את הרכיבים לחלון כדי שיוכל לחשב את הגודל הנכון
        this.setLocationRelativeTo(null); //מושפע מגודל -> קוראים אחרי
        this.setVisible(true); //תמיד אחרון -> כבר אכלנו אותה עם הפילוסופים כשזה לא היה אחרון
        // שונה מיקום של כל המתודות כי הPACK מושפע משאר העצמים בחלון ובנוסף כל המתודות שמושפעות מגודל החלון הועברו מתחתיו על מנת שיפעלו באופן תקין.


        this.checkButton.addActionListener(e -> {
            this.pointProvider = this.mazePanel.getMazePointProvider(); //obtains the correct points provider that aligns with panel
            List<Point> whitePoints = this.pointProvider.getPointList(); //gets the correct white cell points
            int width = MazePointProvider.WIDTH;  //the width and height that the maze solver constructor needs
            int height = MazePointProvider.HEIGHT;

            this.mazeSolver = new MazeSolver(whitePoints, width, height);  //creating the maze solver object
            List<Point> solution = this.mazeSolver.solve(); // using the solve method to see if there exists a path from start to end and if yes making a solution list

            if (solution != null) {  //asking if the solution list is empty, if not then proceed to draw the solution path.
                this.mazePanel.setSolutionPath(solution); //setting the solution path into the setter in maze panel. the panel draw the path.
            } else {
                JOptionPane.showMessageDialog(this, "No path found from start to end.", "Maze Solver", JOptionPane.WARNING_MESSAGE);
                //JOptionPane - A built-in Swing class used for pop-up dialogs (messages, inputs, confirmations, etc.).
                //.showMessageDialog(...) -	A static method that shows a simple message dialog.
                //this - The parent component (MainFrame in your case), used to position the dialog window in the center of this frame.
                //"No path found from start to end." - The main message text that appears in the dialog.
                //"Maze Solver"	- The title of the dialog window (seen in the title bar).
                //JOptionPane.WARNING_MESSAGE - The icon type shown in the dialog: a yellow warning triangle.
            }
        });
    }
}

