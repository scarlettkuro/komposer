
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
public class RuleVoice7 extends Rule  implements RuleInterface {
    
    
    /*
     Скачка в альте лучше избежать.
    */
    public int check(AccordInterface prev, AccordInterface next) throws
        WrongAccordException,
        PauseException,
        SlightMistakeException,
        PlainMistakeException,
        BigMistakeException
    {
        if (Math.abs(prev.getAlt() - next.getAlt()) > 3 ) {
            throw new SlightMistakeException();
        }
        
        return Rule.OK;
    }
}
