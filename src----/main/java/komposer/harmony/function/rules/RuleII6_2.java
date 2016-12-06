
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
public class RuleII6_2 extends Rule implements RuleInterface {
    
    /*
     Употреблять после T53, T6, VI53, S53 в мелодическом положении примы.
    */
    public int check(AccordInterface prev, AccordInterface next) {
        if (!next.checkName("II6")) {
            return Rule.OK;
        }
        
        if ((next.getMelodyDegree() != next.getPrima()) && 
            (prev.checkName("T53") || prev.checkName("T6") || prev.checkName("VI53") || prev.checkName("S53")) 
        ) {
            return Rule.plainMistake;
        }
        
        return Rule.OK;
    }
}
