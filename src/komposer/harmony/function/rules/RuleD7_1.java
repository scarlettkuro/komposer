
package komposer.harmony.function.rules;

import komposer.Accord;
import komposer.Mode;
import komposer.AccordInterface;
import komposer.WrongAccordException;
import komposer.harmony.function.rules.mistakes.*;

/**
 *
 * @author kuro
 */
public class RuleD7_1 extends Rule implements RuleInterface {
    
    /*
     Основной тон при разрешении обращений D7 в аккорды тоники остается на месте как устойчивый и общий. 
    */
    public int check(AccordInterface prev, AccordInterface next) throws
        WrongAccordException,
        SlightMistakeException,
        PlainMistakeException,
        BigMistakeException
    {
        
        if (prev.checkName("D7") && next.checkFunc("T") 
                && (prev.getPitches().get(3) != next.getPitches().get(3)) ) {
            throw new PlainMistakeException();
        }
        
        return Rule.OK;
    }
}
