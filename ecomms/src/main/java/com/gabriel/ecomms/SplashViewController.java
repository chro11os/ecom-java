package com.gabriel.ecomms;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashViewController implements Initializable {

    @Setter
    Stage stage;
    @Setter
    Scene splashScene;
    public ImageView imgHug;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Logo pulse animation
        if (imgHug != null) {
            ScaleTransition st = new ScaleTransition(Duration.millis(500), imgHug);
            st.setByX(0.1);
            st.setByY(0.1);
            st.setCycleCount(2);
            st.setAutoReverse(true);

            FadeTransition ft = new FadeTransition(Duration.millis(1000), imgHug);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);

            SequentialTransition seqT = new SequentialTransition(ft, st);
            seqT.setOnFinished(e -> {
                try {
                    loadMainAppScene();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            seqT.play();
        }
    }

    private void loadMainAppScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-app-view.fxml"));
        Parent mainAppRoot = fxmlLoader.load();

        Scene mainAppScene = new Scene(mainAppRoot, 1000, 700); // Set appropriate size for main app
        mainAppScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.setScene(mainAppScene);

        // Fade in the new scene (main app)
        mainAppRoot.setOpacity(0);
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), mainAppRoot);
        fadeIn.setToValue(1);
        fadeIn.play();
    }
}