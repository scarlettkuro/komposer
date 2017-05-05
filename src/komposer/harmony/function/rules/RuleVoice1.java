
package komposer.harmony.function.rules;

import komposer.Accord;
import komposer.Mode;
import komposer.AccordInterface;
import komposer.PauseException;
import komposer.WrongAccordException;
import komposer.harmony.function.rules.mistakes.*;

/**
 *
 * @author kuro
 */
public class RuleVoice1 extends Rule implements RuleInterface {
    
    
    //Разрыв между тенором и басом не должен превышать двух октав.
    public int check(AccordInterface prev, AccordInterface next) throws
        WrongAccordException,
        PauseException,
        SlightMistakeException,
        PlainMistakeException,
        BigMistakeException
    {
        
        if (prev.getTenor() - prev.getBass() > 2*Mode.OctaveSize) {
            throw new BigMistakeException();
        }
        
        return Rule.OK;
    }
}
