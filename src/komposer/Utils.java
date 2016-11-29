/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komposer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import komposer.harmony.Chromosome;

/**
 *
 * @author kuro
 */
public class Utils {
    
    private static final Random r = new Random();
    
    static public int randomInt(int bound) {
        return r.nextInt(bound);
    }
    
    
    static public double randomDouble() {
        return r.nextDouble();
    }
    
    static public boolean randomBoolean() {
        return r.nextBoolean();
    }
    
    static public int pickRandom(List<Double> values) {
        
        double r = randomDouble();
        int i = -1;
        while( r > 0 && (i+1) < values.size() ) {
            i++;
            r -= values.get(i);
        }
        
        return i;
        
    }
    
    static public List<Double> buildInverseP(List<Integer> values) {
        
        int N = values.size();
        int S = 0;
        
        for (Integer v : values) {
            S += v;
        }
        
        List<Double> p = new ArrayList<>();
        
        for (Integer v : values) {
            p.add((double)(
                (double)(S - v) / (double)(S*(N-1))
            ));
        }
        
        return p;
        
    }
    
}
