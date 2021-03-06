package komposer.harmony.mutation;

import komposer.genetic.MutationOperator;
import java.util.List;
import komposer.harmony.HarmonyChromosome;
import komposer.harmony.Gene;
import static komposer.Utils.randomInt;

/**
 *
 * @author kuro
 */
public class SimpleAllelMutation implements MutationOperator<HarmonyChromosome> {
    
    int mut = 10;
    
    public void setMut(int m) {
       mut = m;
    }

    @Override
    public List<HarmonyChromosome> mutate(List<HarmonyChromosome> pool) {
        for (HarmonyChromosome chromosome : pool) {
            
            if (randomInt(100) < mut) {
                int gene_pos = randomInt(chromosome.getGenes().size());
                Gene gene2 = new Gene(chromosome.getGene(gene_pos));
                int allel_pos = randomInt(chromosome.getGene(0).getAllels().size());
                gene2.setAllel(allel_pos, randomInt(Gene.s.length));
                chromosome.setGene(gene_pos, gene2);
            }
        }
        
        return pool;
    }
    
}
