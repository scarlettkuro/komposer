
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
public class RuleII6_2 extends Rule implements RuleInterface {
    
    /*
     Употреблять после T53, T6, VI53, S53 в мелодическом положении примы.
    */
    public int check(AccordInterface prev, AccordInterface next) throws
        WrongAccordException,
        SlightMistakeException,
        PlainMistakeException,
        BigMistakeException
    {
        if (!next.checkName("II6")) {
            return Rule.OK;
        }
        
        if ((next.getMelodyDegree() != next.getPrima()) && 
            (prev.checkName("T53") || prev.checkName("T6") || prev.checkName("VI53") || prev.checkName("S53")) 
        ) {
            throw new PlainMistakeException();
        }
        
        return Rule.OK;
    }
}
