
package komposer.harmony.function.rules;

import komposer.Accord;
import komposer.Mode;
import komposer.AccordInterface;

/**
 *
 * @author kuro
 */
public class RuleVoice1 extends Rule implements RuleInterface {
    
    
    //Разрыв между тенором и басом не должен превышать двух октав.
    public int check(AccordInterface prev, AccordInterface next) {
        
        if (prev.getTenor() - prev.getBass() > 2*Mode.OctaveSize) {
            return Rule.bigMistake;
        }
        
        return Rule.OK;
    }
}
