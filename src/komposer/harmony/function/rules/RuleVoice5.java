
package komposer.harmony.function.rules;

import java.util.ArrayList;
import komposer.Accord;
import komposer.Mode;
import java.util.List;
import komposer.AccordInterface;
import komposer.PauseException;
import komposer.WrongAccordException;
import komposer.harmony.function.rules.mistakes.*;

/**
 *
 * @author kuro
 */
public class RuleVoice5 extends Rule  implements RuleInterface {
    
    /*
     При скачке в мелодии необходимо одновременное противоположное движение в басу.
    */
    public int check(AccordInterface prev, AccordInterface next) throws
        WrongAccordException,
        PauseException,
        SlightMistakeException,
        PlainMistakeException,
        BigMistakeException
    {
        
        int dMelody = prev.getMelody() - next.getMelody();
        int dBase = prev.getBass() - next.getBass();
        
        if (Math.abs(dMelody) > 3 ) { 
            if (dMelody * dBase < 0)  {
                return Rule.OK;
            }
            
            throw new BigMistakeException();
        }
        
        return Rule.OK;
    }
}
