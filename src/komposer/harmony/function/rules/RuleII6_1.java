
package komposer.harmony.function.rules;

import java.util.HashMap;
import java.util.Iterator;
import komposer.Accord;
import komposer.Mode;
import java.util.Map;
import komposer.AccordInterface;
import komposer.WrongAccordException;
import komposer.harmony.function.rules.mistakes.*;

/**
 *
 * @author kuro
 */
public class RuleII6_1 extends Rule implements RuleInterface {
    
    /*
     При соединении II6 с II65 лучше удвоить приму.
    */
    public int check(AccordInterface prev, AccordInterface next) throws
        WrongAccordException,
        SlightMistakeException,
        PlainMistakeException,
        BigMistakeException
    {
        Map<Integer,Boolean> doubledDegrees = Rule.getDoubledDegrees(prev.getPitches(), mode);
        if (prev.checkName("II6") && next.checkName("II65") && !doubledDegrees.get(prev.getPrima())) {
            throw new SlightMistakeException();
        }
        
        return Rule.OK;
    }
}
