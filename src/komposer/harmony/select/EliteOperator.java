
package komposer.harmony.select;

import komposer.genetic.SelectOperator;
import java.util.Collections;
import java.util.List;
import komposer.genetic.Chromosome;
import komposer.genetic.FitnessFunction;
import komposer.harmony.HarmonyChromosome;
import komposer.harmony.function.HarmonyRule;

/**
 *
 * @author kuro
 */
public class EliteOperator implements SelectOperator {
    
    FitnessFunction fitnessFunction;
    
    @Override
    public void setFitnessFunction(FitnessFunction ff) {
        fitnessFunction = ff;
    }

    @Override
    public List<Chromosome> select(List<Chromosome> pool, int poolsize) {
        
        Collections.sort(pool, fitnessFunction);

        return pool.subList(0, poolsize);
        
    }
    
}
