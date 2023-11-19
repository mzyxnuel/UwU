module UwU {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	
	opens view.client to javafx.graphics, javafx.fxml;
}
