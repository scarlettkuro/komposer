package komposer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import komposer.AccordBuilder;
import komposer.AccordBuilder;
import komposer.AccordBuilder;
import komposer.AccordInterface;
import komposer.AccordInterface;
import komposer.AccordInterface;
import komposer.Mode;
import komposer.Mode;
import komposer.Mode;

/**
 *
 * @author kuro
 */
public class Accord implements AccordInterface {
    
    private Mode mode;
    List<Integer> degrees;
    List<Integer> pitches;
    private int inversion;
    
    public static final int[] TRIAD = {0, 2, 4};
    public static final int[] SEPT = {0, 2, 4, 6};
    
    public static final String[] FUNC_NAMES = {"T", "II", "III", "S", "D", "VI", "VII"};
    public static final String[] TRIAD_INVERSION_NAMES = {"53", "6", "64"};
    public static final String[] SEPT_INVERSION_NAMES = {"7", "65", "43", "2"};
    
    public static final Accord BAD_ACCORD = new Accord();
    
    public List<Integer> getPitches() {
        return pitches;
    }
    
    private List<Integer> getDegrees() {
        return degrees;
    }
    
    private boolean isTriad() {
        return degrees.size() == 3;
    }
    
    //--------------------------------------------------------------------------
    
    private Accord () {
        //for bad accord
    }
    
    private Accord (Mode mode, List<Integer> degrees, List<Integer> pitches) {
        this.mode = mode;
        this.degrees = degrees;
        this.pitches = pitches;
        
        int bs = pitches.get(3);
        int bsDegree = mode.getDegree(bs);
        int inversion = degrees.indexOf(bsDegree);
        this.inversion = inversion;
    }
    
    public static Accord getAccord(Mode mode, int melodyPitch, int alternative, int inversion, int position) {
        AccordBuilder builder = new AccordBuilder(mode, melodyPitch);
        List<Integer> degrees = builder.generateDegrees(alternative);
        List<Integer> pitches = builder.generatePitches(degrees, inversion, position);
        
        return new Accord(mode, degrees, pitches);
    }
    
    public static Accord getAccord(Mode mode, List<Integer> pitches) {
        
        AccordBuilder builder = new AccordBuilder(mode, pitches.get(0));
        
        Set<Integer> actualDegrees = new HashSet();
        for (int pitch : pitches) {
            actualDegrees.add(mode.getDegree(pitch));
        }
        
        List degrees = builder.findDegrees(actualDegrees);
        
        if(degrees != null) {
            return new Accord(mode, degrees, pitches);
        }
        
        return Accord.BAD_ACCORD;
    }
    
    
    //-----------------------------------------------
    
    
    public int getPrima() {
        return getDegrees().get(0);
    }
    
    public int getTertia() {
        return getDegrees().get(1);
    }
    
    public int getQuinta() {
        return getDegrees().get(2);
    }
    
    public int getSeptima() {
        return getDegrees().get(3);
    }
    
    public int getMelodyDegree() {
        return mode.getDegree(pitches.get(0));
    }
    
    public int getBaseDegree() {
        return getDegrees().get(inversion);
    }
    
    
    
    private String getInversionName(int inversion) {
        return isTriad() ? 
            Accord.TRIAD_INVERSION_NAMES[inversion] : 
            Accord.SEPT_INVERSION_NAMES[inversion];
    }
    
    
    public String getFunc() {
        return FUNC_NAMES[getPrima()];
    }
    
    public boolean checkFunc(String funcName) {
        return FUNC_NAMES[getPrima()].equals(funcName);
    }
    
    public boolean checkInversion(String invName) {
        return getInversionName(inversion).equals(invName);
    }
    
    
    public String getAccordName() {
        return FUNC_NAMES[getPrima()] + getInversionName(inversion);
    }
    
    public boolean checkName(String name) {
        if (name.equals("K64")) {
            return getAccordName().equals("T64");
        }
        return getAccordName().equals(name);
    }

    @Override
    public int getMelody() {
        return getPitches().get(0);
    }

    @Override
    public int getAlt() {
        return getPitches().get(1);
    }

    @Override
    public int getTenor() {
        return getPitches().get(2);
    }

    @Override
    public int getBass() {
        return getPitches().get(3);
    }
}
