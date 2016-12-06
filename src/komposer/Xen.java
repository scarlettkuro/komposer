/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komposer;

import java.util.*;
import org.apache.commons.math3.distribution.NormalDistribution;
/**
 *
 * @author kuro
 */
public class Xen {
    public Map<List, Double> normProbs(List objs) {
        int N = objs.size();
        Map<List, Double> map = new HashMap<>();
        double sigma = 1;
        double mean = 0;
        double step = 6*sigma/(double)N;
        NormalDistribution nd = new NormalDistribution(mean, sigma);
        //double s = 0;
        for (int i = 0; i < N; i++) {
            double prob = nd.cumulativeProbability(-3*sigma + (i+1)*step) - nd.cumulativeProbability(-3*sigma + i*step);
            List l = new ArrayList();
            l.add(objs.get(i));
            map.put(l, prob);
            //System.out.println(Arrays.asList(l) + " : " + prob);
           // s += prob;
        }
        
            //System.out.println("sum : " + s);
        return map;
    }
    
    public Map<List, Double> normProbs(Map<List, Double> m, int N, int n) {
        Map<List, Double> map = new HashMap<>();
        double s = 0;
        List<List> keys = new ArrayList<>(m.keySet());
        //System.out.println(keys.size());
        //System.out.println(m.values().size());
        List<List> newKeys = new ArrayList<>();
        List<Double> newValues = new ArrayList<>();
        for (int i =0 ; i<N; i++) {
            List <List> ls;
            boolean nk = true;
                    do {
                       ls  = new ArrayList<>();
                   
             for (int j =0 ; j<n; j++) {
                 List r = keys.get(Utils.pickRandom(m.values()));
                 ls.add(r);
                 nk = nk && ls.get(0).equals(r);
             }
                    } while (nk);
            //List l2;
           // do {
                //l1 = 
                //l2 = keys.get(Utils.pickRandom(m.values()));
            //} while (l1.equals(l2) && N > 1);
            double prob = m.get(ls.get(0));
            for (int j =1 ; j<n; j++) {
                prob *=m.get(ls.get(j));
            }
            s += prob;
            List l = new ArrayList();
            for (List ll : ls) {
                l.addAll(ll);
            }
            newKeys.add(l);
            newValues.add(prob);
        }
        
        for (int i =0 ; i<N; i++) {
            map.put(newKeys.get(i), newValues.get(i)/s);
        }
        
        return map;
    }
}
