
package komposer.harmony.select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import komposer.harmony.Chromosome;
import komposer.harmony.function.HarmonyRule;
import static komposer.Utils.randomDouble;
import static komposer.Utils.buildInverseP;
import static komposer.Utils.pickRandom;

/**
 *
 * @author kuro
 */
public class RangeOperator implements SelectOperator {
    
    HarmonyRule rule;
    
    public void setRule(HarmonyRule r) {
        rule = r;
    }

    @Override
    public List<Chromosome> select(List<Chromosome> pool, int poolsize) {
        
        Collections.sort(pool, rule);
        
        List<Double> p = new ArrayList<>();
        double a = 1 + randomDouble();
        double N = pool.size();
        
        for (int i = 0; i < pool.size(); i++) {
            p.add(
                (a - (2 - a)*((double)i - 1)/(N-1))/N
            );
        }
        
        List<Chromosome> newpool = new ArrayList<>();
        
        for (int i = 0; i < poolsize; i++) {
            int index = pickRandom(p);
            newpool.add(pool.get(index));
            
        }

        return newpool;
        
    }
    
}
