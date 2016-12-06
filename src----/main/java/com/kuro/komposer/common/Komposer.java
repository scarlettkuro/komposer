
package com.kuro.komposer.common;
import java.io.FileNotFoundException;
import java.io.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import komposer.harmony.Harmonizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
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
    public void main(String[] args) throws InterruptedException, ExecutionException, FileNotFoundException, IOException, ClassNotFoundException {
        
        Player p = new Player();
        Mode mode = new Mode(Mode.naturalMajor,0);     
        Melodizer m = new Melodizer(mode);
        
        List<Playble> period = m.makePeriod(4, 4, 2);
            
        Harmonizer h = new Harmonizer(mode);
        
        h.setMaxIterations(500);
        
        HarmonyRule rule = new HarmonyRule();
        rule.setMode(mode);
        h.setRule(rule);
        /*
        List<SelectOperator> sOps = new ArrayList<>();
        UniformOperator un = new UniformOperator();
        un.setThreshold(0.5f);
        sOps.add(new EliteOperator());
        sOps.add(new RangeOperator());
        sOps.add(new RouleteOperator());
        sOps.add(new TournamentOperator());
        sOps.add(un);
        */
        //Map<String, int[]> map = new HashMap<>();
        //map.put("poolSize", new int[]{4, 6, 8, 10, 12, 14});
        //map.put("mutation", new int[]{5, 10, 15, 20, 25, 30, 35});
        //map.put("amount", new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        //map.put("size", new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        
        
        
        /*
        for (Map.Entry<String, int[]> entry : map.entrySet()) {
            List<List<List<Double>>> means = new ArrayList<>();
            List<String> legs =new ArrayList<>();
            for (SelectOperator sOp : sOps) {
                means.add(sss(h,period,sOp,entry.getKey(), entry.getValue(), 50));
                legs.add("'" + sOp.getClass().getSimpleName() + "'");
            }
            pp(entry, means, legs);
            serializ(means,"D:\\Dropbox\\diploma\\" +  entry.getKey() + "__.jav");
        }
        */
        
        int N = 200;
        
        List<List<Double>> means = new ArrayList<>();
        
        EliteOperator eo = new EliteOperator();
        h = setH(h, 12, 35, 1, 3, eo);
        means.add(s_s(h,period, N));
        
        RangeOperator ro = new RangeOperator();
        h = setH(h, 12, 35, 6, 1, ro);
        means.add(s_s(h,period, N));
        
        TournamentOperator to = new TournamentOperator();
        to.setTour(6);
        h = setH(h, 12, 35, 7, 1, to);
        means.add(s_s(h,period, N));
        
        UniformOperator uo = new UniformOperator();
        uo.setThreshold(0.65f);
        h = setH(h, 12, 25, 6, 1, uo);
        means.add(s_s(h,period, N));
            
        List<SelectOperator> sOps = new ArrayList<>();
        sOps.add(eo);
        sOps.add(ro);
        sOps.add(to);
        sOps.add(uo);
        List<String> legs = new ArrayList<>();
        for (SelectOperator sOp : sOps) {
            legs.add("'" + sOp.getClass().getSimpleName() + "'");
        }
        p_p(means, legs, "total2");
        serializ(means,"D:\\Dropbox\\diploma\\total2.jav");
        
        
        /*
        int N = 100;
        
        UniformOperator uo = new UniformOperator();
        h = setH(h, 12, 25, 6, 1, uo);
        List<List<Double>> means = new ArrayList<>();
        List<String> legs = new ArrayList<>();
        for (float i = 0.2f; i <= 0.8f; i+=0.15f) {
            uo.setThreshold(i);
            legs.add(String.valueOf(i));
            means.add(s_s(h,period, N));
        }
        p_p(means, legs, "uniform");
        serializ(means,"D:\\Dropbox\\diploma\\uniform.jav");
        */
        
        /*
        int N = 100;
        TournamentOperator to = new TournamentOperator();
        h = setH(h, 12, 35, 7, 1, to);
        List<List<Double>> means = new ArrayList<>();
        List<String> legs = new ArrayList<>();
        for (int i = 2; i <= 10; i+=2) {
            to.setTour(i);
            legs.add(String.valueOf(i));
            means.add(s_s(h,period, N));
        }
        p_p(means, legs, "tournament");
        serializ(means,"D:\\Dropbox\\diploma\\tournament.jav");
        */
        
        /*
        FileInputStream fis = new FileInputStream("D:\\Dropbox\\diploma\\total.jav");
        ObjectInputStream oin = new ObjectInputStream(fis);
        List<List<Double>> ts = (List<List<Double>>) oin.readObject();
        for (List<Double> t : ts) {
            System.out.println(t.stream().reduce(0.0, (a, b) -> a + b/ts.get(0).size()));
        }
        */
        //Sequence s1 = p.buildSequence(music);
        
        //p.play(s1);
        //p.save(s1,"x");
        System.exit(0);
    }
    
    public static void serializ(Object o, String name) throws IOException {
        FileOutputStream fos = new FileOutputStream(name);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(o);
        oos.flush();
        oos.close();
    }
    
    
    
    public static void p_p(List<List<Double>> means, List<String> legs, String name) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("D:\\Dropbox\\diploma\\" + name + ".txt");
        
        out.println(name + "_legend = " + Arrays.toString(legs.toArray()) + ";");
        for (int i = 0; i < means.size(); i++) {
            out.println(name + "_y("+(i+1)+",:) = " + Arrays.toString(means.get(i).toArray()) + ";");
        }
        
        out.close();
    }
    
    public static void pp(Map.Entry<String, int[]> entry, List<List<List<Double>>> means, List<String> legs) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("D:\\Dropbox\\diploma\\" + entry.getKey() + ".txt");
        
        out.println(entry.getKey() + "_legend = " + Arrays.toString(legs.toArray()) + ";");
        out.println(entry.getKey() + "_x = " + Arrays.toString(entry.getValue()) + ";");
        for (int i = 0; i < means.size(); i++) {
            for (int j = 0; j < means.get(i).size(); j++) {
                out.println(entry.getKey() + "_y("+(i+1)+","+(j+1)+",:) = " + Arrays.toString(means.get(i).get(j).toArray()) + ";");
            }
        }
        out.close();
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
    
    public static List<Double> s_s(Harmonizer h, List<Playble> melody, int N) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Set<Future<Double>> set = new HashSet<Future<Double>>();
        for (int i = 0; i < N; i++) {
            set.add(pool.submit(new HarmonizeCallable(h, melody)));
        }

        List<Double> mean = new ArrayList<>();
        for (Future<Double> future : set) {
            mean.add(future.get());
        }

        return mean;
    }
     
    public static Harmonizer setH(Harmonizer h, int poolSize, int mut, int mount, int size, SelectOperator sOp) {
        h.setPool(6);
        SimpleAllelMutation mOp = new SimpleAllelMutation();
        mOp.setMut(mut);
        h.setMutationOperator(mOp);
        MixupOperator cOp = new MixupOperator();
        cOp.setAmount(mount);
        cOp.setSize(size);
        h.setCrossOperator(cOp);
        
        
        h.setSelectOperator(sOp);
        
        return h;
     }
    
    public static List<List<Double>> sss(Harmonizer h, List<Playble> melody, SelectOperator sOp, String type, int[] values, int N) throws InterruptedException, ExecutionException {
        
        /* eult */
        h = setH(h, 6, 5, 2, 2, sOp);
        //h.setSelectOperator(sOp);
        
        List<List<Double>> means = new ArrayList<>();
        
        for (int v : values) {
            //upte
            if (type.equals("poolSize")) {
                h.setPool(v);
            }
            else if (type.equals("mutation")) {
                setH(h, 6, v, 2, 2, sOp);
            }
            else if ( type.equals("amount")) {
                setH(h, 6, 5, v, 2, sOp);
            }
            else if (type.equals("size")) {
                setH(h, 6, 5, 2, v, sOp);
            }
            //clculte
            means.add(s_s(h, melody, N));
                    
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
}
