
package komposer.harmony.function.rules;

import java.util.ArrayList;
import komposer.Accord;
import komposer.Mode;
import java.util.List;
import komposer.AccordInterface;
import komposer.WrongAccordException;
import komposer.harmony.function.rules.mistakes.*;

/**
 *
 * @author kuro
 */
public class RuleCadenz1 extends Rule implements RuleInterface {
    
    /*
     II53 с D53 и K64 соединяется мелодически, с противоположным мелодическим 
     движением в крайних голосах. 
    */
    public int check(AccordInterface c1, AccordInterface c2) throws
        WrongAccordException,
        SlightMistakeException,
        PlainMistakeException,
        BigMistakeException
    {
        
        String cdz = c1.getFunc() + c2.getFunc();
        
        if (
            cdz.equals("TD") || cdz.equals("DT") ||
            cdz.equals("TS") || cdz.equals("ST")
        ) { 
            return Rule.OK;
        }
        //throw new Exception();
        return 0;
        
        
    }
}
