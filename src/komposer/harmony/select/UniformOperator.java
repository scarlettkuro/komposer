
package komposer.harmony.select;

import komposer.genetic.SelectOperator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static komposer.Utils.randomInt;
import komposer.genetic.Chromosome;
import komposer.genetic.FitnessFunction;
import komposer.harmony.HarmonyChromosome;
import komposer.harmony.function.HarmonyRule;

/**
 *
 * @author kuro
 */
public class UniformOperator implements SelectOperator {
    
    FitnessFunction fitnessFunction;
    float threshold = 0;

    @Override
    public void setFitnessFunction(FitnessFunction ff) {
        fitnessFunction = ff;
    }
    
    public void setThreshold(float t) {
        threshold = t;
    }
    
    
    private int makeThreshold(int size) {
        return Math.round(((float)size)*threshold);
    }

    @Override
    public List<Chromosome> select(List<Chromosome> pool, int poolsize) {
        
        int thresh = makeThreshold(pool.size());
        
        Collections.sort(pool, fitnessFunction);
        List<Chromosome> hpool = pool.subList(0, pool.size() - thresh);
        
        List<Chromosome> newpool = new ArrayList<>();
        
        for (int i = 0; i < poolsize; i++) {
            newpool.add(selectOne(hpool));
        }

        return newpool;
        
    }
    
    public Chromosome selectOne(List<Chromosome> hpool) {
        return hpool.get(randomInt(hpool.size()));
        
    }
    
}
