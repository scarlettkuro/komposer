package komposer.genetic;

import java.util.List;

/**
 *
 * @author kuro
 */
public interface CrossOperator<T> {
    public List<T> cross(T ch1, T ch2);
}
