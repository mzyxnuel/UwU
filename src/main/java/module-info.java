module uwu {
    requires javafx.controls;
    requires javafx.fxml;

    opens uwu.view to javafx.fxml;
    exports uwu;
    exports uwu.view to javafx.graphics;
}
