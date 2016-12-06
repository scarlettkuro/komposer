
package komposer.harmony.select;

import komposer.genetic.SelectOperator;
import java.util.ArrayList;
import java.util.List;
import komposer.harmony.HarmonyChromosome;
import komposer.harmony.function.HarmonyRule;
import static komposer.Utils.randomInt;
import static komposer.Utils.buildInverseP;
import static komposer.Utils.pickRandom;
import komposer.genetic.Chromosome;
import komposer.genetic.FitnessFunction;

/**
 *
 * @author kuro
 */
public class RouleteOperator implements SelectOperator {
    
    FitnessFunction fitnessFunction;
    
    @Override
    public void setFitnessFunction(FitnessFunction ff) {
        fitnessFunction = ff;
    }

    @Override
    public List<Chromosome> select(List<Chromosome> pool, int poolsize) {
        List<Chromosome> newpool = new ArrayList<>();
        
        List<Double> values = new ArrayList<>();
        
        for (int i = 0; i < pool.size(); i++) {
            values.add(fitnessFunction.check(pool.get(i)));
        }
        
        List<Double> p = buildInverseP(values);
        
        for (int i = 0; i < poolsize; i++) {
            int index = pickRandom(p);
            newpool.add(pool.get(index));
            
        }

        return newpool;
        
    }
    
}
