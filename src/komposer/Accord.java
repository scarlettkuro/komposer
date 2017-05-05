package komposer;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import komposer.AccordBuilder;
import komposer.AccordInterface;
import komposer.Mode;

/**
 *
 * @author kuro
 */
public class Accord implements AccordInterface {
    
    private Mode mode;
    List<Integer> pitches;
    
    public static final int pausePitch = -1000;
    
    public static final int[] TRIAD = {0, 2, 4};
    public static final int[] SEPT = {0, 2, 4, 6};
    
    public static final String[] FUNC_NAMES = {"T", "II", "III", "S", "D", "VI", "VII"};
    public static final String[] TRIAD_INVERSION_NAMES = {"53", "6", "64"};
    public static final String[] SEPT_INVERSION_NAMES = {"7", "65", "43", "2"};
    
    
    private List<Integer> getDegrees() throws WrongAccordException {
        List<Integer> actualDegrees = new ArrayList();
        for (int pitch : pitches) {
            Integer degree = mode.getDegree(pitch);
            if (!actualDegrees.contains(degree)) {
                actualDegrees.add(degree);
            }
        }
        
        AccordBuilder builder = new AccordBuilder(mode, getPitches().get(0));
        for(int i = 0; i < (Accord.TRIAD.length + Accord.SEPT.length); i++ ) {
            List<Integer> degrees = builder.generateDegrees(i);
            if (Collections.indexOfSubList(degrees, actualDegrees) >= 0) {
                return degrees;
            }
        }
        
        throw new WrongAccordException();
    }
    
    private boolean isTriad() throws WrongAccordException {
        return getDegrees().size() == 3;
    }
    /*
    private Mode getMode() {
        return mode;
    }
    */
    //--------------------------------------------------------------------------
    
    private Accord (Mode mode, List<Integer> pitches) {
        this.mode = mode;
        this.pitches = pitches;
    }
    
    public static Accord getAccord(Mode mode, int melodyPitch, int alternative, int inversion, int position) {
        AccordBuilder builder = new AccordBuilder(mode, melodyPitch);
        List<Integer> degrees = builder.generateDegrees(alternative);
        List<Integer> pitches = builder.generatePitches(degrees, inversion, position);
        
        return new Accord(mode, pitches);
    }
    
    
    //-----------------------------------------------
    
    @Override
    public List<Integer> getPitches() {
        return pitches;
    }
    
    @Override
    public int getPrima() throws WrongAccordException {
        return getDegrees().get(0);
    }
    
    @Override
    public int getTertia() throws WrongAccordException {
        return getDegrees().get(1);
    }
    
    @Override
    public int getQuinta() throws WrongAccordException {
        return getDegrees().get(2);
    }
    
    @Override
    public int getSeptima() throws WrongAccordException {
        return getDegrees().get(3);
    }
    
    @Override
    public int getMelodyDegree() {
        return mode.getDegree(pitches.get(0));
    }
    
    @Override
    public int getBaseDegree() {
        
        int basePitch = pitches.get(3);
        return mode.getDegree(basePitch);
    }
    
    
    
    private String getInversionName(int inversion) throws WrongAccordException {
        return isTriad() ? 
            Accord.TRIAD_INVERSION_NAMES[inversion] : 
            Accord.SEPT_INVERSION_NAMES[inversion];
    }
    
    
    @Override
    public String getFunc() throws WrongAccordException {
        return FUNC_NAMES[getPrima()];
    }
    
    @Override
    public boolean checkFunc(String funcName) throws WrongAccordException {
        return FUNC_NAMES[getPrima()].equals(funcName);
    }
    
    @Override
    public boolean checkInversion(String invName) throws WrongAccordException {
        return getInversionName(getInversion()).equals(invName);
    }
    
    
    @Override
    public String getAccordName() throws WrongAccordException {
        return FUNC_NAMES[getPrima()] + getInversionName(getInversion());
    }
    
    @Override
    public boolean checkName(String name) throws WrongAccordException {
        if (name.equals("K64")) {
            return getAccordName().equals("T64");
        }
        return getAccordName().equals(name);
    }

    @Override
    public int getMelody() throws PauseException {
        if (getPitches().get(0) == pausePitch) throw new PauseException();
        return getPitches().get(0);
    }

    @Override
    public int getAlt() throws PauseException {
        if (getPitches().get(1) == pausePitch) throw new PauseException();
        return getPitches().get(1);
    }

    @Override
    public int getTenor() throws PauseException {
        if (getPitches().get(2) == pausePitch) throw new PauseException();
        return getPitches().get(2);
    }

    @Override
    public int getBass() throws PauseException {
        if (getPitches().get(3) == pausePitch) throw new PauseException();
        return getPitches().get(3);
    }

    @Override
    public int getInversion() throws WrongAccordException {
        int basePitch = pitches.get(3);
        int baseDegree = mode.getDegree(basePitch);
        return getDegrees().indexOf(baseDegree);
    }
}
