
package komposer.harmony.select;

import java.util.Collections;
import java.util.List;
import komposer.harmony.Chromosome;
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
    public List<Chromosome> select(List<Chromosome> pool, int poolsize) {
        
        Collections.sort(pool, rule);

        return pool.subList(0, poolsize);
        
    }
    
}
