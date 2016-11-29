
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
public class Rule6_2 extends Rule implements RuleInterface {
    
    /*
     Лучше избегать употребления подряд 2-х секстаккордов разных функций.
    */
    public int check(AccordInterface prev, AccordInterface next) {
        if (prev.checkInversion("6") && next.checkInversion("6") && 
                next.getPrima() == prev.getPrima()) {
            return Rule.slightMistake;
        }
        
        return Rule.OK;
    }
}
