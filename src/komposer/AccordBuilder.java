
package komposer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author kuro
 */
public class AccordBuilder {
    
    Mode mode;
    int melodyPitch;
    
    public AccordBuilder(Mode mode, int melodyPitch) {
        this.mode = mode;
        this.melodyPitch = melodyPitch;
    }
    
    public static boolean isTriad(int alternative) {
        return alternative < Accord.TRIAD.length;
    }
    
    public static int[] getType(int alternative) {
        return isTriad(alternative) ? Accord.TRIAD : Accord.SEPT;
    }
    
    public static int getAlterntiveOfType(int alternative) {
        return isTriad(alternative) ? alternative : alternative - Accord.TRIAD.length;
    }
    
    public int melodyDegree() {
        return mode.getDegree(melodyPitch);
    }
    
    public List<Integer> generatePitches(List<Integer> degrees, int inversion, int position) {
        if (degrees.size()==4 && degrees.indexOf(melodyDegree()) == inversion) {
            inversion = (inversion + 1) % Accord.SEPT.length;
        }
        List<Integer> voices = generateVoices(degrees, inversion, position);
        
        return generatePitches(voices);
    }
    
    private List<Integer> generatePitches(List<Integer> voices) {
        ArrayList<Integer> pitches = new ArrayList<>(voices.size());
        pitches.add(melodyPitch);
        int lastVoice = melodyPitch;
        int currentVoice;
        for(int i = 1; i < voices.size(); i++) {
            currentVoice = mode.getPitchLowerThen(voices.get(i), lastVoice);
            pitches.add(currentVoice);
            lastVoice = currentVoice;
        }
        
        return pitches;
    }
    
    private List<Integer> generateVoices(List<Integer> degrees, int inversion, int position) {
        int baseDegree = degrees.get(inversion);
        int prima = degrees.get(0);
        int quinta = degrees.get(2);
        boolean isTriad = degrees.size() == 3;
        
        List<Integer> degreesRemain = new ArrayList<>(degrees);
        degreesRemain.remove(degreesRemain.indexOf(melodyDegree()));
        if ( melodyDegree() != baseDegree) {
            degreesRemain.remove(degreesRemain.indexOf(baseDegree));
        }
        
        List<Integer> voices = new ArrayList<>(4);
        voices.add(melodyDegree());
        voices.add(degreesRemain.get(0));
        
        if (isTriad && melodyDegree() != baseDegree) {
            if (degreesRemain.contains(prima)) {
                voices.add(prima);
            } else {
                voices.add(quinta);
            }
        } else {
            voices.add(degreesRemain.get(1));
        }
        
        if (position%2 == 1) {
            Collections.swap(voices, 1, 2);
        }
        
        voices.add(baseDegree);
        
        return voices;
    }
    
    public List<Integer> generateDegrees(int alternative) {
        return generateDegrees(
            getType(alternative), 
            melodyDegree(), 
            getAlterntiveOfType(alternative)
        );
    }
    
    public static List<Integer> generateDegrees(int[] type, int degree, int alternativeOfType) {
        ArrayList<Integer> degrees = new ArrayList<>(type.length);
        for(int i = 0; i < type.length; i++) {
            degrees.add((degree + type[i] + (7 - type[alternativeOfType])) % 7);
        }
        
        return degrees;
    }
    
    public List<Integer> findDegrees(Set degreeSet) {
        
        if (degreeSet.size() < Accord.TRIAD.length || degreeSet.contains(-1)) {
            return null;
        }
        
        for(int i = 0; i < (Accord.TRIAD.length + Accord.SEPT.length); i++ ) {
            List<Integer> degrees = generateDegrees(i);
            if (degreeSet.equals(new HashSet<>(degrees))) {
                return degrees;
            }
        }
        
        return null;
    }
}
