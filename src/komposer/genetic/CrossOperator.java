package komposer.genetic;

import java.util.List;
import komposer.harmony.HarmonyChromosome;

/**
 *
 * @author kuro
 */
public interface CrossOperator {
    public List<Chromosome> cross(Chromosome ch1, Chromosome ch2);
}
