/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komposer.harmony;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.kuro.komposer.common.Accord;
import com.kuro.komposer.common.Mode;
import static com.kuro.komposer.common.Utils.randomInt;

/**
 *
 * @author kuro
 */
public class Gene {
    
    private List<Integer> allels;
    private int melodyPitch;
    
    public static int ALTERNATIVE_AMOUNT = Accord.TRIAD.length + Accord.SEPT.length;
    public static int INVERSION_AMOUNT = 3;
    public static int POSITION_AMOUNT = 4;
    
    public static int[] s = {ALTERNATIVE_AMOUNT, INVERSION_AMOUNT, POSITION_AMOUNT};
    
    public Gene(int melodyPitch) {
        this.melodyPitch = melodyPitch;
        allels = Arrays.asList(
            randomInt(ALTERNATIVE_AMOUNT),
            randomInt(INVERSION_AMOUNT), 
            randomInt(POSITION_AMOUNT) 
        );
        
    }
    
    public Gene(Gene g) {
        this.melodyPitch = g.getMelodyPitch();
        allels = new ArrayList(g.getAllels());
    }
    
    public void setAllel(int i, int val) {
        allels.set(i, val);
    }
    
    public List<Integer> getAllels() {
        return allels;
    }
    
    public int getMelodyPitch() {
        return melodyPitch;
    }
    
    public Integer getAllel(int i) {
        return allels.get(i);
    }
    
    public Accord getAccord(Mode mode) {
        return Accord.getAccord(mode, melodyPitch, allels.get(0), allels.get(1), allels.get(2));
    }
    
}
