
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
public class RuleII6_3 extends Rule implements RuleInterface {
    
    /*
     Употреблять перед D53, D7, K64 в мелодическом положении примы.
    */
    public int check(AccordInterface prev, AccordInterface next) {
        if (!prev.checkName("II6")) {
            return Rule.OK;
        }
        
        if ((prev.getMelodyDegree() != prev.getPrima()) && 
            (next.checkName("D53") || next.checkName("D7") || next.checkName("K64")) 
        ) {
            return Rule.plainMistake;
        }
        
        return Rule.OK;
    }
}
