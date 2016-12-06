
package komposer.harmony.function.rules;

import com.kuro.komposer.common.Accord;
import com.kuro.komposer.common.Mode;
import com.kuro.komposer.common.AccordInterface;

/**
 *
 * @author kuro
 */
public class RuleII53_1 extends Rule implements RuleInterface  {
    
    /*
     Трезвучие II53 относится к субдоминантовой группе аккордов и используется 
     только в натуральном мажоре
    */
    public int check(AccordInterface prev, AccordInterface next) {
        
        if (prev.checkName("II53") && !(mode.getMode().equals(Mode.naturalMajor))) {
            return Rule.bigMistake;
        }
        
        return Rule.OK;
    }
}
