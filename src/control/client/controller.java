package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.animation.TranslateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.util.Duration;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class controller {

		@FXML
    	private FlowPane flowPane;
    	
		private Stage stage;
		private Scene scene;
		private Parent root;
	    @FXML
	    private AnchorPane menuPane;

	    @FXML
	    private ImageView baozi;

	    @FXML
	    private ImageView edamame;

	    @FXML
	    private ImageView gyoza;

	    @FXML
	    private ImageView tartare;

	    @FXML
	    private ImageView tempura;

	    @FXML
	    private ImageView shrimp;
	    
	    @FXML
	    private ImageView frice;

	    @FXML
	    private ImageView maki;

	    @FXML
	    private ImageView nigiri;

	    @FXML
	    private ImageView noodles;

	    @FXML
	    private ImageView uramaki;
	    
	    @FXML
	    private ImageView temaki;

	    @FXML
	    private ImageView teriyaki;
	    
	    
	    private boolean isMenuVisible = false;

	    private List<ImageView> antipastiImages;
	    private List<ImageView> primiImages;

	    // ...

	    @FXML
	    private void initialize() {
	        // Inizializza le liste di immagini per antipasti e primi
	        antipastiImages = Arrays.asList(baozi, edamame, gyoza, tartare, tempura, shrimp);
	        primiImages = Arrays.asList(frice, maki, nigiri, noodles, uramaki, temaki, teriyaki);
	        
	        changeImages(antipastiImages);
	    }

	    @FXML
	    private void onAntipastiButtonClick() {

	            changeImages(antipastiImages);
	        
	    }

	    @FXML
	    private void onPrimiButtonClick() {

	            changeImages(primiImages);
	        
	    }
	    
	    private void changeImages(List<ImageView> newImages) {
	        // Nasconde le immagini attuali solo se non sono null
	        antipastiImages.stream().filter(imageView -> imageView != null).forEach(imageView -> imageView.setVisible(false));
	        primiImages.stream().filter(imageView -> imageView != null).forEach(imageView -> imageView.setVisible(false));

	        // Mostra le nuove immagini solo se non sono null
	        newImages.stream().filter(imageView -> imageView != null).forEach(imageView -> imageView.setVisible(true));
	    }

	    private void fadeTransition() {
	        // Crea una transizione di fading
	        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), flowPane);

	        // Imposta l'opacità a 0 durante la transizione
	        fadeTransition.setFromValue(1.0);
	        fadeTransition.setToValue(0.0);

	        // Aggiungi un listener per aggiornare il menu alla fine della transizione
	        fadeTransition.setOnFinished(event -> {
	            fadeTransition.setFromValue(0.0);
	            fadeTransition.setToValue(1.0);
	            fadeTransition.play();
	        });

	        // Avvia la transizione di fading
	        fadeTransition.play();
	    }
	
	    @FXML
	    private void onMenuButtonClick() {
	        if (!isMenuVisible) {
	            // Transizioni per il menu
	            TranslateTransition menuTranslateTransition = new TranslateTransition(Duration.seconds(0.5), menuPane);
	            FadeTransition menuFadeTransition = new FadeTransition(Duration.seconds(0.5), menuPane);

	            menuTranslateTransition.setToX(0);
	            menuFadeTransition.setToValue(1);

	            ParallelTransition menuParallelTransition = new ParallelTransition(menuTranslateTransition, menuFadeTransition);

	            // Transizioni per le immagini
	            List<TranslateTransition> imageTransitions = new ArrayList<>();

	            for (ImageView imageView : antipastiImages) {
	                TranslateTransition imageTranslateTransition = createImageTransition(imageView);
	                imageTranslateTransition.setToX(0);
	                imageTransitions.add(imageTranslateTransition);
	            }

	            for (ImageView imageView : primiImages) {
	                TranslateTransition imageTranslateTransition = createImageTransition(imageView);
	                imageTranslateTransition.setToX(0);
	                imageTransitions.add(imageTranslateTransition);
	            }

	            ParallelTransition imagesParallelTransition = new ParallelTransition(imageTransitions.toArray(new Animation[0]));

	            // Sincronizza le transizioni del menu e delle immagini
	            ParallelTransition totalTransition = new ParallelTransition(menuParallelTransition, imagesParallelTransition);

	            totalTransition.setOnFinished(event -> {
	                // Chiamato quando tutte le transizioni sono complete
	                isMenuVisible = true;
	            });

	            // Avvia la sequenza totale
	            totalTransition.play();
	        } else {
	            // Transizioni per il menu
	            TranslateTransition menuTranslateTransition = new TranslateTransition(Duration.seconds(0.5), menuPane);
	            FadeTransition menuFadeTransition = new FadeTransition(Duration.seconds(0.5), menuPane);

	            menuTranslateTransition.setToX(-menuPane.getWidth());
	            menuFadeTransition.setToValue(0);

	            ParallelTransition menuParallelTransition = new ParallelTransition(menuTranslateTransition, menuFadeTransition);

	            // Transizioni per le immagini
	            List<TranslateTransition> imageTransitions = new ArrayList<>();

	            for (ImageView imageView : antipastiImages) {
	                TranslateTransition imageTranslateTransition = createImageTransition(imageView);
	                imageTranslateTransition.setToX(-menuPane.getWidth() + 100);
	                imageTransitions.add(imageTranslateTransition);
	            }

	            for (ImageView imageView : primiImages) {
	                TranslateTransition imageTranslateTransition = createImageTransition(imageView);
	                imageTranslateTransition.setToX(-menuPane.getWidth() + 100);
	                imageTransitions.add(imageTranslateTransition);
	            }

	            ParallelTransition imagesParallelTransition = new ParallelTransition(imageTransitions.toArray(new Animation[0]));

	            // Sincronizza le transizioni del menu e delle immagini
	            ParallelTransition totalTransition = new ParallelTransition(menuParallelTransition, imagesParallelTransition);

	            totalTransition.setOnFinished(event -> {
	                // Chiamato quando tutte le transizioni sono complete
	                isMenuVisible = false;
	            });

	            // Avvia la sequenza totale
	            totalTransition.play();
	        }
	    }
	    
	    private TranslateTransition createImageTransition(ImageView imageView) {
	        TranslateTransition imageTranslateTransition = new TranslateTransition(Duration.seconds(0.5), imageView);

	        if (isMenuVisible) {
	            imageTranslateTransition.setToX(-menuPane.getWidth() + 100);
	        } else {
	            imageTranslateTransition.setToX(0);
	        }

	        return imageTranslateTransition;
	    }

	    
	 
	public void switchScenaSecondaria(ActionEvent event) throws IOException{
		 root = FXMLLoader.load(getClass().getResource("prova1.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.setResizable(false);
		 stage.show();
	}
	
public void switchScenaPrincipale(ActionEvent event) throws IOException{
		 root = FXMLLoader.load(getClass().getResource("prova.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.setResizable(false);
		 stage.show();
}
}