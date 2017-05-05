
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
public class RuleVoice2 extends Rule implements RuleInterface {
    
    
    /*
     Разрыв между соседними голосами (за исключением расстояния между басом и 
     тенором) не должен превышать октавы. 
    */
    public int check(AccordInterface prev, AccordInterface next) throws
        WrongAccordException,
        SlightMistakeException,
        PlainMistakeException,
        BigMistakeException
    {
        
        for (int i = 0; i < prev.getPitches().size()-1; i++) {
            if (prev.getPitches().get(i+1) -prev.getPitches().get(i) > Mode.OctaveSize) {
                throw new BigMistakeException();
            }
        }
        
        return Rule.OK;
    }
}
