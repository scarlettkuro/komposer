/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komposer.genetic;

import java.util.Comparator;

/**
 *
 * @author kuro
 */
public interface FitnessFunction<T> extends Comparator<T> {
    public double check(T ch);
}
