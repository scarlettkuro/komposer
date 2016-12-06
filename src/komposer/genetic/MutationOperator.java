package komposer.genetic;

import java.util.List;
import komposer.harmony.HarmonyChromosome;

/**
 *
 * @author kuro
 */
public interface MutationOperator {
    public List<Chromosome> mutate(List<Chromosome> pool);
}
