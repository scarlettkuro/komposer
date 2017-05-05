package komposer.genetic;

import java.util.List;

/**
 *
 * @author kuro
 */
public interface BreedOperator<T> {
    public List<T> breed(CrossOperator cOp, List<T> parents);
    public int poolsize(List<T> gen);
}
