
package komposer;
import komposer.harmony.Harmonizer;
import java.util.ArrayList;
import java.util.Arrays;
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
import komposer.harmony.cross.MixupOperator;
import komposer.harmony.function.HarmonyRule;
import komposer.harmony.mutation.SimpleAllelMutation;
import komposer.harmony.select.EliteOperator;
import komposer.harmony.select.RangeOperator;
import komposer.harmony.select.RouleteOperator;
import komposer.harmony.select.SelectOperator;
import komposer.harmony.select.TournamentOperator;
import komposer.harmony.select.UniformOperator;
/**
 *
 * @author kuro
 */
public class Komposer {
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        Player p = new Player();
        Mode mode = new Mode(Mode.naturalMajor,0);     
        Melodizer m = new Melodizer(mode);
        
        List<Playble> period = m.makePeriod(4, 4, 2);
            
        Harmonizer h = new Harmonizer(mode);
        
        h.setMaxIterations(500);
        
        HarmonyRule rule = new HarmonyRule();
        rule.setMode(mode);
        h.setRule(rule);
        
        List<SelectOperator> sOps = new ArrayList<>();
        UniformOperator un = new UniformOperator();
        un.setThreshold(0.5f);
        sOps.add(new EliteOperator());
        /*sOps.add(new RangeOperator());
        sOps.add(new RouleteOperator());
        sOps.add(new TournamentOperator());
        sOps.add(un);*/
        
        Map<String, int[]> map = new HashMap<>();
        //map.put("poolSize", new int[]{4, 6, 8, 10, 12});
        map.put("mutation", new int[]{5, 10, 15, 20, 25, 30, 35});
        //map.put("amount", new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        //map.put("size", new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        
        //for (float t = 0.15f; t < 0.75f; t+=0.15f) {
        //    un.setThreshold(t);
            for (Map.Entry<String, int[]> entry : map.entrySet()) {
                System.out.println("//" + entry.getKey());
                System.out.println("splot(" +  Arrays.toString(entry.getValue()) + ", [");
                List<String> legs =new ArrayList<>();
                for (SelectOperator sOp : sOps) {
                   sss(h,period,sOp,entry.getKey(), entry.getValue(), 5);
                   legs.add("'" + sOp.getClass().getSimpleName() + "'");
                }
                System.out.println("]', ["+ String.join(" , ",legs)+  "], '"+entry.getKey() + "' );");
            }
        //}
        
        //Sequence s1 = p.buildSequence(music);
        
        //p.play(s1);
        //p.save(s1,"x");
        System.exit(0);
    }
    
    public static class HarmonizeCallable implements Callable {
        private Harmonizer h;
        private List<Playble> melody;
        public HarmonizeCallable(Harmonizer h, List<Playble> melody) {
            this.h = h;
            this.melody = melody;
        }
        public Double call() {
            return h.sharmonize(melody);
        }
    }
    
    
    public static void sss(Harmonizer h, List<Playble> melody, SelectOperator sOp, String type, int[] v, int N) throws InterruptedException, ExecutionException {
        
       
        int[] poolSizes = type.equals("poolSize") ? v : new int[]{6};
        int[] mutations = type.equals("mutation") ? v : new int[]{5};
        int[] amounts = type.equals("amount") ? v : new int[]{2};
        int[] sizes = type.equals("size") ? v : new int[]{2};
        
        String info = "//" + sOp.getClass().getSimpleName()
            + "; pool size: " + Arrays.toString(poolSizes)
            + "; mutation: " + Arrays.toString(mutations)
            + "; amount: " + Arrays.toString(amounts)
            + "; size: " + Arrays.toString(sizes)
            + "; mean from: " + N
            ;
        System.out.println(info);
        
        List<Double> means = new ArrayList<>();
        
        SimpleAllelMutation mOp = new SimpleAllelMutation();
        h.setMutationOperator(mOp);
        MixupOperator cOp = new MixupOperator();
        h.setCrossOperator(cOp);
        
        h.setSelectOperator(sOp);
        for (int poolSize : poolSizes) {
            h.setPool(poolSize);
            for (int mutation : mutations) {
                mOp.setMut(mutation);
                for (int amount : amounts) {
                    cOp.setAmount(amount);
                    for (int size : sizes) {
                        cOp.setSize(size);
                        double mean = 0;
                        /*for (int i = 0; i < N; i++) {
                            mean += h.sharmonize(melody)/N;
                        }*/
                        ExecutorService pool = Executors.newFixedThreadPool(2);
                        Set<Future<Float>> set = new HashSet<Future<Float>>();
                        for (int i = 0; i < N; i++) {
                            Future<Float> future = pool.submit(new HarmonizeCallable(h, melody));
                            set.add(future);
                        }
                        for (Future<Float> future : set) {
                            mean += future.get()/N;
                        }
                        means.add(mean);
                    }
                }
            }
        }
        
        System.out.println(/*sOp.getClass().getSimpleName() + "_" + type + " = " + */Arrays.toString(means.toArray()) + ";");
    }
    
    public static List<List<Double>> sss2(Harmonizer h, List<Playble> melody, SelectOperator sOp, String type, int[] values, int N) throws InterruptedException, ExecutionException {
        
        List<List<Double>> means = new ArrayList<>();
        
        SimpleAllelMutation mOp = new SimpleAllelMutation();
        mOp.setMut(5);
        h.setMutationOperator(mOp);
        MixupOperator cOp = new MixupOperator();
        cOp.setAmount(2);
        cOp.setSize(2);
        h.setCrossOperator(cOp);
        
        h.setSelectOperator(sOp);
        
        
        h.setPool(6);
        
        for (int v : values) {
            //upte
             if (type.equals("poolSize")) {
                h.setPool(v);
            }
            else if (type.equals("mutation")) {
               mOp.setMut(v);
            }
            else if ( type.equals("amount")) {
                cOp.setAmount(v);
            }
            else if (type.equals("size")) {
                cOp.setSize(v);
            }
            //clculte
            ExecutorService pool = Executors.newFixedThreadPool(2);
            Set<Future<Double>> set = new HashSet<Future<Double>>();
            for (int i = 0; i < N; i++) {
                set.add(pool.submit(new HarmonizeCallable(h, melody)));
            }
            
            List<Double> mean = new ArrayList<>();
            for (Future<Double> future : set) {
                mean.add(future.get()/N);
            }
            means.add(mean);
                    
        }
        
        return means;
        
    }
    
    public static void print(List<Playble> ps) {
        for (int i = 0; i < ps.get(0).getPitches().size(); i++) {
            for(Playble p : ps) {
                System.out.print(Mode.getPitchName(p.getPitches().get(i)) + "\t");
            }
            System.out.println("");
        }
    }
    
    public static void printA(List<Accord> ps) {
            for(Accord p : ps) {
                System.out.print(p.getAccordName() + "\t");
            }
            System.out.println("");
    }
    
    public static List concat(List a, List b) {
        List<Accord> sum = new ArrayList<>();
        sum.addAll(a);
        sum.addAll(b);
        
        return sum;
    }
    
    public static List concat(List a, List b, List c) {
        List<Accord> sum = new ArrayList<>();
        sum.addAll(a);
        sum.addAll(b);
        sum.addAll(c);
        
        return sum;
    }
}
