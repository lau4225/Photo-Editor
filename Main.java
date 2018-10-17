import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.geometry.Pos.CENTER;

public class Main extends Application {

    public static void main(String[] args) { launch(); }

    public void start(Stage primaryStage) {

        primaryStage.setTitle("Laboratoire #6");
        primaryStage.setHeight(700);
        primaryStage.setWidth(800);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(true);

        //MENUS

        Menu fichier = new Menu("Fichier");
        Menu action = new Menu("Action");
        Menu chargerImage = new Menu("Charger une image");

        MenuItem reinitialiser = new MenuItem("Réinitialiser");
        MenuItem image1 = new MenuItem("Image 1");
        MenuItem image2 = new MenuItem("Image 2");
        MenuItem image3 = new MenuItem("Image 3");

        fichier.getItems().addAll(chargerImage);
        chargerImage.getItems().addAll(image1, image2, image3);

        action.getItems().addAll(reinitialiser);

        MenuBar menuBar = new MenuBar(fichier, action);

        Menu action2 = new Menu("Action");
        Menu chargerImage2 = new Menu("Charger une image");

        MenuItem reinitialiser2 = new MenuItem("Réinitialiser");
        MenuItem image12 = new MenuItem("Image 1");
        MenuItem image22 = new MenuItem("Image 2");
        MenuItem image32 = new MenuItem("Image 3");

        chargerImage2.getItems().addAll(image12, image22, image32);

        action2.getItems().addAll(reinitialiser2);


        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(action2, chargerImage2);


        //SLIDERS
        Tooltip lumiTT = new Tooltip("Rend l'image plus claire ou plus sombre");
        Tooltip contTT = new Tooltip("Diminue ou augmente la différence entre les couleurs");
        Tooltip teinTT = new Tooltip("Change la teinte (couleur) de l'image");
        Tooltip satuTT = new Tooltip("Diminue ou augmente l'intensité des couleurs");


        Label lumi = new Label("Luminosité");
        Slider luminosite = new Slider(-1, 1, 0);
        luminosite.setTooltip(lumiTT);
        lumi.setTooltip(lumiTT);

        Label cont = new Label("Contraste");
        Slider contraste = new Slider(-1, 1, 0);
        contraste.setTooltip(contTT);
        cont.setTooltip(contTT);

        Label tein = new Label("Teinte");
        Slider teinte = new Slider(-1, 1, 0);
        teinte.setTooltip(teinTT);
        tein.setTooltip(teinTT);

        Label satu = new Label("Saturation");
        Slider saturation = new Slider(-1, 1, 0);
        saturation.setTooltip(satuTT);
        satu.setTooltip(satuTT);


        //IMAGE
        Image picA = new Image("paysage.jpg");
        Image picB = new Image("volcano.jpg");
        Image picC = new Image("bird.jpeg");

        ImageView imageView = new ImageView(new Image("chien.jpg"));


        VBox sliders = new VBox(lumi, luminosite, cont, contraste, tein, teinte, satu, saturation);
        sliders.setAlignment(CENTER);
        sliders.setSpacing(10);
        sliders.setPadding(new Insets(0, 0, 0, 10));

        HBox all = new HBox(imageView, sliders);
        all.setAlignment(CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(all);
        borderPane.setOnContextMenuRequested((ContextMenuEvent event) -> {
            contextMenu.show(borderPane, event.getScreenX(), event.getScreenY());
        });


        //ACTIONS

        ColorAdjust imageColors = new ColorAdjust();

        luminosite.valueProperty().addListener((observable, oldValue, newValue) -> {
            imageColors.setBrightness((Double) newValue);
        });

        contraste.valueProperty().addListener((observable, oldValue, newValue) -> {
            imageColors.setContrast((Double) newValue); });

        teinte.valueProperty().addListener((observable, oldValue, newValue) -> {
            imageColors.setHue((Double) newValue); });

        saturation.valueProperty().addListener((observable, oldValue, newValue) -> {
            imageColors.setSaturation((Double) newValue); });



        reinitialiser.setOnAction(event -> {
            luminosite.setValue(0);
            contraste.setValue(0);
            teinte.setValue(0);
            saturation.setValue(0);

            imageColors.setBrightness(0);
            imageColors.setContrast(0);
            imageColors.setHue(0);
            imageColors.setSaturation(0);

    });

        reinitialiser2.setOnAction(event -> {reinitialiser.fire();});

        image1.setOnAction(event -> { reinitialiser.fire();imageView.setImage(picA);});
        image2.setOnAction(event -> { reinitialiser.fire();imageView.setImage(picB);});
        image3.setOnAction(event -> { reinitialiser.fire();imageView.setImage(picC);});

        image12.setOnAction(event -> { reinitialiser.fire();imageView.setImage(picA);});
        image22.setOnAction(event -> { reinitialiser.fire();imageView.setImage(picB);});
        image32.setOnAction(event -> { reinitialiser.fire();imageView.setImage(picC);});



        imageView.setEffect(imageColors);
        primaryStage.setScene(new Scene(borderPane));
        primaryStage.show();
    }
}
