module com.example.clientservergui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.clientservergui to javafx.fxml;
    exports com.example.clientservergui;
}