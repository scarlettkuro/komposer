
package komposer.harmony.function.rules;

import java.util.ArrayList;
import com.kuro.komposer.common.Accord;
import com.kuro.komposer.common.Mode;
import java.util.List;
import com.kuro.komposer.common.AccordInterface;

/**
 *
 * @author kuro
 */
public class RuleVoice6 extends Rule  implements RuleInterface {
    
    
    /*
     Скачка в теноре лучше избежать, заменив одно из трезвучий другим видом аккорда.
    */
    public int check(AccordInterface prev, AccordInterface next) {
        
        if (Math.abs(prev.getTenor() - next.getTenor()) > 3 ) {
            return Rule.plainMistake;
        }
        
        return Rule.OK;
    }
}
