/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komposer.genetic;

import java.util.List;

/**
 *
 * @author kuro
 */
public class Genetic<T> {
    SelectOperator<T> selectOperator;
    CrossOperator<T> crossOperator;
    MutationOperator<T> mutationOperator;
    BreedOperator<T> breedOperator;
    FitnessFunction<T> ff;
    
    
    public void setFitnessFunction(FitnessFunction<T> f) {
        ff = f;
        if (selectOperator != null) {
            selectOperator.setFitnessFunction(ff);
        }
    }
    
    public FitnessFunction<T> getFitnessFunction() {
        return ff;
    }
    
    public void setSelectOperator(SelectOperator sOp) {
        selectOperator = sOp;
        if (ff != null) {
            selectOperator.setFitnessFunction(ff);
        }
    }
    
    public SelectOperator<T> getSelectOperator() {
        return selectOperator;
    }
    
    public void setCrossOperator(CrossOperator cOp) {
        crossOperator = cOp;
    }
    
    public CrossOperator<T> getCrossOperator() {
        return crossOperator;
    }
    
    public void setMutationOperator(MutationOperator mOp) {
        mutationOperator = mOp;
    }
    
    public MutationOperator<T> getMutationOperator() {
        return mutationOperator;
    }
    
    public void setBreedOperator(BreedOperator bOp) {
        breedOperator = bOp;
    }
    
    public BreedOperator<T> getBreedOperator() {
        return breedOperator;
    }
    
    public List<T> evolve(List<T> generation, int N) {
        int n = 0;
        for (; n <  N ; n++) {
            List<T> parents = selectOperator.select(generation, breedOperator.poolsize(generation));
            List<T> children = breedOperator.breed(crossOperator, parents);
            generation = mutationOperator.mutate(children);
        }
        
        return generation;
    }
}
