package komposer.external;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.sound.midi.InvalidMidiDataException;
import org.jfugue.midi.MidiFileManager;

import org.jfugue.pattern.Pattern;

/**
 *
 * @author kuro
 */
public class ExtMain {
    public static void main(String[] args) throws Exception {
        Pattern examplePatternLoaded;
        
        try {
            examplePatternLoaded = MidiFileManager.loadPatternFromMidi(new File("NKM.mid"));
            System.out.println(examplePatternLoaded );
            ConsonanceReader test = new ConsonanceReader();
            test.setPattern(examplePatternLoaded);
            for(int j =0; j<100; j++) {
                List<Integer> a = test.nextConsonance();
                for (int i : a) {
                    System.out.println(i);
                }
                System.out.println("--");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        
    }
}
