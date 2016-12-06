
package komposer.harmony.function.rules;

import com.kuro.komposer.common.Accord;
import com.kuro.komposer.common.Mode;
import com.kuro.komposer.common.AccordInterface;

/**
 *
 * @author kuro
 */
public class RuleVoice8 extends Rule   implements RuleInterface {
    
    
    //Необходимо избегать прямого (в одном направлении) движения всех голосов
    public int check(AccordInterface prev, AccordInterface next) {
        
        boolean diff = prev.getPitches().get(0) > next.getPitches().get(0);
        boolean same = true;
        
        for(int i=1; i<3; i++) {
            same = same && (diff == prev.getPitches().get(i) > next.getPitches().get(i));
        }
        
        if (same) {
            return Rule.bigMistake;
        }
        
        return Rule.OK;
    }
}
