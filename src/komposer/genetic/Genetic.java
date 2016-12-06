/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komposer.genetic;

import komposer.genetic.BreedOperator;
import java.util.ArrayList;
import java.util.List;
import komposer.harmony.HarmonyChromosome;

/**
 *
 * @author kuro
 */
public class Genetic {
    SelectOperator selectOperator;
    CrossOperator crossOperator;
    MutationOperator mutationOperator;
    BreedOperator breedOperator;
    
    public void setSelectOperator(SelectOperator sOp) {
        selectOperator = sOp;
        //selectOperator.setRule(rule);
    }
    
    public void setCrossOperator(CrossOperator cOp) {
        crossOperator = cOp;
    }
    
    public void setMutationOperator(MutationOperator mOp) {
        mutationOperator = mOp;
    }
    
    public void setBreedOperator(BreedOperator bOp) {
        breedOperator = bOp;
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
    
    public BreedOperator getBreedOperator() {
        return breedOperator;
    }
    
    public List<Chromosome> evolve(List<Chromosome> generation, int N) {
        int n = 0;
        for (; n <  N ; n++) {
            
            List<Chromosome> parents = selectOperator.select(generation, pool);
        
            List<Chromosome> children = breedOperator.breed(crossOperator, parents);
            
            children = mutationOperator.mutate(children);
            
            generation = children;
        }
        
        return generation;
    }
}
