package komposer.harmony.function.rules;

import komposer.Mode;
import java.util.Map;
import komposer.AccordInterface;

/**
 *
 * @author kuro
 */
public class Rule6_1 extends Rule implements RuleInterface {
    
    /*
     В главных секстаккордах (Т6, S6, D6) традиционно удваивается либо прима, либо квинтовый тон
    */
    public int check(AccordInterface prev, AccordInterface next) {
        if (!prev.checkInversion("6")) {
            return Rule.OK;
        }
        
        Map<Integer,Boolean> doubledDegrees = Rule.getDoubledDegrees(prev.getPitches(), mode);
        if (doubledDegrees.get(prev.getTertia())) {
            return Rule.plainMistake;
        }
        
        return Rule.OK;
    }
}
