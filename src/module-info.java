module UwU {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens view.client to javafx.graphics, javafx.fxml;
}
