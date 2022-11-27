package se.iths.labb3osedummestephen;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;


public abstract class Shape {
    private final int size;
    private double xPosition;

    private double yPosition;
    private final Color color;

    public Shape(int size, double xPosition, double yPosition, Color color) {
        this.size = size;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
    }

    public Shape(Shape shape) {
        this.size = shape.size;
        this.xPosition = shape.xPosition;
        this.yPosition = shape.yPosition;
        this.color = shape.color;
    }

    public Shape(int size, Color color) {
        this.size = size;
        this.color = color;
    }


    public int getSize() {
        return size;
    }


    public double getXPosition() {
        return xPosition;
    }


    public double getYPosition() {
        return yPosition;
    }


    public Color getColor() {
        return color;
    }


    public double centerY() {
        return getYPosition() - (getSize() / 2.0);
    }

    public double centerX() {
        return getXPosition() - (getSize() / 2.0);
    }

    public abstract void draw(GraphicsContext context);

    public abstract boolean findPosition(double mouseX, double mouseY);

    public abstract boolean isWithinShape(double mouseX, double mouseY);


    public abstract String toSVG();

    @Override
    public String toString() {
        return "Shape{" +
                "size=" + size +
                ", xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                ", color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shape shape)) return false;
        return size == shape.size && Double.compare(shape.xPosition, xPosition) == 0 && Double.compare(shape.yPosition, yPosition) == 0 && Objects.equals(color, shape.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, xPosition, yPosition, color);
    }
}
