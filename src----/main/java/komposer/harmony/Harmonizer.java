package komposer.harmony;

import komposer.harmony.function.HarmonyRule;
import komposer.harmony.mutation.MutationOperator;
import komposer.harmony.select.SelectOperator;
import komposer.harmony.cross.CrossOperator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.kuro.komposer.common.Accord;
import com.kuro.komposer.common.Mode;
import com.kuro.komposer.common.Playble;
import static com.kuro.komposer.common.Utils.randomInt;

/**
 *
 * @author kuro
 */
public class Harmonizer {
    
    private Mode mode;
    private int pool = 8; //amount of parents to take TAKES MOST OF TIME
    int outsiders = 0;
    //int size = 2; //size of genes chain
    //int amount = 1; //amont of crossed genes in each cross
    //int iterations = 2000;//pool * 30;
    int max_iterations = 200;
    //float e = (float)0.3;
    //int mut = 5;
    HarmonyRule rule;
    SelectOperator selectOperator;
    CrossOperator crossOperator;
    MutationOperator mutationOperator;
        
    public Harmonizer(Mode mode) {
        this.mode = mode;
    }
    
    public void setPool(int p) {
        pool = p;
    }
    
    public void setMaxIterations(int mi) {
        max_iterations = mi;
    }
    
    public void setRule(HarmonyRule r) {
        rule = r;
        selectOperator.setRule(rule);
    }
    
    public void setSelectOperator(SelectOperator sOp) {
        selectOperator = sOp;
        selectOperator.setRule(rule);
    }
    
    public void setCrossOperator(CrossOperator cOp) {
        crossOperator = cOp;
    }
    
    public void setMutationOperator(MutationOperator mOp) {
        mutationOperator = mOp;
    }
    
    public SelectOperator getSelectOperator() {
        return selectOperator;
    }
    
    public CrossOperator getCrossOperator() {
        return crossOperator;
    }
    
    public MutationOperator getMutationOperator() {
        return mutationOperator;
    }
    
    
    
    public Double sharmonize(List<Playble> melody) {
        List<Chromosome> chs = buildAccordsM(melody);
        double sMIN = rule.check(chs.get(0).getAccords(mode));
        chs = harmonizeAccords(chs, max_iterations);
        double smin = rule.check(chs.get(0).getAccords(mode));
        return smin/sMIN;
    }
    
    public List<Playble> harmonize(List<Playble> melody, int variation) {
        
        List<Chromosome> chs = harmonizeAccords(buildAccordsM(melody), max_iterations);
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
    
    public List<Chromosome> buildAccordsM(List<Playble> melody) {
        
        return buildAccords(Playble.getMelodyPitches(melody));
    }
    
    public List<Chromosome> buildAccords(List<Integer> melody) {
        List<Chromosome> chs = new ArrayList<>();

        for(int i = 0; i < pool*pool; i++) {
            chs.add(new Chromosome(melody));
        }
        
        return chs;
    }
    
    public List<Chromosome> selectOut(SelectOperator op, List<Chromosome> p) {
        List<Chromosome> chsss = p.subList(p.size() - outsiders, p.size());
        chsss.addAll(op.select(p, pool));
        //unone
        return chsss;
        
    }
    
    public List<Chromosome> harmonizeAccords(List<Chromosome> generation, int N) {
        int n = 0;
        for (; n <  N ; n++) {
            
            List<Chromosome> parents = selectOperator.select(generation, pool);
        
            List<Chromosome> children = new ArrayList<>();
            
            for(int j = 0; j < pool - 1; j++) {
                for(int i = j + 1; i < pool; i++) {
                    children.addAll(crossOperator.cross(parents.get(i), parents.get(j)));
                }
            }
            
            children = mutationOperator.mutate(children);
            
            generation = children;
        }
        
        return generation;
    }
    
    
    //----------------------------
    
    public void printInfo(List<Chromosome> chs)  {
        Collections.sort(chs, rule);
        
        System.out.println("Error function from " + rule.check(chs.get(0).getAccords(mode)) 
                + " to " + rule.check(chs.get(chs.size()-1).getAccords(mode)));
    }
}
