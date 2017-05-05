
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
public class RuleVoice6 extends Rule  implements RuleInterface {
    
    
    /*
     Скачка в теноре лучше избежать, заменив одно из трезвучий другим видом аккорда.
    */
    public int check(AccordInterface prev, AccordInterface next) throws
        WrongAccordException,
        PauseException,
        SlightMistakeException,
        PlainMistakeException,
        BigMistakeException
    {
        
        if (Math.abs(prev.getTenor() - next.getTenor()) > 3 ) {
            throw new PlainMistakeException();
        }
        
        return Rule.OK;
    }
}
