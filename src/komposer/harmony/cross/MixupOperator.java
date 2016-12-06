package komposer.harmony.cross;

import komposer.genetic.CrossOperator;
import java.util.ArrayList;
import java.util.List;
import komposer.harmony.HarmonyChromosome;
import komposer.harmony.Gene;
import static komposer.Utils.randomInt;

/**
 *
 * @author kuro
 */
public class MixupOperator implements CrossOperator {
    
    int amount = 2;
    int size = 2;
    
    public void setAmount(int a) {
        this.amount = a;
    }
    
    public void setSize(int s) {
        size = s;
    }

    @Override
    public List<HarmonyChromosome> cross(HarmonyChromosome ch1, HarmonyChromosome ch2) {
        
        List<HarmonyChromosome> chs = new ArrayList<>();
        
        HarmonyChromosome ch1_new = new HarmonyChromosome(ch1);
        HarmonyChromosome ch2_new = new HarmonyChromosome(ch2);
        
        for (int j = 0; j < amount; j++) {
            int genesCount = ch2.getGenes().size();
            int start_gene = randomInt(genesCount);
            int allel = randomInt(ch2.getGene(start_gene).getAllels().size());
            
            for (int i = 0; i < size; i++) {
                int gene = (start_gene + i) % genesCount;
                Gene gene1 = new Gene(ch1.getGene(gene));
                Gene gene2 = new Gene(ch2.getGene(gene));
                gene1.setAllel(allel, ch1.getGene(gene).getAllel(allel));
                gene2.setAllel(allel, ch2.getGene(gene).getAllel(allel));
                ch1_new.setGene(gene, gene1);
                ch2_new.setGene(gene, gene2);
            }
        }
        
        chs.add(ch1_new);
        chs.add(ch2_new);
        
        return chs;
    }
    
}
