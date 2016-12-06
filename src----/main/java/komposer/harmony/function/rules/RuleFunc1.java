/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komposer.harmony.function.rules;

import com.kuro.komposer.common.Accord;
import com.kuro.komposer.common.Mode;
import com.kuro.komposer.common.AccordInterface;

/**
 *
 * @author kuro
 */
public class RuleFunc1 extends Rule implements RuleInterface {
    
    /*
     Регулирование функциональных отношений
    */
    public int check(AccordInterface prev, AccordInterface next) {
        
        if (prev.checkFunc("II") && (next.checkFunc("S") || next.checkFunc("VI") || next.checkFunc("VII") ) ) {
            return Rule.bigMistake;
        }
        
        if (prev.checkFunc("III") && (next.checkFunc("II") )) {
            return Rule.bigMistake;
        }
        
        if (prev.checkFunc("S") && (next.checkFunc("III") || next.checkFunc("VI") || next.checkFunc("VII") )) {
            return Rule.bigMistake;
        }
        
        if (prev.checkFunc("D") && (next.checkFunc("II") || next.checkFunc("S") )) {
            return Rule.bigMistake;
        }
        
        if (prev.checkFunc("VI") && (next.checkFunc("D") || next.checkFunc("VII") )) {
            return Rule.bigMistake;
        }
        
        if (prev.checkFunc("VII") && !(next.checkFunc("T") || next.checkFunc("VII") )) {
            return Rule.bigMistake;
        }
        
        return Rule.OK;
    }

    
}
