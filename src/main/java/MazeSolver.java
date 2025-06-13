import java.awt.*;
import java.util.*;
import java.util.List;

public class MazeSolver {

    private Set<Point> allowedPoints;
    private Set<Point> visitedPoints;
    private Map<Point, Point> previousStep;
    private int width;
    private int height;
    private Point start;
    private Point end;


    public MazeSolver(List<Point> allowedPoints, int width, int height) {
        this.allowedPoints = new HashSet<>(allowedPoints);
        this.visitedPoints = new HashSet<>();
        this.previousStep = new HashMap<>();
        this.width = width;
        this.height = height;
        this.start = new Point(0, 0);
        this.end = new Point(width - 1, height - 1);
    }

    public List<Point> solve() {

        if (!isValidStartAndEnd(this.start, this.end)) {
            return null;
        }
        this.exploreMaze(this.start, this.end);

        return buildPath(this.end);
    }

    private void exploreMaze(Point start, Point end) {
        Queue<Point> pointQueue = new LinkedList<>(); // תור FIFO מקורס מבני נתונים
        pointQueue.add(start);
        this.visitedPoints.add(start);
        while (!pointQueue.isEmpty()) {
            Point current = pointQueue.poll();

            if (current.equals(end)) {
                break;
            }
            List<Point> neighbors = getValidNeighbors(current);
            for (Point neighbor : neighbors) {
                if (!isVisited(neighbor)) {
                    pointQueue.add(neighbor);
                    this.visitedPoints.add(neighbor);
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
            addIfValidNeighbor(neighbors, neighbor);
        }
        return neighbors;
    }

    private void addIfValidNeighbor(List<Point> neighbors, Point point) {
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

    private boolean isVisited(Point point) {
        if (this.visitedPoints.contains(point)) {
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
            currentPoint = newCurrentPoint;
        }
        return path;
    }
}