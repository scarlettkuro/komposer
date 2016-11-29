
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
public class RuleVoice4 extends Rule  implements RuleInterface {
    
    
    /*
     Желательно сохранять косвенное движение аккордов (чтобы между соседними 
     аккордами были общие звуки).
    */
    public int check(AccordInterface prev, AccordInterface next) {
        
        if (Rule.getCommonVoices(prev, next) > 0) {
            return Rule.OK;
        }
        
        return Rule.slightMistake;
    }
}
