/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komposer.harmony.function.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.kuro.komposer.common.Accord;
import com.kuro.komposer.common.Mode;
import com.kuro.komposer.common.AccordInterface;

/**
 *
 * @author kuro
 */
public class Rule {
    
    public static final int structMistake = 16;
    public static final int bigMistake = 4;
    public static final int plainMistake = 2;
    public static final int slightMistake = 1;
    public static final int OK = 0;
    
    public static Map<Integer,Boolean> getDoubledDegrees(List<Integer> pitches, Mode mode) {
        Map<Integer,Boolean> doubledDegrees = new HashMap<>();
        for(Integer pitch: pitches) {
            int degree = mode.getDegree(pitch);
            doubledDegrees.put(degree, doubledDegrees.containsKey(degree));
        }
        
        return doubledDegrees;
    }
    
    public static int getCommonVoices(AccordInterface prev, AccordInterface next) {
        List<Integer> common = new ArrayList<>(prev.getPitches());
        common.retainAll(next.getPitches());
        
        return common.size();
    }
    
    protected Mode mode;
    
    public void setMode(Mode mode) {
        this.mode = mode;
    }
    
}
