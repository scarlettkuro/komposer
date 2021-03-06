package komposer.harmony.function.rules;

import com.kuro.komposer.common.Mode;
import java.util.Map;
import com.kuro.komposer.common.AccordInterface;

/**
 *
 * @author kuro
 */
public class Rule64_1 extends Rule implements RuleInterface {
    
    /*
     В квартсекстаккордах всегда удваивается квинтовый тон
    */
    public int check(AccordInterface prev, AccordInterface next) {
        Map<Integer,Boolean> doubledDegrees = Rule.getDoubledDegrees(prev.getPitches(), mode);
        if (prev.checkInversion("64") && !doubledDegrees.get(prev.getQuinta())) {
            return Rule.plainMistake;
        }
        
        return Rule.OK;
    }
}
