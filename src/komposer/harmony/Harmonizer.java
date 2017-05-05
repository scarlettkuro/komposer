package komposer.harmony;

import komposer.harmony.function.HarmonyRule;
import komposer.genetic.MutationOperator;
import komposer.genetic.SelectOperator;
import komposer.genetic.CrossOperator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import komposer.Accord;
import komposer.Mode;
import komposer.Playble;
import static komposer.Utils.randomInt;
import komposer.genetic.BreedOperator;
import komposer.genetic.Genetic;
import komposer.harmony.breed.EachToEachOperator;
import komposer.harmony.cross.MixupOperator;
import komposer.harmony.mutation.SimpleAllelMutation;

/**
 *
 * @author kuro
 */
public class Harmonizer {
    
    private Mode mode;
    private int pool = 8; //amount of parents to take TAKES MOST OF TIME
    int max_iterations = 200;
    SelectOperator selectOperator;
    MixupOperator crossOperator;
    SimpleAllelMutation mutationOperator;
    BreedOperator breedOperator;
    HarmonyRule ff;
    
    public void setRule(HarmonyRule p) {
        ff = p;
    }
        
    public Harmonizer(Mode mode) {
        this.mode = mode;
    }
    
    public void setPool(int p) {
        pool = p;
    }
    
    public void setMaxIterations(int mi) {
        max_iterations = mi;
    }
    
    public void setH(int poolSize, int mut, int mount, int size, SelectOperator sOp) {
        pool = poolSize;
        mutationOperator = new SimpleAllelMutation();
        mutationOperator.setMut(mut);
        crossOperator = new MixupOperator();
        crossOperator.setAmount(mount);
        crossOperator.setSize(size);
        selectOperator = sOp;
        selectOperator.setFitnessFunction(ff);
        breedOperator = new EachToEachOperator();
     }
    
    
    public Double sharmonize(List<Playble> melody) {
        List<HarmonyChromosome> chs = buildAccordsM(melody);
       // HarmonyRule ff = new HarmonyRule();
        double sMIN = ff.check(chs.get(0).getAccords(mode));
        chs = harmonizeAccords(chs, max_iterations);
        double smin = ff.check(chs.get(0).getAccords(mode));
        return smin/sMIN;
    }
    
    
    public Double fharmonize(List<Playble> melody) {
        List<HarmonyChromosome> chs = buildAccordsM(melody);
       // HarmonyRule ff = new HarmonyRule();
        //double sMIN = ff.check(chs.get(0).getAccords(mode));
        chs = harmonizeAccords(chs, max_iterations);
        return (double)ff.check(chs.get(0).getAccords(mode));
    }
    
    public List<Playble> harmonize(List<Playble> melody, int variation) {
        
        List<HarmonyChromosome> chs = harmonizeAccords(buildAccordsM(melody), max_iterations);
        List<Accord> acs = chs.get(variation).getAccords(mode);
        List<Playble> msc = new ArrayList<>();
        for(Accord  a: acs) {
            msc.add(new Playble(a.getPitches()));
        }
        return msc;
    }
    
    public List<Playble> harmonize(List<Playble> melody) {
        return harmonize(melody, 0);
    }
    
    public List<HarmonyChromosome> buildAccordsM(List<Playble> melody) {
        return buildAccords(Playble.getMelodyPitches(melody));
    }
    
    public List<HarmonyChromosome> buildAccords(List<Integer> melody) {
        List<HarmonyChromosome> chs = new ArrayList<>();

        for(int i = 0; i < pool*pool; i++) {
            chs.add(new HarmonyChromosome(melody));
        }
        
        return chs;
    }
    
    public List<HarmonyChromosome> harmonizeAccords(List<HarmonyChromosome> generation, int N) {
        Genetic<HarmonyChromosome> genetic = new Genetic();
        genetic.setBreedOperator(breedOperator);
        genetic.setCrossOperator(crossOperator);
        genetic.setMutationOperator(mutationOperator);
        genetic.setSelectOperator(selectOperator);
        
        return genetic.evolve(generation, N);
    }
}
