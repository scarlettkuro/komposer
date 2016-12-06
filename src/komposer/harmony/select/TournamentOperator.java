
package komposer.harmony.select;

import komposer.genetic.SelectOperator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import komposer.harmony.HarmonyChromosome;
import komposer.harmony.function.HarmonyRule;
import static komposer.Utils.randomInt;
import komposer.genetic.Chromosome;
import komposer.genetic.FitnessFunction;

/**
 *
 * @author kuro
 */
public class TournamentOperator implements SelectOperator {
    
    FitnessFunction fitnessFunction;
    int tour = 2;
    
    @Override
    public void setFitnessFunction(FitnessFunction ff) {
        fitnessFunction = ff;
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

        return Collections.min(tourpool, fitnessFunction);
        
    }
    
}
