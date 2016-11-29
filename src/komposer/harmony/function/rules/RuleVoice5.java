
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
public class RuleVoice5 extends Rule  implements RuleInterface {
    
    
    /*
     При скачке в мелодии необходимо одновременное противоположное движение в басу.
    */
    public int check(AccordInterface prev, AccordInterface next) {
        
        int dMelody = prev.getMelody() - next.getMelody();
        int dBase = prev.getBass() - next.getBass();
        
        if (Math.abs(dMelody) > 3 ) { 
            if (dMelody * dBase < 0)  {
                return Rule.OK;
            }
            
            return Rule.bigMistake;
        }
        
        return Rule.OK;
    }
}
