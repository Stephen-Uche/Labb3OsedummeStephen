package se.iths.labb3osedummestephen;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {


    public Circle(int size, double xPosition, double yPosition, Color color) {
        super(size, xPosition, yPosition, color);
    }

    public Circle(Shape shape) {
        super(shape);
    }


    public boolean findPosition(double mouseX, double mouseY) {
        return isWithinShape(mouseX, mouseY);

    }

    public boolean isWithinShape(double mouseX, double mouseY) {
        double distanceX = mouseX - getXPosition();
        double distanceY = mouseY - getYPosition();
        if (distanceY < 0)
            distanceY = distanceY * -1;
        else if (distanceX < 0)
            distanceX = distanceX * -1;

        boolean insideX = distanceX < getSize() / 2.0;
        boolean insideY = distanceY < getSize() / 2.0;
        return insideX && insideY;

    }

    @Override
    public String toSVG() {
        String svgColorCode = "#" + getColor().toString().substring(2, 10);
        return "<circle cx=\"" + getXPosition() + "\" cy=\"" + getYPosition() + "\" r=\"" + getSize() + "\" fill=\"" + svgColorCode + "\"/>";

    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillOval(centerX(), centerY(), getSize(), getSize());
    }
}

