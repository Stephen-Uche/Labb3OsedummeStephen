module com.example.labb {
    requires javafx.controls;
    requires javafx.fxml;


    opens se.iths.labb3osedummestephen to javafx.fxml;
    exports se.iths.labb3osedummestephen;
}