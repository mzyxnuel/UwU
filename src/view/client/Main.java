package view.client;
	
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import control.client.ClientController;
import model.Method;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.css.Styleable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Order;
import model.Product;
import model.Request;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;

//COME MANDARE LE RICHIESTE: client.sendRequest(new Request(Method.ADD, clientID, new Order()));
public class Main extends Application {
	private static ClientController client;
	private static String clientID;
	
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
    
    @FXML
    private ImageView acqua;

    @FXML
    private ImageView coca;
    
    @FXML
    private ImageView sprite;

    @FXML
    private ImageView fanta;
    
    private boolean isMenuVisible = false;

    private List<ImageView> antipastiImages;
    private List<ImageView> primiImages;
    private List<ImageView> bevande;
    private List<ImageView> cart = new ArrayList<>();
    private Map<String, Integer> orderCount = new HashMap<>();
    private boolean carrello = false;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		client = new ClientController();
		clientID = client.getClientID();
		
		launch(args);
	
	}

    @FXML
    private void initialize() {

        antipastiImages = Arrays.asList(baozi, edamame, gyoza, tartare, tempura, shrimp);
        primiImages = Arrays.asList(frice, maki, nigiri, noodles, uramaki, temaki, teriyaki);
        bevande = Arrays.asList(acqua, fanta, coca, sprite);
        cart = new ArrayList<>();
        changeImages(antipastiImages);
    }
    
    @FXML
    private void immagineCliccata(MouseEvent event) {
     ImageView image = (ImageView) event.getSource();
     String imageUrl = image.getImage().getUrl();
     String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
     String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf("."));
     Order order = new Order();
     
	     order.setName(fileNameWithoutExtension);
	     
	     if(carrello == false) {
	     client.sendRequest(new Request(Method.ADD, clientID, order));
	     carrello = true;
	     }
	     	else {
		     if(!cart.contains(image)) {
		    	 client.sendRequest(new Request(Method.UPDATE, clientID, order));
		     }
		    }
     cart.add(image);
    }
    
    @FXML
    private void onAntipastiButtonClick() {

            changeImages(antipastiImages);
        
    }

    @FXML
    private void onPrimiButtonClick() {

            changeImages(primiImages);
        
    }
    
    @FXML
    private void onBevandeButtonClick() {

            changeImages(bevande);
        
    }
    @FXML
    private void onCartButtonClick() {
    	
    	List<ImageView> images = new ArrayList<>();
    	
    	for (ImageView image : images) {
 	       image.setOnMouseClicked(null);
 	   }
        	changeImages(cart);
        	
    
    }

    
    private void changeImages(List<ImageView> newImages) {

        antipastiImages.stream().filter(imageView -> imageView != null).forEach(imageView -> imageView.setVisible(false));
        primiImages.stream().filter(imageView -> imageView != null).forEach(imageView -> imageView.setVisible(false));
        bevande.stream().filter(imageView -> imageView != null).forEach(imageView -> imageView.setVisible(false));
        cart.stream().filter(imageView -> imageView != null).forEach(imageView -> imageView.setVisible(false));
        
        newImages.stream().filter(imageView -> imageView != null).forEach(imageView -> imageView.setVisible(true));
    }


    @FXML
    private void onMenuButtonClick() {
        if (!isMenuVisible) {
         
            TranslateTransition menuTranslateTransition = new TranslateTransition(Duration.seconds(0.5), menuPane);
            FadeTransition menuFadeTransition = new FadeTransition(Duration.seconds(0.5), menuPane);

            menuTranslateTransition.setToX(0);
            menuFadeTransition.setToValue(1);

            ParallelTransition menuParallelTransition = new ParallelTransition(menuTranslateTransition, menuFadeTransition);


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
            for (ImageView imageView : bevande) {
                TranslateTransition imageTranslateTransition = createImageTransition(imageView);
                imageTranslateTransition.setToX(0);
                imageTransitions.add(imageTranslateTransition);
            }
            for (ImageView imageView : cart) {
                TranslateTransition imageTranslateTransition = createImageTransition(imageView);
                imageTranslateTransition.setToX(0);
                imageTransitions.add(imageTranslateTransition);
            }

            ParallelTransition imagesParallelTransition = new ParallelTransition(imageTransitions.toArray(new Animation[0]));


            ParallelTransition totalTransition = new ParallelTransition(menuParallelTransition, imagesParallelTransition);

            totalTransition.setOnFinished(event -> {

                isMenuVisible = true;
            });


            totalTransition.play();
        } else {

            TranslateTransition menuTranslateTransition = new TranslateTransition(Duration.seconds(0.5), menuPane);
            FadeTransition menuFadeTransition = new FadeTransition(Duration.seconds(0.5), menuPane);

            menuTranslateTransition.setToX(-menuPane.getWidth());
            menuFadeTransition.setToValue(0);

            ParallelTransition menuParallelTransition = new ParallelTransition(menuTranslateTransition, menuFadeTransition);

 
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
            
            for (ImageView imageView : bevande) {
                TranslateTransition imageTranslateTransition = createImageTransition(imageView);
                imageTranslateTransition.setToX(-menuPane.getWidth() + 100);
                imageTransitions.add(imageTranslateTransition);
            }
            
            for (ImageView imageView : cart) {
                TranslateTransition imageTranslateTransition = createImageTransition(imageView);
                imageTranslateTransition.setToX(-menuPane.getWidth() + 100);
                imageTransitions.add(imageTranslateTransition);
            }

            ParallelTransition imagesParallelTransition = new ParallelTransition(imageTransitions.toArray(new Animation[0]));


            ParallelTransition totalTransition = new ParallelTransition(menuParallelTransition, imagesParallelTransition);

            totalTransition.setOnFinished(event -> {

                isMenuVisible = false;
            });

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

    
 
public void switchSecondaScena(ActionEvent event) throws IOException{
	 root = FXMLLoader.load(getClass().getResource("order.fxml"));
	 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	 scene = new Scene(root);
	 stage.setScene(scene);
	 stage.setResizable(false);
	 stage.show();
}

public void switchScenaPrincipale(ActionEvent event) throws IOException{
	 root = FXMLLoader.load(getClass().getResource("main.fxml"));
	 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	 scene = new Scene(root);
	 stage.setScene(scene);
	 stage.setResizable(false);
	 stage.show();
}
}
