package komposer.harmony.function.rules;

import com.kuro.komposer.common.AccordInterface;
import com.kuro.komposer.common.Mode;

/**
 *
 * @author kuro
 */
public interface RuleInterface {
    public int check(AccordInterface prev, AccordInterface next);
    public void setMode(Mode mode);
}
