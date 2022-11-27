package se.iths.labb3osedummestephen;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    Model model = new Model();

    @Test
    void createCircleAddToShapeListCheckSizeCheckType() {
        model.sizeSpinnerProperty().setValue(10);
        model.colorPickerProperty().setValue(Color.BLACK);
        model.setMouseY(10);
        model.setMouseX(10);

        model.circleButtonProperty().setValue(true);
        model.checkShapeAndDraw();

        var expected = 1;
        var actual = model.shapeList.size();
        var expectedType = Circle.class;
        var actualType = model.shapeList.get(0).getClass();

        assertEquals(expected,actual);
        assertEquals(expectedType,actualType);
    }
    @Test
    void createRectangleAddToShapeListCheckSizeCheckType() {
        model.sizeSpinnerProperty().setValue(10);
        model.colorPickerProperty().setValue(Color.BLACK);
        model.setMouseY(10);
        model.setMouseX(10);

        model.rectangleButtonProperty().setValue(true);
        model.checkShapeAndDraw();

        var expected = 1;
        var actual = model.shapeList.size();
        var expectedType = Rectangle.class;
        var actualType = model.shapeList.get(0).getClass();

        assertEquals(expected,actual);
        assertEquals(expectedType,actualType);

    }

    @Test
    void changeSizeAndColorOfShapeAddOldShapeToUndoList() {
        model.sizeSpinnerProperty().setValue(10);
        model.colorPickerProperty().setValue(Color.BLUE);
        model.setMouseY(10);
        model.setMouseX(10);
        Circle circle = new Circle(1,10,10,Color.BLACK);
        model.shapeList.add(circle);
        model.selectButtonProperty().setValue(true);

        model.checkShapeAndDraw();

        assertEquals(10,model.shapeList.get(0).getSize());
        assertEquals(Color.BLUE, model.shapeList.get(0).getColor());

        assertEquals(1, model.undoList.getFirst().getFirst().getSize());
        assertEquals(Color.BLACK, model.undoList.getFirst().getFirst().getColor());

    }


}
