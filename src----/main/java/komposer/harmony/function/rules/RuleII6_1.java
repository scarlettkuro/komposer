
package komposer.harmony.function.rules;

import java.util.HashMap;
import java.util.Iterator;
import com.kuro.komposer.common.Accord;
import com.kuro.komposer.common.Mode;
import java.util.Map;
import com.kuro.komposer.common.AccordInterface;

/**
 *
 * @author kuro
 */
public class RuleII6_1 extends Rule implements RuleInterface {
    
    /*
     При соединении II6 с II65 лучше удвоить приму.
    */
    public int check(AccordInterface prev, AccordInterface next) {
        Map<Integer,Boolean> doubledDegrees = Rule.getDoubledDegrees(prev.getPitches(), mode);
        if (prev.checkName("II6") && next.checkName("II65") && !doubledDegrees.get(prev.getPrima())) {
            return Rule.slightMistake;
        }
        
        return Rule.OK;
    }
}
