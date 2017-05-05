
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
public class RuleVoice8 extends Rule   implements RuleInterface {
    
    
    //Необходимо избегать прямого (в одном направлении) движения всех голосов
    public int check(AccordInterface prev, AccordInterface next) throws
        WrongAccordException,
        SlightMistakeException,
        PlainMistakeException,
        BigMistakeException
    {
        
        boolean diff = prev.getPitches().get(0) > next.getPitches().get(0);
        boolean same = true;
        
        for(int i=1; i<3; i++) {
            same = same && (diff == prev.getPitches().get(i) > next.getPitches().get(i));
        }
        
        if (same) {
            throw new SlightMistakeException();
        }
        
        return Rule.OK;
    }
}
