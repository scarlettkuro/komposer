
package komposer.harmony.function.rules;

import com.kuro.komposer.common.Accord;
import com.kuro.komposer.common.Mode;
import com.kuro.komposer.common.AccordInterface;

/**
 *
 * @author kuro
 */
public class RuleD7_1 extends Rule implements RuleInterface {
    
    /*
     Основной тон при разрешении обращений D7 в аккорды тоники остается на месте как устойчивый и общий. 
    */
    public int check(AccordInterface prev, AccordInterface next) {
        
        if (prev.checkName("D7") && next.checkFunc("T") 
                && (prev.getPitches().get(3) != next.getPitches().get(3)) ) {
            return Rule.plainMistake;
        }
        
        return Rule.OK;
    }
}
