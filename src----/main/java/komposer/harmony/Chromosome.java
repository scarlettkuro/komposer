/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komposer.harmony;

import java.util.ArrayList;
import java.util.List;
import com.kuro.komposer.common.Accord;
import com.kuro.komposer.common.Mode;
import static com.kuro.komposer.common.Utils.randomInt;

/**
 *
 * @author kuro
 */
public class Chromosome {
    
    private List<Gene> genes;
    
    public Chromosome(List<Integer> melody) {
        genes = new ArrayList<>();
        for (int melodyPitch : melody) {
            genes.add(new Gene(melodyPitch));
        }
    }
    
    public Chromosome(Chromosome ancestor) {
        genes = new ArrayList<>(ancestor.getGenes());
    }
    
    public Gene getGene(int i) {
        return genes.get(i);
    }
    
    public List<Gene> getGenes() {
        return genes;
    }
    
    public List<Accord> getAccords(Mode mode) {
        List<Accord> accords = new ArrayList<>();
        for(Gene g : genes) {
            accords.add(g.getAccord(mode));
        }
        
        return accords;
    }
    
    public void setGene(int i, Gene val) {
        genes.set(i, val);
    }
    
    
    
}
