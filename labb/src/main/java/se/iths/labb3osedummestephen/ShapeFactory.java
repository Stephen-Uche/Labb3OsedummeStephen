package se.iths.labb3osedummestephen;

import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShapeFactory {
    static Pattern regexColor = Pattern.compile("fill=.{10}");
    static Pattern regexX = Pattern.compile("x=.\\d+");
    static Pattern regexY = Pattern.compile("y=.\\d+");
    static Pattern regexRadius = Pattern.compile("r=.\\d+");
    static Pattern regexSize = Pattern.compile("width=.\\d+");


    public static Shape createShape(String shape, Model model) {
        if (shape.contains("circle"))
            return createNewCircle(model);
        else if (shape.contains("rectangle")) {
            return createNewRectangle(model);
        }
        return null;
    }

    public static Shape createShape(String SVGString) {

        if (SVGString.contains("circ"))
            return createCircleFromSVG(SVGString);
        else if (SVGString.contains("rect")) {
            return createRectangleFromSVG(SVGString);
        }
        return null;
    }

    private static Rectangle createRectangleFromSVG(String SVGString) {
        return new Rectangle(Integer.parseInt(findSVGValue(SVGString, regexSize)), Double.parseDouble(findSVGValue(SVGString, regexX)), Double.parseDouble(findSVGValue(SVGString, regexY)), Color.valueOf(findSVGValue(SVGString, regexColor)));
    }

    private static Circle createCircleFromSVG(String SVGString) {
        return new Circle(Integer.parseInt(findSVGValue(SVGString, regexRadius)), Double.parseDouble(findSVGValue(SVGString, regexX)), Double.parseDouble(findSVGValue(SVGString, regexY)), Color.valueOf(findSVGValue(SVGString, regexColor)));
    }


    public static Shape copyShape(Shape shape) {
        if (shape.getClass() == Circle.class)
            return new Circle(shape);

        else if (shape.getClass() == Rectangle.class) {
            return new Rectangle(shape);
        } else return null;
    }

    public static Shape copyShapeUpdated(Shape shape, Model model) {
        if (shape.getClass() == Circle.class)
            return createNewCircleChanged(shape, model);

        else if (shape.getClass() == Rectangle.class) {
            return createNewRectangleChanged(shape, model);
        } else return null;
    }

    public static Circle createNewCircle(Model model) {
        return new Circle(model.getSizeSpinner(), model.getMouseX(), model.getMouseY(), model.getColorPicker());
    }


    public static Rectangle createNewRectangle(Model model) {
        return new Rectangle(model.getSizeSpinner(), model.getMouseX(), model.getMouseY(), model.getColorPicker());
    }

    public static Rectangle createNewRectangleChanged(Shape shape, Model model) {
        return new Rectangle(model.getSizeSpinner(), shape.getXPosition(), shape.getYPosition(), model.getColorPicker());
    }

    public static Circle createNewCircleChanged(Shape shape, Model model) {
        return new Circle(model.getSizeSpinner(), shape.getXPosition(), shape.getYPosition(), model.getColorPicker());
    }

    public static String findSVGValue(String string, Pattern pattern) {
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            String found = matcher.group(0);
            List<String> foundStrings = Arrays.stream(found.split("=\"")).toList();
            return foundStrings.get(1);
        }
        return "";
    }
}

