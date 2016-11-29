package komposer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static komposer.Utils.randomInt;
import static komposer.Utils.randomBoolean;

/**
 *
 * @author kuro
 */
public class Melodizer {
    Mode mode;
    
    
    public Melodizer(Mode mode) {
        this.mode = mode;
    }
    
    public List<Playble> makePeriod(int introSize, int procSize, int o) {
        return makeMelody(makePeriodDegrees(introSize, procSize), o);
        
    }
    
    public List<Integer> makePeriodDegrees(int introSize, int procSize) {
        List<Integer> intro1 = makeDegrees(introSize);
        List<Integer> intro2 = randomBoolean() ? new ArrayList<>(intro1) : makeDegrees(introSize);
        List<Integer> cadenz1 = buildCadenz(true);
        List<Integer> cadenz2 = buildCadenz(false);
        List<Integer> proc1 = makeDegrees(procSize - cadenz1.size());
        List<Integer> proc2 = makeDegrees(procSize - cadenz2.size());
        
        
        List<Integer> period = new ArrayList<>();
        period.addAll(intro1);
        period.addAll(proc1);
        period.addAll(cadenz1);
        period.addAll(intro2);
        period.addAll(proc2);
        period.addAll(cadenz2);
        
        return period;
        
    }
    
    public List<Playble> makeMelody(List<Integer> degrees, int o) {
        List<Playble> melody = new ArrayList<>();
        for(int i = 0; i < degrees.size() ; i++) {
            int degree = degrees.get(i);
            int ppitch = 0;
            int oo = 0;
            /*if (i > 0 ) {
                int minoo = -1; 
                int minp = Math.abs(mode.getPitch(degree, o + oo) - ppitch);
                for (oo = 0; oo <= 1; oo++ ) {
                    int ddd =  Math.abs(mode.getPitch(degree, o + oo) - ppitch);
                    if ( ddd < minp) {
                        minoo=oo;
                        minp = ddd;
                    }
                }
                
            }
            */
            int pitch = mode.getPitch(degree, o + oo);
            ppitch = pitch;
            melody.add(new Playble(pitch));
        }
        
        return melody;
    }
    
    public List<Integer> makeDegrees(int n) {
        List<Integer> melody = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            melody.add(randomInt(Mode.degreeNames.length));
        }
        
        return melody;
        
    }
    
    
    
    public List<Integer> buildCadenz(boolean center) {
        Set<Integer> D = center ? 
                D7() : 
                (randomBoolean() ? D7() : D53());
        
        
        List<Set<Integer>> cadenz = new ArrayList<>();
        
        if (randomBoolean()) {
            cadenz.add(T());
            cadenz.add( randomBoolean() ? D : S()); //TD or TS
            if (randomBoolean()) {
                Collections.swap(cadenz, 0, 1); //DT or ST
            }
        } else {
            //SDT
            cadenz.add(S());
            cadenz.add(D);
            cadenz.add(T());
        }
        
        List<Integer> degrees = new ArrayList<>();
        for (Set<Integer> c : cadenz) {
            degrees.add((new ArrayList<Integer>(c)).get(randomInt(c.size())));
        }
        
        return degrees;
    }
    
    
    
    //--------------------------------------------------------------------------
    
    public Set<Integer> T() {
        Set<Integer> T = new HashSet<>();
        for(int i = 0; i < Accord.TRIAD.length; i++) {
            T.addAll(AccordBuilder.generateDegrees(Accord.TRIAD, 0, i));
        }
        return T;
    }
    
    public Set<Integer> D53() {
        Set<Integer> D53 = new HashSet<>();
        for(int i = 0; i < Accord.TRIAD.length; i++) {
            D53.addAll(AccordBuilder.generateDegrees(Accord.TRIAD, 4, i));
        }
        return D53;
    }
    
    public Set<Integer> D7() {
        Set<Integer> D7 = new HashSet<>();
        for(int i = 0; i < Accord.SEPT.length; i++) {
            D7.addAll(AccordBuilder.generateDegrees(Accord.SEPT, 4, i));
        }
        return D7;
    }
    
    public Set<Integer> S() {
        Set<Integer> S = new HashSet<>();
        for(int i = 0; i < Accord.TRIAD.length; i++) {
            S.addAll(AccordBuilder.generateDegrees(Accord.TRIAD, 3, i));
        }
        return S;
    }
}
