package se.iths.labb3osedummestephen;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static se.iths.labb3osedummestephen.ShapeFactory.createShape;


public class Model {
    public StringProperty chatBoxInput = new SimpleStringProperty();
    public BooleanProperty selectButton = new SimpleBooleanProperty();
    public BooleanProperty circleButton = new SimpleBooleanProperty();
    public BooleanProperty rectangleButton = new SimpleBooleanProperty();
    public BooleanProperty ServerConnected = new SimpleBooleanProperty();

    public BooleanProperty serverConnectedProperty() {
        return ServerConnected;
    }

    public ObservableList<String> chatWindow = FXCollections.observableArrayList();
    public ObjectProperty<Color> colorPicker = new SimpleObjectProperty<>(Color.BLACK);
    public ObjectProperty<Integer> sizeSpinner = new SimpleObjectProperty<>(30);
    public Deque<Deque<Shape>> undoList = new ArrayDeque<>();
    public Deque<Deque<Shape>> redoList = new ArrayDeque<>();
    public ObservableList<Shape> shapeList = FXCollections.observableArrayList();
    public Server server = new Server();
    private double mouseX;
    private double mouseY;


    public BooleanProperty circleButtonProperty() {
        return circleButton;
    }

    public BooleanProperty rectangleButtonProperty() {
        return rectangleButton;
    }

    public StringProperty chatBoxInputProperty() {
        return chatBoxInput;
    }

    public ObjectProperty<Color> colorPickerProperty() {
        return colorPicker;
    }

    public BooleanProperty selectButtonProperty() {
        return selectButton;
    }

    public double getMouseX() {
        return mouseX;
    }

    public void setMouseX(double mouseX) {
        this.mouseX = mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public void setMouseY(double mouseY) {
        this.mouseY = mouseY;
    }

    public String getChatBoxInput() {
        return chatBoxInput.get();
    }

    public Integer getSizeSpinner() {
        return sizeSpinner.get();
    }

    public ObjectProperty<Integer> sizeSpinnerProperty() {
        return sizeSpinner;
    }

    public Color getColorPicker() {
        return colorPicker.get();
    }


    public void checkShapeAndDraw() {
        redoList.clear();
        updateUndoList();

        if (circleButtonProperty().getValue()) {
            createShapeAddToList();

        } else if (rectangleButtonProperty().getValue()) {
            createShapeAddToList();

        } else if (selectButtonProperty().getValue()) {
            replaceShape();
            replaceShapeList();
        }
    }

    private void updateUndoList() {
        undoList.add(copyShapeListToDeque());
    }

    private void replaceShape() {
        Shape tempShape = ShapeFactory.copyShapeUpdated(findShape(), this);
        shapeList.remove(findShape());
        shapeList.add(tempShape);
    }

    private Shape findShape() {
        return shapeList.stream()
                .filter(shape -> shape.findPosition(getMouseX(), getMouseY())).findAny().orElseThrow();
    }

    public void replaceShapeList() {
        ObservableList<Shape> tempList = FXCollections.observableArrayList();
        for (Shape shape : shapeList)
            tempList.add(ShapeFactory.copyShape(shape));
        updateShapeList(tempList);
    }

    public void updateShapeList(ObservableList<Shape> tempList) {
        shapeList.clear();
        shapeList.addAll(tempList);
    }

    public void createShapeAddToList() {
        if (circleButtonProperty().getValue()) {
            shapeList.add(ShapeFactory.createShape("circle", this));

        } else if (rectangleButtonProperty().getValue()) {
            shapeList.add(ShapeFactory.createShape("rectangle", this));
        }
    }

    public Shape lastShapeInList(List<Shape> list) {
        return list.get(list.size() - 1);

    }

    public Deque<Shape> copyShapeListToDeque() {
        Deque<Shape> tempDeque = new ArrayDeque<>();
        for (Shape shape : shapeList)
            tempDeque.add(ShapeFactory.copyShape(shape));
        return tempDeque;
    }


    public void importSvgString(String string) {
        try {
            shapeList.add(ShapeFactory.createShape(string));
        } catch (Exception ignored) {
        }

    }
}
