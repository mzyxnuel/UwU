package uwu.view;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import uwu.control.client.ClientController;
import uwu.model.Method;
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
import uwu.model.Order;
import uwu.model.Product;
import uwu.model.Request;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class App extends Application {
    private static ClientController client;
    private static String clientID;
    private static final int ITEMS_PER_ROW = 3;
    private boolean isCartVisible = false;
    private List<ImageView> cartImages = new ArrayList<>();
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
        } catch (Exception e) {
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
        changeImages(antipastiImages,false);

    }

    @FXML
    private void immagineCliccata(MouseEvent event) {
        int index = 0;
        ImageView image = (ImageView) event.getSource();
        String imageUrl = image.getImage().getUrl();
        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf("."));
        Order order = new Order();
        order.setName(fileNameWithoutExtension);

        if (cart.isEmpty()) {
            client.sendRequest(new Request(Method.ADD, clientID, order));
            addToCart(image);
        } else {
            client.sendRequest(new Request(Method.UPDATE, clientID, order));
        }
    }

    @FXML
    private void onAntipastiButtonClick() {
        changeImages(antipastiImages,false);
        isCartVisible = false;
    }

    @FXML
    private void onPrimiButtonClick() {
        changeImages(primiImages,false);
        isCartVisible = false;
    }

    @FXML
    private void onBevandeButtonClick() {
        changeImages(bevande,false);
        isCartVisible = true;
    }

    

    @FXML
    private void onCartButtonClick() {
        changeImages(cart,true);
        isCartVisible = true;
    }



    @FXML
private void addToCart(ImageView imageView) {
    if (imageView != null && !cart.contains(imageView)) {
        cart.add(imageView);
        // Aggiungi questa immagine alla lista specifica per il carrello solo se è diversa da null
        cartImages.add(imageView);
    }
}

    
    private void changeImages(List<ImageView> images, boolean isCart) {
        if (isCart) {
            int rowIndex = 0;
            int colIndex = 0;
            for (ImageView imageView : images) {
                imageView.setVisible(true);
    
                imageView.setLayoutX(colIndex * 100);
                imageView.setLayoutY(rowIndex * 100);
    
                colIndex++;
                if (colIndex >= ITEMS_PER_ROW) {
                    colIndex = 0;
                    rowIndex++;
                }
            }
        } else {
            antipastiImages.forEach(imageView -> imageView.setVisible(false));
            primiImages.forEach(imageView -> imageView.setVisible(false));
            bevande.forEach(imageView -> imageView.setVisible(false));
            cart.forEach(imageView -> imageView.setVisible(false));
    
            images.forEach(imageView -> imageView.setVisible(true));
        }
    }

    @FXML
    private void onMenuButtonClick() {
        if (!isMenuVisible) {

            TranslateTransition menuTranslateTransition = new TranslateTransition(Duration.seconds(0.5), menuPane);
            FadeTransition menuFadeTransition = new FadeTransition(Duration.seconds(0.5), menuPane);

            menuTranslateTransition.setToX(0);
            menuFadeTransition.setToValue(1);

            ParallelTransition menuParallelTransition = new ParallelTransition(menuTranslateTransition,
                    menuFadeTransition);

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

            ParallelTransition imagesParallelTransition = new ParallelTransition(
                    imageTransitions.toArray(new Animation[0]));

            ParallelTransition totalTransition = new ParallelTransition(menuParallelTransition,
                    imagesParallelTransition);

            totalTransition.setOnFinished(event -> {

                isMenuVisible = true;
            });

            totalTransition.play();
        } else {

            TranslateTransition menuTranslateTransition = new TranslateTransition(Duration.seconds(0.5), menuPane);
            FadeTransition menuFadeTransition = new FadeTransition(Duration.seconds(0.5), menuPane);

            menuTranslateTransition.setToX(-menuPane.getWidth());
            menuFadeTransition.setToValue(0);

            ParallelTransition menuParallelTransition = new ParallelTransition(menuTranslateTransition,
                    menuFadeTransition);

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

            ParallelTransition imagesParallelTransition = new ParallelTransition(
                    imageTransitions.toArray(new Animation[0]));

            ParallelTransition totalTransition = new ParallelTransition(menuParallelTransition,
                    imagesParallelTransition);

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

    public void switchSecondaScena(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("order.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchScenaPrincipale(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
