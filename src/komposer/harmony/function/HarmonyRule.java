package komposer.harmony.function;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import komposer.Accord;
import komposer.Mode;
import komposer.harmony.HarmonyChromosome;
import komposer.harmony.function.rules.Rule64_1;
import komposer.harmony.function.rules.Rule6_1;
import komposer.harmony.function.rules.Rule6_2;
import komposer.harmony.function.rules.Rule6_3;
import komposer.harmony.function.rules.Rule6_4;
import komposer.harmony.function.rules.RuleCadenz1;
import komposer.harmony.function.rules.RuleD7_1;
import komposer.harmony.function.rules.RuleFunc1;
import komposer.harmony.function.rules.RuleII53_1;
import komposer.harmony.function.rules.RuleII53_2;
import komposer.harmony.function.rules.RuleII6_1;
import komposer.harmony.function.rules.RuleII6_2;
import komposer.harmony.function.rules.RuleII6_3;
import komposer.harmony.function.rules.RuleInterface;
import komposer.harmony.function.rules.RuleVoice1;
import komposer.harmony.function.rules.RuleVoice2;
import komposer.harmony.function.rules.RuleVoice3;
import komposer.harmony.function.rules.RuleVoice4;
import komposer.harmony.function.rules.RuleVoice5;
import komposer.harmony.function.rules.RuleVoice6;
import komposer.harmony.function.rules.RuleVoice7;
import komposer.harmony.function.rules.RuleVoice8;

/**
 *
 * @author kuro
 */
public class HarmonyRule implements Comparator<HarmonyChromosome> {
    
    Mode mode;
    
    public void setMode(Mode m) {
        mode = m;
    }

    @Override
    public int compare(HarmonyChromosome ch1, HarmonyChromosome ch2) {
        return Integer.compare (check(ch1.getAccords(mode)) , check(ch2.getAccords(mode)));
    }
    
    public int check(HarmonyChromosome ch) {
        return check(ch.getAccords(mode));
    }
    
    public static class RuleCallable implements Callable {
        private Accord A1;
        private Accord A2;
        private RuleInterface rule;
        public RuleCallable( RuleInterface r, Accord a1, Accord a2) {
            A1 = a1;
            A2 = a2;
            rule = r;
        }
        public Integer call() {
            return rule.check(A1, A2);
        }
    }
    
    public int check(List<Accord> accords)  {
        
        List<RuleInterface> rules = new ArrayList<>();
        
        rules.add(new RuleFunc1());
        rules.add(new Rule6_1());
        rules.add(new Rule6_2());
        rules.add(new Rule6_3());
        rules.add(new Rule6_4());
        rules.add(new RuleII6_1());
        rules.add(new RuleII6_2());
        rules.add(new RuleII6_3());
        rules.add(new RuleD7_1());
        
        //rules.add(new RuleD7_2());
        
        rules.add(new Rule64_1());
        rules.add(new RuleII53_1());
        rules.add(new RuleII53_2()); //?
        
        rules.add(new RuleVoice1());
        rules.add(new RuleVoice2());
        rules.add(new RuleVoice3());
        rules.add(new RuleVoice4());
        rules.add(new RuleVoice5());
        rules.add(new RuleVoice6());
        rules.add(new RuleVoice7()); //?
        rules.add(new RuleVoice8()); //?
        
        for(RuleInterface rule : rules) {
            rule.setMode(mode);
        }
        
        int sum = 0;
        
        for(int i = 0; i< accords.size() - 1; i++) {
            /*ExecutorService pool = Executors.newFixedThreadPool(2);
            Map<Future<Integer>, RuleInterface> set = new HashMap();
            for(RuleInterface rule : rules) {
                set.put(pool.submit(new RuleCallable(rule, accords.get(i), accords.get(i+1))),rule);
            }
            
            Set<RuleInterface> rejectedRules = new HashSet<>();
            
            for (Future<Integer> future : set.keySet()) {
                try {
                    sum += future.get();
                } catch (InterruptedException ex) {
                    rejectedRules.add(set.get(future));
                } catch (ExecutionException ex) {
                    rejectedRules.add(set.get(future));
                }
            }
            
            for(RuleInterface rule : rejectedRules) {
                sum += rule.check(accords.get(i), accords.get(i+1));
            }
            */
            for(RuleInterface rule : rules) {
                sum += rule.check(accords.get(i), accords.get(i+1));
            }
        }
        
        int center = (int) Math.floor(accords.size() / 2 );
        
        RuleCadenz1 cRule = new RuleCadenz1();
        cRule.setMode(mode);
        sum += cRule.check(accords.get(center-1), accords.get(center));
        sum += cRule.check(accords.get(accords.size()-2), accords.get(accords.size()-1));
        
        return sum;
    }
    
}
