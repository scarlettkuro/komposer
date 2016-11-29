package komposer.harmony.mutation;

import java.util.List;
import komposer.harmony.Chromosome;

/**
 *
 * @author kuro
 */
public interface MutationOperator {
    public List<Chromosome> mutate(List<Chromosome> pool);
}
