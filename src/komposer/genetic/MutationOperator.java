package komposer.genetic;

import java.util.List;

/**
 *
 * @author kuro
 */
public interface MutationOperator<T> {
    public List<T> mutate(List<T> pool);
}
