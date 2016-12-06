/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komposer;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;

/**
 *
 * @author kuro
 */
public class GUIController implements Initializable {
    @FXML private ComboBox instr1;
    @FXML private ComboBox instr2;
    @FXML private ComboBox instr3;
    @FXML private ComboBox instr4;
    
    @FXML private CheckBox gen_tone_auto;
    @FXML private CheckBox gen_size_auto;
    @FXML private CheckBox gen_periodsize_auto;
    @FXML private HBox gen_tone;
    @FXML private HBox gen_size;
    @FXML private HBox gen_periodsize;
    
    
    @FXML private ComboBox hand_pitch;
    @FXML private ComboBox hand_mode;
    @FXML private ComboBox hand_octave;
    @FXML private ComboBox hand_size;
    @FXML private Spinner hand_period_intro;
    @FXML private Spinner hand_period_outro;
    
    @FXML private ListView hand_periods;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<ComboBox> instrs = Arrays.asList(instr1, instr2, instr3, instr4);
        for (ComboBox instr : instrs) {
            instr.getItems().addAll(GUIService.intruments());
            instr.setValue(instr.getItems().get(0));
        }
        
        hand_pitch.getItems().addAll(GUIService.pitches());
        hand_pitch.setValue(hand_pitch.getItems().get(0));
        hand_mode.getItems().addAll(GUIService.modes());
        hand_mode.setValue(hand_mode.getItems().get(0));
        hand_octave.getItems().addAll(GUIService.octaves());
        hand_octave.setValue(hand_octave.getItems().get(0));
        hand_size.getItems().addAll(GUIService.sizes());
        hand_size.setValue(hand_size.getItems().get(0));
        hand_period_intro.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 8));
        hand_period_outro.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 8));
        
    }
    
    @FXML 
    public void gen_tone_auto_action (ActionEvent event) {
        gen_tone.setDisable(gen_tone_auto.isSelected());
    }
    
    @FXML 
    public void gen_size_auto_action (ActionEvent event) {
        gen_size.setDisable(gen_size_auto.isSelected());
    }
    
    @FXML 
    public void gen_periodsize_auto_action (ActionEvent event) {
        gen_periodsize.setDisable(gen_periodsize_auto.isSelected());
    }
    
    @FXML 
    public void add_period_action (ActionEvent event) {
        hand_periods.getItems().add( "" + 
            hand_pitch.getValue() + hand_mode.getValue() + hand_octave.getValue() 
                + " " + hand_size.getValue() + " "  
                + "("  + hand_period_intro.getValue() + "," + hand_period_outro.getValue() + ")"
        );
    }
}
