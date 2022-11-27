package se.iths.labb3osedummestephen;

/*import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}*/
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class HelloController {
    @FXML
    public Spinner<Integer> sizeSpinner;
    @FXML
    public Label chatLabel;
    @FXML
    public Button sendShape;
    @FXML
    public Label serverConnected;
    @FXML
    private Button sendButton;
    @FXML
    private TextField chatBoxInput;
    @FXML
    private ListView<String> chatWindow;
    @FXML
    private ToggleButton selectButton;
    @FXML
    private ToggleButton rectangleButton;
    @FXML
    public ToggleButton circleButton;
    @FXML
    public Canvas canvas;
    @FXML
    private ColorPicker colorPicker;

    public GraphicsContext context;
    public Stage stage;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    Model model = new Model();
    SvgFileWriter svgFileWriter = new SvgFileWriter();


    public void initialize() {
        setToggleGroup();
        context = canvas.getGraphicsContext2D();
        model.shapeList.addListener((ListChangeListener<Shape>) onChange -> clearCanvasDrawShapes());
        setupChat();
        setupServerLabel();
        bindTool();
        bindButton();
    }

    private void setupServerLabel() {
        serverConnected.disableProperty().bind(model.serverConnectedProperty());
        model.serverConnectedProperty().setValue(true);
    }

    private void setupChat() {
        chatWindow.setItems(model.chatWindow);
        chatBoxInput.textProperty().bindBidirectional(model.chatBoxInputProperty());
    }

    private void bindTool() {
        colorPicker.valueProperty().bindBidirectional(model.colorPickerProperty());
        sizeSpinner.getValueFactory().valueProperty().bindBidirectional(model.sizeSpinnerProperty());
    }

    private void bindButton() {
        selectButton.selectedProperty().bindBidirectional(model.selectButtonProperty());
        circleButton.selectedProperty().bindBidirectional(model.circleButtonProperty());
        rectangleButton.selectedProperty().bindBidirectional(model.rectangleButtonProperty());
        sendButton.disableProperty().bind(model.chatBoxInputProperty().isEmpty());

    }

    private void setToggleGroup() {
        ToggleGroup toggleGroup = new ToggleGroup();
        selectButton.setToggleGroup(toggleGroup);
        circleButton.setToggleGroup(toggleGroup);
        rectangleButton.setToggleGroup(toggleGroup);

    }

    public void connectServer() {
        model.server.connect(model);
    }

    public void saveToFile() {
        svgFileWriter.saveToFile(model, stage);
    }

    public void exitProgram() {
        System.exit(0);
    }


    public void undo() {
        if (model.undoList.isEmpty()) {
            model.shapeList.clear();
            return;
        }
        model.redoList.addLast(model.copyShapeListToDeque());
        model.shapeList.clear();
        model.shapeList.addAll(model.undoList.removeLast());

        clearCanvasDrawShapes();
    }

    public void redo() {
        if (model.redoList.isEmpty())
            return;

        model.undoList.addLast(model.copyShapeListToDeque());
        model.shapeList.clear();
        model.shapeList.addAll(model.redoList.removeLast());

        clearCanvasDrawShapes();
    }


    public void clearCanvasDrawShapes() {
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawShapes(context);
    }

    public void canvasClicked(MouseEvent mouseEvent) {

        model.setMouseX(mouseEvent.getX());
        model.setMouseY(mouseEvent.getY());

        model.checkShapeAndDraw();
        clearCanvasDrawShapes();
    }

    public void drawShapes(GraphicsContext context) {
        for (var shape : model.shapeList) {
            shape.draw(context);
        }
    }

    public void sendMessage() {
        model.server.sendMessage(model.getChatBoxInput(),model);
        chatBoxInput.clear();
    }

    public void onEnter() {
        model.server.sendMessage(model.getChatBoxInput(),model);
        chatBoxInput.clear();
    }

    public void sendShape() {
        model.server.sendMessage(model.lastShapeInList(model.shapeList).toSVG(),model);
    }

}