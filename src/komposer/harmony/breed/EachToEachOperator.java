/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komposer.harmony.breed;

import java.util.ArrayList;
import java.util.List;
import komposer.genetic.BreedOperator;
import komposer.genetic.CrossOperator;
import komposer.harmony.HarmonyChromosome;

/**
 *
 * @author kuro
 */
public class EachToEachOperator implements BreedOperator {

    @Override
    public List<HarmonyChromosome> breed(CrossOperator crossOperator, List<HarmonyChromosome> parents) {
        List<HarmonyChromosome> children = new ArrayList<>();
        int pool = parents.size();
        for(int j = 0; j < pool - 1; j++) {
            for(int i = j + 1; i < pool; i++) {
                children.addAll(crossOperator.cross(parents.get(i), parents.get(j)));
            }
        }
        return children;
    }
    
}
