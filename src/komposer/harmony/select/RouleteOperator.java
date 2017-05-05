
package komposer.harmony.select;

import komposer.genetic.SelectOperator;
import java.util.ArrayList;
import java.util.List;
import komposer.harmony.HarmonyChromosome;
import komposer.harmony.function.HarmonyRule;
import static komposer.Utils.randomInt;
import static komposer.Utils.buildInverseP;
import static komposer.Utils.pickRandom;
import komposer.genetic.FitnessFunction;

/**
 *
 * @author kuro
 */
public class RouleteOperator implements SelectOperator<HarmonyChromosome> {
    
    FitnessFunction<HarmonyChromosome> fitnessFunction;
    
    @Override
    public void setFitnessFunction(FitnessFunction<HarmonyChromosome> ff) {
        fitnessFunction = ff;
    }

    @Override
    public List<HarmonyChromosome> select(List<HarmonyChromosome> pool, int poolsize) {
        List<HarmonyChromosome> newpool = new ArrayList<>();
        
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
