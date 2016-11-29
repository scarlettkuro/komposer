
package komposer.harmony.function.rules;

import java.util.ArrayList;
import komposer.Accord;
import komposer.Mode;
import java.util.List;
import komposer.AccordInterface;

/**
 *
 * @author kuro
 */
public class RuleCadenz1 extends Rule implements RuleInterface {
    
    /*
     II53 с D53 и K64 соединяется мелодически, с противоположным мелодическим 
     движением в крайних голосах. 
    */
    public int check(AccordInterface c1, AccordInterface c2) {
        
        String cdz = c1.getFunc() + c2.getFunc();
        
        if (
            cdz.equals("TD") || cdz.equals("DT") ||
            cdz.equals("TS") || cdz.equals("ST")
        ) { 
            return Rule.OK;
        }
        return Rule.structMistake;
        
        
    }
}
