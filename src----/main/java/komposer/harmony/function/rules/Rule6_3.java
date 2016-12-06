
package komposer.harmony.function.rules;

import java.util.HashMap;
import java.util.Iterator;
import com.kuro.komposer.common.Accord;
import com.kuro.komposer.common.Mode;
import java.util.Map;
import com.kuro.komposer.common.AccordInterface;

/**
 *
 * @author kuro
 */
public class Rule6_3 extends Rule implements RuleInterface {
    
    /*
     Соединение секстаккорда и трезвучия, находящихся в кварто-квинто	вом и терцовом соотношении должно быть по возможности гармоническим.
    */
    public int check(AccordInterface prev, AccordInterface next) {
        if (prev.checkInversion("6") && ((7 + (next.getBaseDegree() - prev.getBaseDegree()))%7 > 2) ) {
            if (Rule.getCommonVoices(prev, next) == 0) {
                return Rule.slightMistake;
            }
        }
        
        return Rule.OK;
    }
}
