package AP_Lab2.CircleTest;

import java.util.*;
import java.util.stream.Collectors;

enum TYPE {
    POINT,
    CIRCLE
}

enum DIRECTION {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

class ObjectCanNotBeMovedException extends Exception {
    ObjectCanNotBeMovedException(int x, int y) {
        super(String.format("Point (%d,%d) is out of bounds", x, y));
    }
}

class MovableObjectNotFittableException extends Exception {
    MovableObjectNotFittableException(String msg) {
        super(msg);
    }
}

interface Movable {
    public void moveUp() throws ObjectCanNotBeMovedException;

    public void moveDown() throws ObjectCanNotBeMovedException;

    public void moveRight() throws ObjectCanNotBeMovedException;

    public void moveLeft() throws ObjectCanNotBeMovedException;

    public boolean isFittable();

    public int getCurrentXPosition();

    public int getCurrentYPosition();

    public TYPE getType();

}

class MovablePoint implements Movable {
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;

    public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public MovablePoint(MovablePoint other) {
        this.x = other.x;
        this.y = other.y;
        this.xSpeed = other.x;
        this.ySpeed = other.ySpeed;
    }

    @Override
    public boolean isFittable() {
        if ((x < 0 || x > MovablesCollection.X_MAX)
                || (y < 0 || x > MovablesCollection.Y_MAX)) return false;
        return true;
    }


    @Override
    public void moveUp() throws ObjectCanNotBeMovedException {
        if (y < 0 || y + ySpeed > MovablesCollection.Y_MAX) throw new ObjectCanNotBeMovedException(x, y+ySpeed);

        this.y += this.ySpeed;
    }

    @Override
    public void moveDown() throws ObjectCanNotBeMovedException {
        if (y - ySpeed < 0 || (y > MovablesCollection.Y_MAX)) throw new ObjectCanNotBeMovedException(x, y - ySpeed);

        this.y -= this.ySpeed;
    }

    @Override
    public void moveRight() throws ObjectCanNotBeMovedException {
        if (x < 0 || x + xSpeed > MovablesCollection.X_MAX) throw new ObjectCanNotBeMovedException(x+xSpeed, y);

        this.x += this.xSpeed;
    }

    @Override
    public void moveLeft() throws ObjectCanNotBeMovedException {
        if (x - xSpeed < 0 || x > MovablesCollection.X_MAX) throw new ObjectCanNotBeMovedException(x-xSpeed, y);

        this.x -= this.xSpeed;
    }

    @Override
    public int getCurrentXPosition() {
        return x;
    }

    @Override
    public int getCurrentYPosition() {
        return y;
    }

    @Override
    public TYPE getType() {
        return TYPE.POINT;
    }

    @Override
    public String toString() {
        return "Movable point with coordinates (" + x + "," + y + ")";
    }
}

class MovableCircle implements Movable {

    private int radius;
    private MovablePoint center;

    public MovableCircle(int radius, MovablePoint center) {
        this.radius = radius;
        this.center = center;
    }

    @Override
    public boolean isFittable() {
        int x = center.getCurrentXPosition();
        int y = center.getCurrentYPosition();
        int r = getRadius();

        if ((x-r < 0 || x+r > MovablesCollection.X_MAX)
                || (y-r < 0 || y+r > MovablesCollection.Y_MAX)) return false;
        return true;
    }

    public void moveUp() throws ObjectCanNotBeMovedException {
        int x = getCurrentXPosition(), y = getCurrentYPosition();
        if(y < 0 || y + radius > MovablesCollection.Y_MAX) {
            throw new ObjectCanNotBeMovedException(x, y);
        }
        center.moveUp();
    }

    public void moveDown() throws ObjectCanNotBeMovedException {
        int x = getCurrentXPosition(), y = getCurrentYPosition();
        if(y - radius < 0 || y > MovablesCollection.Y_MAX) {
            throw new ObjectCanNotBeMovedException(x, y);
        }
        center.moveDown();
    }

    public void moveRight() throws ObjectCanNotBeMovedException {
        int x = getCurrentXPosition(), y = getCurrentYPosition();
        if(x < 0 || x + radius> MovablesCollection.X_MAX) {
            throw new ObjectCanNotBeMovedException(x, y);
        }
        center.moveRight();
    }

    public void moveLeft() throws ObjectCanNotBeMovedException {
        int x = getCurrentXPosition(), y = getCurrentYPosition();
        if(x - radius < 0 || x > MovablesCollection.X_MAX) {
            throw new ObjectCanNotBeMovedException(x, y);
        }
        center.moveLeft();
    }

    public int getCurrentXPosition() {
        return center.getCurrentXPosition();
    }

    public int getCurrentYPosition() {
        return center.getCurrentYPosition();
    }

    @Override
    public TYPE getType() {
        return TYPE.CIRCLE;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Movable circle with center coordinates (");
        sb.append(center.getCurrentXPosition()).append(",").append(center.getCurrentYPosition()).append(") and radius ").append(radius);

        return sb.toString();
    }
}

class MovablesCollection {
    private Movable[] movable;
    public static int X_MAX;
    public static int Y_MAX;

    public MovablesCollection() {
        this.X_MAX = 0;
        this.Y_MAX = 0;
    }

    public MovablesCollection(int xMax, int yMax) {
        this.X_MAX = xMax;
        this.Y_MAX = yMax;
        movable = new Movable[0];
    }

    public static void setxMax(int xMax) {
        X_MAX = xMax;
    }

    public static void setyMax(int yMax) {
        Y_MAX = yMax;
    }

//    public void addMovableObject(Movable m) throws MovableObjectNotFittableException { // this is a better version with polymorphism but because of formatting I have to use the substitute
//        if (!m.isFittable()) {
//            throw new MovableObjectNotFittableException(m + " can not be fitted into the collection");
//        } else {
//            movable = Arrays.copyOf(movable, movable.length + 1);
//            movable[movable.length - 1] = m;
//        }
//    }

    void addMovableObject(Movable m) throws MovableObjectNotFittableException {
        int x = m.getCurrentXPosition();
        int y = m.getCurrentYPosition();

        MovablePoint point = m instanceof MovablePoint ? ((MovablePoint) m) : null;
        MovableCircle circle = m instanceof MovableCircle ? ((MovableCircle) m) : null;

        if(point != null) {
            if(x < 0 || x > X_MAX) {
                throw new MovableObjectNotFittableException(m + " can not be fitted into the collection");
            }
            if(y < 0 || y > Y_MAX) {
                throw new MovableObjectNotFittableException(m + " can not be fitted into the collection");
            }
        } else if(circle != null) {
            int r = circle.getRadius();
            if(x - r < 0 || x + r > X_MAX) {
                throw new MovableObjectNotFittableException("Movable circle with center (" + x + "," + y + ") and radius " + r + " can not be fitted into the collection");
            }
            if(y - r < 0 || y + r > Y_MAX) {
                throw new MovableObjectNotFittableException("Movable circle with center (" + x + "," + y + ") and radius " + r + " can not be fitted into the collection");
            }
        }

//        MovableCircle circle = (MovableCircle)m;

        Movable [] newMovable = Arrays.copyOf(movable, movable.length + 1);
        newMovable[newMovable.length - 1] = m;
        movable = newMovable;
    }




    public void moveObjectsFromTypeWithDirection(TYPE type, DIRECTION direction) throws ObjectCanNotBeMovedException {
        for (Movable m : movable) {
            if (m.getType() == type) {
                switch (direction) {
                    case UP:
                        m.moveUp();
                        break;
                    case DOWN:
                        m.moveDown();
                        break;
                    case LEFT:
                        m.moveLeft();
                        break;
                    case RIGHT:
                        m.moveRight();
                        break;
                }
            }
        }
    }

    public String toString() {
        return String.format("Collection of movable objects with size %d:\n", movable.length) + Arrays.stream(movable)
                .map(Movable::toString)
                .collect(Collectors.joining("\n")) + "\n";
    }

}

class CirclesTest {

    public static void main(String[] args) {

        System.out.println("===COLLECTION CONSTRUCTOR AND ADD METHOD TEST===");
        MovablesCollection collection = new MovablesCollection(100, 100);
        Scanner sc = new Scanner(System.in);
        int samples = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < samples; i++) {
            String inputLine = sc.nextLine();
            String[] parts = inputLine.split(" ");

            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            int xSpeed = Integer.parseInt(parts[3]);
            int ySpeed = Integer.parseInt(parts[4]);

            if (Integer.parseInt(parts[0]) == 0) { //point
                try {
                    collection.addMovableObject(new MovablePoint(x, y, xSpeed, ySpeed));
                } catch (MovableObjectNotFittableException e) {
                    System.out.println(e.getLocalizedMessage());
                }

            } else { //circle
                int radius = Integer.parseInt(parts[5]);
                try {
                    collection.addMovableObject(new MovableCircle(radius, new MovablePoint(x, y, xSpeed, ySpeed)));
                } catch (MovableObjectNotFittableException e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }

        }
        System.out.println(collection.toString());

        System.out.println("MOVE POINTS TO THE LEFT");
        try {
            collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.LEFT);
        } catch (ObjectCanNotBeMovedException e) {
            System.out.println(e.getLocalizedMessage());
        }
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES DOWN");
        try {
            collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);
        } catch (ObjectCanNotBeMovedException e) {
            System.out.println(e.getLocalizedMessage());
        }
        System.out.println(collection.toString());

        System.out.println("CHANGE X_MAX AND Y_MAX");
        MovablesCollection.setxMax(90);
        MovablesCollection.setyMax(90);

        System.out.println("MOVE POINTS TO THE RIGHT");
        try {
            collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.RIGHT);
        } catch (ObjectCanNotBeMovedException e) {
            System.out.println(e.getLocalizedMessage());
        }
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES UP");
        try {
            collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.UP);
        } catch (ObjectCanNotBeMovedException e) {
            System.out.println(e.getLocalizedMessage());
        }
        System.out.println(collection.toString());
    }
}
