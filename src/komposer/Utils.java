/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komposer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import komposer.harmony.HarmonyChromosome;

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
    
    static public int pickRandom(Collection<Double> values) {
        
        double r = randomDouble();
        int i = -1;
        Iterator<Double> vi = values.iterator();
        while( r > 0 && /*(i+1) < values.size()*/ vi.hasNext() ) {
            i++;
            r -= vi.next();
        }
        
        return i;
        
    }
    
    static public List<Double> buildInverseP(List<Double> values) {
        
        Double N = (double)values.size();
        Double S = 0.0;
        
        for (Double v : values) {
            S += v;
        }
        
        List<Double> p = new ArrayList<>();
        
        for (Double v : values) {
            p.add(
                (S - v) / (S*(N-1))
            );
        }
        
        return p;
        
    }
    
    static public List<Double> ps(List<Double> values) {
        
        int N = values.size();
        int S = 0;
        
        List<Double> p = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }
                double ph = (values.get(i) + values.get(j)) / (4*(N-1));
                p.add(ph);
                p.add(ph);
            }
        }
        
        return p;
        
    }
    
}
