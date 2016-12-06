/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komposer.GUI;

import java.util.Arrays;
import java.util.Collection;
import komposer.Mode;

/**
 *
 * @author kuro
 */
public class GUIService {
    public static Collection<String> intruments() {
        return org.jfugue.midi.MidiDictionary.INSTRUMENT_BYTE_TO_STRING.values();
    }
    
    public static Collection<String> pitches() {
        return Arrays.asList(Mode.pitchNames);
    }
    
    public static Collection<String> octaves() {
        return Arrays.asList("Мала","1","2","3","4");
    }
    
    public static Collection<String> modes() {
        return Arrays.asList("Мажор","Мінор");
    }
    
    public static Collection<String> sizes() {
        return Arrays.asList("2/4","3/4", "4/4");
    }
    public static Collection<String> period_sizes() {
        return Arrays.asList("1","2","3","4","5","6","7","8");
    }
}
