/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komposer.harmony.function.rules;

import komposer.Accord;
import komposer.Mode;
import komposer.AccordInterface;
import komposer.WrongAccordException;
import komposer.harmony.function.rules.mistakes.*;

/**
 *
 * @author kuro
 */
public class RuleFunc1 extends Rule implements RuleInterface {
    
    /*
     Регулирование функциональных отношений
    */
    public int check(AccordInterface prev, AccordInterface next) throws
        WrongAccordException,
        SlightMistakeException,
        PlainMistakeException,
        BigMistakeException
    {
        
        if (prev.checkFunc("II") && (next.checkFunc("S") || next.checkFunc("VI") || next.checkFunc("VII") ) ) {
            throw new BigMistakeException();
        }
        
        if (prev.checkFunc("III") && (next.checkFunc("II") )) {
            throw new BigMistakeException();
        }
        
        if (prev.checkFunc("S") && (next.checkFunc("III") || next.checkFunc("VI") || next.checkFunc("VII") )) {
            throw new BigMistakeException();
        }
        
        if (prev.checkFunc("D") && (next.checkFunc("II") || next.checkFunc("S") )) {
            throw new BigMistakeException();
        }
        
        if (prev.checkFunc("VI") && (next.checkFunc("D") || next.checkFunc("VII") )) {
            throw new BigMistakeException();
        }
        
        if (prev.checkFunc("VII") && !(next.checkFunc("T") || next.checkFunc("VII") )) {
            throw new BigMistakeException();
        }
        
        return Rule.OK;
    }

    
}
