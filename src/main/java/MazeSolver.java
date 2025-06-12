import java.awt.*;
import java.util.*;
import java.util.List;

public class MazeSolver {

    private Set<Point> allowedPoints;
    private Set<Point> vistedPoint;
    private Map<Point, Point> previousStep;
    private int width;
    private int height;


    public MazeSolver(List<Point> allowedPoints, int width, int height) {
        this.allowedPoints = new HashSet<>(allowedPoints);
        this.vistedPoint = new HashSet<>();
        this.previousStep = new HashMap<>();
        this.width = width;
        this.height = height;
    }

    public List<Point> solve() {
        Point start = new Point(this.width - this.width, this.height - this.height);
        Point end = new Point(this.width - 1, this.height - 1);

        if (!isValidStartAndEnd(start, end)) {
            return null;
        }
        this.exploreMaze(start, end);

        return buildPath(end)


    }

    private void exploreMaze(Point start, Point end) {
        Queue<Point> pointQueue = new LinkedList<>(); // תור FIFO מקורס מבני נתונים
        pointQueue.add(start);
        this.vistedPoint.add(start);
        while (!pointQueue.isEmpty()) {
            Point current = pointQueue.poll();

            if (current.equals(end)) {
                break;
            }
            List<Point> neighbors = getValidNeighbors(current);
            for (Point neighbor : neighbors) {
                if (!ifVisited(neighbor)) {
                    pointQueue.add(neighbor);
                    this.vistedPoint.add(neighbor);
                    this.previousStep.put(neighbor, current);
                }
            }
        }
    }

    private boolean isValidStartAndEnd(Point start, Point end) {
        return isPointAllowed(start) && isPointAllowed(end);
    }

    private boolean isPointAllowed(Point p) {
        return allowedPoints.contains(p);
    }

    private List<Point> getValidNeighbors(Point current) {
        List<Point> neighbors = new ArrayList<>();// פה הוא יקבל רשימה של השכנים
        List<Point> directions = List.of(
                new Point(1, 0),  // right
                new Point(-1, 0), // left
                new Point(0, 1),  // down
                new Point(0, -1)  //up
        );
        for (Point dir : directions) {
            Point neighbor = new Point(current.x + dir.x, current.y + dir.y);
            addNeighborsIfValid(neighbors, neighbor);
        }
        return neighbors;
    }

    private void addNeighborsIfValid(List<Point> neighbors, Point point) {
        if (isInBounds(point) && isPointAllowed(point)) {
            neighbors.add(point);
        }
    }

    private boolean isInBounds(Point point) {
        if (point.x >= 0 && point.x < this.width && point.y >= 0 && point.y < this.height) {
            return true;
        } else {
            return false;
        }
    }

    private boolean ifVisited(Point point) {
        if (this.vistedPoint.contains(point)) {
            return true;
        }
        return false;
    }

    private List<Point> buildPath(Point end) {
        if (!this.previousStep.containsKey(end)) {
            return null;
        }

        List<Point> path = new LinkedList<>();
        Point currentPoint = end;

        while (currentPoint != null) {
            path.addFirst(currentPoint);
            Point newCurrentPoint = this.previousStep.get(currentPoint);
            currentPoint=newCurrentPoint;
        }
        return path;
    }
}