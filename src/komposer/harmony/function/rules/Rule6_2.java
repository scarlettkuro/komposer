
package komposer.harmony.function.rules;

import java.util.HashMap;
import java.util.Iterator;
import komposer.Accord;
import komposer.Mode;
import java.util.Map;
import komposer.AccordInterface;
import komposer.PauseException;
import komposer.WrongAccordException;
import komposer.harmony.function.rules.mistakes.*;

/**
 *
 * @author kuro
 */
public class Rule6_2 extends Rule implements RuleInterface {
    
    /*
     Лучше избегать употребления подряд 2-х секстаккордов разных функций.
    */
    public int check(AccordInterface prev, AccordInterface next) throws
        WrongAccordException,
        PauseException,
        SlightMistakeException,
        PlainMistakeException,
        BigMistakeException
    {
        if (prev.checkInversion("6") && next.checkInversion("6") && 
                next.getPrima() == prev.getPrima()) {
            throw new SlightMistakeException();
        }
        
        return Rule.OK;
    }
}
