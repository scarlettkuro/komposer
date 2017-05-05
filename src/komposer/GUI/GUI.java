/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komposer.GUI;

import java.io.File;
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
import javax.sound.midi.InvalidMidiDataException;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.pattern.Token;

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
        
        primaryStage.setTitle("Komposer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InvalidMidiDataException {
       /* Pattern pattern = MidiFileManager.loadPatternFromMidi(new File("C:\\Users\\kuro\\Downloads\\grieg_album_format0.mid"));
        for( Token t : pattern.getTokens()) {
            if (t.getType().equals(Token.TokenType.NOTE)) {
                System.out.println("Token : " + t.);
            }
        }
        System.exit(0);*/
        launch(args);
    }
    
}
