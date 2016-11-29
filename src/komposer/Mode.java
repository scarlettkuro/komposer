package komposer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author kuro
 */
public class Mode {
    public static final Integer[] naturalMajor = {0, 2, 4, 5, 7, 9, 11};
    public static final Integer[] naturalMinor = {0, 2, 3, 5, 7, 8, 10};
    
    public static final String[] pitchNames = {"C", "#C", "D", "#D", "E", "F", "#F", "G", "#G", "A", "#A", "B"};
    public static final String[] degreeNames = {"I", "II", "III", "IV", "V", "VI", "VII"};
    public static final int C3 = 0;
    public static final int OctaveSize = 12; //12 полутонов
    
    
    
    private List<Integer> mode;
    private int tonic;
    
    public Mode(Integer[] mode, int tonic) {
        this.mode = Arrays.asList(mode);
        this.tonic = tonic;
    }
    
    public List<Integer> getMode() {
        return mode;
    }
    
    public int getPitchLowerThen(int degree, int higherPitch) {
        int octave = getOctave(higherPitch);
        int lowerPitch = getPitch(degree, octave);
        
        if (lowerPitch >= higherPitch) {
            return getPitch(degree, octave - 1);
        }
        
        return lowerPitch;
    }
    
    public int getPitch(int degree, int octave) {
        int purePitch = (tonic + mode.get(degree)) % Mode.OctaveSize;
        return purePitch + Mode.OctaveSize*octave;
    }
    
    public static int getOctave(int pitch) {
        return pitch/Mode.OctaveSize;
    }
    
    public int getDegree(int pitch) {
        int proposalDegree = ( pitch - tonic ) % Mode.OctaveSize;
        proposalDegree = (Mode.OctaveSize + proposalDegree) % Mode.OctaveSize;
        return mode.indexOf(proposalDegree);
    }
    
    public static String getPitchName(int pitch) {
        int purePitch = ( Mode.OctaveSize + pitch % Mode.OctaveSize) % Mode.OctaveSize;
        int octave = getOctave(pitch);
        
        return pitchNames[purePitch] + ((octave > 0) ? octave  : "");
    }
    
    public static String getDegreeName(int degree) {
        return degreeNames[degree];
    }
}
