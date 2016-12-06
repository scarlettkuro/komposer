package komposer.harmony.cross;

import java.util.List;
import komposer.harmony.Chromosome;

/**
 *
 * @author kuro
 */
public interface CrossOperator {
    public List<Chromosome> cross(Chromosome ch1, Chromosome ch2) ;
}
