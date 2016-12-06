
package komposer.harmony.function.rules;

import java.util.ArrayList;
import com.kuro.komposer.common.Accord;
import com.kuro.komposer.common.Mode;
import java.util.List;
import com.kuro.komposer.common.AccordInterface;

/**
 *
 * @author kuro
 */
public class RuleVoice7 extends Rule  implements RuleInterface {
    
    
    /*
     Скачка в альте лучше избежать.
    */
    public int check(AccordInterface prev, AccordInterface next) {
        if (Math.abs(prev.getAlt() - next.getAlt()) > 3 ) {
            return Rule.slightMistake;
        }
        
        return Rule.OK;
    }
}
