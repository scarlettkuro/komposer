
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
