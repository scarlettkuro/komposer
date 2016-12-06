
package komposer.harmony.select;

import java.util.ArrayList;
import java.util.List;
import komposer.harmony.Chromosome;
import komposer.harmony.function.HarmonyRule;
import static com.kuro.komposer.common.Utils.randomInt;
import static com.kuro.komposer.common.Utils.buildInverseP;
import static com.kuro.komposer.common.Utils.pickRandom;

/**
 *
 * @author kuro
 */
public class RouleteOperator implements SelectOperator {
    
    HarmonyRule rule;
    
    public void setRule(HarmonyRule r) {
        rule = r;
    }

    @Override
    public List<Chromosome> select(List<Chromosome> pool, int poolsize) {
        List<Chromosome> newpool = new ArrayList<>();
        
        List<Integer> values = new ArrayList<>();
        
        for (int i = 0; i < pool.size(); i++) {
            values.add(rule.check(pool.get(i)));
        }
        
        List<Double> p = buildInverseP(values);
        
        for (int i = 0; i < poolsize; i++) {
            int index = pickRandom(p);
            newpool.add(pool.get(index));
            
        }

        return newpool;
        
    }
    
}
