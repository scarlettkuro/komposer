/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komposer;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.net.URL;

/**
 *
 * @author kuro
 */
public class GUI extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        //StackPane page = (StackPane) FXMLLoader.load(GUI.class.getResource("simple.fxml"));
        
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("resources/GUI.fxml"));
        
    //MyController myController = loader.getController();
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
