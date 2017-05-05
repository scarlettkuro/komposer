
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
public class RuleVoice3 extends Rule  implements RuleInterface {
    
    
    /*
     Не удваивать без надобности терцию (кроме особых случаев: 
        II6; VI53, 
        T после VII7, II7).
    */
    public int check(AccordInterface prev, AccordInterface next) throws
        WrongAccordException,
        SlightMistakeException,
        PlainMistakeException,
        BigMistakeException
    {
        
        //check prev - if it's II6; VI53, return 0
        if (next.checkName("II6") || next.checkName("VI53")) {
            return Rule.OK;
        }
        //check prev is T and next is VII7, II7, return 0
        if (prev.checkFunc("T") && (next.checkName("VII7") || next.checkName("II7"))) {
            return Rule.OK;
        }
        //else:
        Map<Integer,Boolean> doubledDegrees = Rule.getDoubledDegrees(next.getPitches(), mode);
        if (doubledDegrees.get(next.getTertia())) {
            throw new BigMistakeException();
        }
        
        return Rule.OK;
    }
}
