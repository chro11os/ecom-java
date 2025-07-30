package com.gabriel.ecomms;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class ManageEcommerceJFXApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        System.out.println("SplashApp:start ");
        FXMLLoader fxmlLoader = new FXMLLoader(ManageEcommerceJFXApp.class.getResource("splash-view.fxml"));
        Parent splashRoot = fxmlLoader.load();

        SplashViewController splashViewController = fxmlLoader.getController();
        splashViewController.setStage(primaryStage); // Pass the primary stage to the splash controller

        Scene splashScene = new Scene(splashRoot, 420, 420);
        splashScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        splashViewController.setSplashScene(splashScene);

        primaryStage.setTitle("Korean Merch Shop");
        primaryStage.setScene(splashScene);
        primaryStage.show();

        // Set up fade-in transition for the splash screen
        splashRoot.setOpacity(0);
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), splashRoot);
        fadeIn.setToValue(1);
        fadeIn.play();
    }
}