package komposer.harmony.function.rules;

import komposer.AccordInterface;
import komposer.Mode;

/**
 *
 * @author kuro
 */
public interface RuleInterface {
    public int check(AccordInterface prev, AccordInterface next);
    public void setMode(Mode mode);
}
