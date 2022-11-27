package se.iths.labb3osedummestephen;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape {


    public Rectangle(int size, double xPosition, double yPosition, Color color) {
        super(size, xPosition, yPosition, color);
    }

    public Rectangle(Shape shape) {
        super(shape);
    }

    @Override
    public boolean findPosition(double mouseX, double mouseY) {
        return isWithinShape(mouseX, mouseY);
    }

    public boolean isWithinShape(double mouseX, double mouseY) {

        boolean insideX = mouseX <= centerX() + getSize() && mouseX >= centerX();
        boolean insideY = mouseY <= centerY() + getSize() && mouseY >= centerY();
        return insideX && insideY;
    }

    @Override
    public String toSVG() {
        String svgColorCode = "#" + getColor().toString().substring(2, 10);
        return "<rect fill=\"" + svgColorCode + "\" stroke=\"" + svgColorCode + "\" x=\"" + getXPosition() +
                "\" y=\"" + getYPosition() + "\" width=\"" + getSize() + "\" height=\"" + getSize() + "\"/>";

    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillRect(centerX(), centerY(), getSize(), getSize());


    }


}

