package komposer.genetic;

import java.util.List;
import komposer.harmony.HarmonyChromosome;

/**
 *
 * @author kuro
 */
public interface BreedOperator {
    public List<Chromosome> breed(CrossOperator cOp, List<Chromosome> parents);
}
