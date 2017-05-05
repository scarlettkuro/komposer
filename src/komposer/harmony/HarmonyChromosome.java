/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komposer.harmony;

import java.util.ArrayList;
import java.util.List;
import komposer.Accord;
import komposer.Mode;
import static komposer.Utils.randomInt;

/**
 *
 * @author kuro
 */
public class HarmonyChromosome {
    
    private List<Gene> genes;
    
    public HarmonyChromosome(List<Integer> melody) {
        genes = new ArrayList<>();
        for (int melodyPitch : melody) {
            genes.add(new Gene(melodyPitch));
        }
    }
    
    public HarmonyChromosome(HarmonyChromosome ancestor) {
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
