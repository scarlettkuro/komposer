
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
public class RuleII53_2 extends Rule implements RuleInterface {
    
    /*
     II53 с D53 и K64 соединяется мелодически, с противоположным мелодическим 
     движением в крайних голосах. 
    */
    public int check(AccordInterface prev, AccordInterface next) throws
        WrongAccordException,
        PauseException,
        SlightMistakeException,
        PlainMistakeException,
        BigMistakeException
    {
        
        List<Integer> common = new ArrayList<>(prev.getPitches());
        common.retainAll(next.getPitches());
        
        if ( 
            prev.checkName("II53") 
            && (next.checkName("D53") || next.checkName("K64"))
        ) { 
            int dMelody = prev.getMelody() - next.getMelody();
            int dBase = prev.getBass() - next.getBass();
 
                if (dMelody * dBase >= 0)  {
                    throw new SlightMistakeException();
                }
        }
        return Rule.OK;
        
        
    }
}
