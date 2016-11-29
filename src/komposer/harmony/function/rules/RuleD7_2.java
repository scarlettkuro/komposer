
package komposer.harmony.function.rules;

import komposer.Accord;
import komposer.Mode;
import komposer.AccordInterface;

/**
 *
 * @author kuro
 */
public class RuleD7_2 extends Rule  implements RuleInterface  {
    
    /*
     Основной тон при разрешении обращений D7 в аккорды тоники остается на месте как устойчивый и общий. 
    */
    public int check(AccordInterface prev, AccordInterface next) {
        /*
        if (next.checkName("D7") && prev.checkFunc("S") ) {
            int septVoice = -1;
            for(int i = 0; i < 2; i++) {
                if (mode.getDegree(next.getPitches().get(i)) == next.getSeptima()) {
                septVoice = i;
                }
            }
            if (septVoice>0 &&) {
                
            }
            
        }
                i(prev.getPitches().get(3) != next.getPitches().get(3)) ) {
            return Rule.plainMistake;
        }
        */
        return Rule.OK;
    }
}
