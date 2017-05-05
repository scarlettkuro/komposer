
package komposer.harmony.function.rules;

import java.util.HashMap;
import java.util.Iterator;
import komposer.Accord;
import komposer.Mode;
import java.util.Map;
import komposer.AccordInterface;
import komposer.PauseException;
import komposer.WrongAccordException;
import komposer.harmony.function.rules.mistakes.*;

/**
 *
 * @author kuro
 */
public class Rule6_3 extends Rule implements RuleInterface {
    
    /*
     Соединение секстаккорда и трезвучия, находящихся в кварто-квинто	вом и терцовом соотношении должно быть по возможности гармоническим.
    */
    public int check(AccordInterface prev, AccordInterface next) throws
        WrongAccordException,
        PauseException,
        SlightMistakeException,
        PlainMistakeException,
        BigMistakeException
    {
        if (prev.checkInversion("6") && ((7 + (next.getBaseDegree() - prev.getBaseDegree()))%7 > 2) ) {
            if (Rule.getCommonVoices(prev, next) == 0) {
                throw new SlightMistakeException();
            }
        }
        
        return Rule.OK;
    }
}
