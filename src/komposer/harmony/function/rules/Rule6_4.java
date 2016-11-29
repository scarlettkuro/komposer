
package komposer.harmony.function.rules;

import java.util.HashMap;
import java.util.Iterator;
import komposer.Accord;
import komposer.Mode;
import java.util.Map;
import komposer.AccordInterface;

/**
 *
 * @author kuro
 */
public class Rule6_4 extends Rule implements RuleInterface {
    
    /*
     При перемещении секстаккорда обычно движется лишь один голос
    */
    public int check(AccordInterface prev, AccordInterface next) {
        if (prev.checkInversion("6") && next.checkInversion("6")) {
            if (Rule.getCommonVoices(prev, next) < 3) {
                return Rule.slightMistake;
            }
        }
        
        return Rule.OK;
    }
}
