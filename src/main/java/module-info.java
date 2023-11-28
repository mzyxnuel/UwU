module uwu {
    requires javafx.controls;
    requires javafx.fxml;

    opens uwu to javafx.fxml;
    exports uwu;
}
