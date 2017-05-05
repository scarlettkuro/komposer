package komposer.harmony.function.rules;

import komposer.AccordInterface;
import komposer.Mode;
import komposer.PauseException;
import komposer.WrongAccordException;
import komposer.harmony.function.rules.mistakes.*;
/**
 *
 * @author kuro
 */
public interface RuleInterface {
    public int check(AccordInterface prev, AccordInterface next) throws
        WrongAccordException,
        PauseException,
        SlightMistakeException,
        PlainMistakeException,
        BigMistakeException
        //StructMistakeException
        ;
    public void setMode(Mode mode);
}
