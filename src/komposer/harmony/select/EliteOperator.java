
package komposer.harmony.select;

import komposer.genetic.SelectOperator;
import java.util.Collections;
import java.util.List;
import komposer.harmony.HarmonyChromosome;
import komposer.harmony.function.HarmonyRule;

/**
 *
 * @author kuro
 */
public class EliteOperator implements SelectOperator {
    
    HarmonyRule rule;
    
    public void setRule(HarmonyRule r) {
        rule = r;
    }

    @Override
    public List<HarmonyChromosome> select(List<HarmonyChromosome> pool, int poolsize) {
        
        Collections.sort(pool, rule);

        return pool.subList(0, poolsize);
        
    }
    
}
