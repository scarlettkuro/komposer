
package komposer.harmony.select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import komposer.harmony.Chromosome;
import komposer.harmony.function.HarmonyRule;
import static komposer.Utils.randomInt;

/**
 *
 * @author kuro
 */
public class TournamentOperator implements SelectOperator {
    
    HarmonyRule rule;
    int tour = 2;
    
    public void setRule(HarmonyRule r) {
        rule = r;
    }
    
    public void setTour(int t) {
        tour = t;
    }

    @Override
    public List<Chromosome> select(List<Chromosome> pool, int poolsize) {
        List<Chromosome> newpool = new ArrayList<>();
        
        for (int i = 0; i < poolsize; i++) {
            newpool.add(selectOne(pool));
        }

        return newpool;
        
    }
    
    public Chromosome selectOne(List<Chromosome> pool) {
        List<Chromosome> tourpool = new ArrayList<>();
        for (int j = 0; j < tour; j++) {
            tourpool.add(pool.get(randomInt(pool.size())));
        }

        return Collections.min(tourpool, rule);
        
    }
    
}
