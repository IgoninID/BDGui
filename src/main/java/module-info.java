module com.classig.dbgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.classig.dbgui to javafx.fxml;
    exports com.classig.dbgui;
}