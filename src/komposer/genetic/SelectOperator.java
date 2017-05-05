package komposer.genetic;

import java.util.List;

/**
 *
 * @author kuro
 */
public interface SelectOperator<T> {
    public List<T> select(List<T> generation, int poolsize);
    public void setFitnessFunction(FitnessFunction<T> ff);
}
