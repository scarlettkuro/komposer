
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
public class RuleII53_1 extends Rule implements RuleInterface  {
    
    /*
     Трезвучие II53 относится к субдоминантовой группе аккордов и используется 
     только в натуральном мажоре
    */
    public int check(AccordInterface prev, AccordInterface next) throws
        WrongAccordException,
        SlightMistakeException,
        PlainMistakeException,
        BigMistakeException
    {
        
        if (prev.checkName("II53") && !(mode.getMode().equals(Mode.naturalMajor))) {
            throw new BigMistakeException();
        }
        
        return Rule.OK;
    }
}
