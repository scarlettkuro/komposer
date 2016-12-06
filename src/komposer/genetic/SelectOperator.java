package komposer.genetic;

import java.util.List;
import komposer.harmony.HarmonyChromosome;
import komposer.harmony.function.HarmonyRule;

/**
 *
 * @author kuro
 */
public interface SelectOperator {
    public List<Chromosome> select(List<Chromosome> generation, int poolsize);
    public void setRule(HarmonyRule r);
}
