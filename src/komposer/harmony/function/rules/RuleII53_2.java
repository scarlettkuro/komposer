
package komposer.harmony.function.rules;

import java.util.ArrayList;
import komposer.Accord;
import komposer.Mode;
import java.util.List;
import komposer.AccordInterface;

/**
 *
 * @author kuro
 */
public class RuleII53_2 extends Rule implements RuleInterface {
    
    /*
     II53 с D53 и K64 соединяется мелодически, с противоположным мелодическим 
     движением в крайних голосах. 
    */
    public int check(AccordInterface prev, AccordInterface next) {
        
        List<Integer> common = new ArrayList<>(prev.getPitches());
        common.retainAll(next.getPitches());
        
        if ( 
            prev.checkName("II53") 
            && (next.checkName("D53") || next.checkName("K64"))
        ) { 
            int dMelody = prev.getMelody() - next.getMelody();
            int dBase = prev.getBass() - next.getBass();
 
                if (dMelody * dBase >= 0)  {
                    return Rule.slightMistake;
                }
        }
        return Rule.OK;
        
        
    }
}
