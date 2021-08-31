package edu.westga.cs.babble;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for the Babble project
 * 
 * @author lewisb
 * @version cs6241
 */
public class Babble extends Application {

	private static final String GUI_RESOURCE = "edu/westga/cs/babble/views/BabbleGui.fxml";
    @Override
    public void start(Stage primaryStage) throws Exception {
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        URL resource = classLoader.getResource(Babble.GUI_RESOURCE);
//        FXMLLoader loader = new FXMLLoader(resource);
//        Parent root = (Parent) loader.load();
//        Scene scene = new Scene(root);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Babble");
//        primaryStage.show();
    }

    /**
     * Start point for the appliction.
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        launch(args);
    }

}
