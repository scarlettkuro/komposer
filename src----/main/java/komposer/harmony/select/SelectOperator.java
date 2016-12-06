package komposer.harmony.select;

import java.util.List;
import komposer.harmony.Chromosome;
import komposer.harmony.function.HarmonyRule;

/**
 *
 * @author kuro
 */
public interface SelectOperator {
    public List<Chromosome> select(List<Chromosome> pool, int poolsize);
    public void setRule(HarmonyRule r);
}
