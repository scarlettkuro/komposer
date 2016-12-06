/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komposer;

import org.jfugue.player.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.sound.midi.InvalidMidiDataException;
import static komposer.Komposer.setH;
import komposer.harmony.Harmonizer;
import komposer.harmony.function.HarmonyRule;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.theory.ChordProgression;
import org.staccato.ReplacementMapPreprocessor;
import komposer.harmony.select.EliteOperator;

/**
 *
 * @author kuro
 */

//org.jfugue.midi.MidiDictionary.INSTRUMENT_BYTE_TO_STRING
public class HelloWorld {
    public void main(String[] args) throws IOException, InvalidMidiDataException {
       // Player player = new Player();
       // Pattern pattern = MidiFileManager.loadPatternFromMidi(new File("D:\\Dropbox\\diploma\\Неназвана тека\\music (інша копія).mid"));
       // System.out.println(pattern);
//        player.play(new ChordProgression(pattern.toString()));

komposer.Player p = new komposer.Player();
        Mode mode = new Mode(Mode.naturalMajor,0);     
        Melodizer me = new Melodizer(mode);
        
         Harmonizer h = new Harmonizer(mode);
        EliteOperator eo = new EliteOperator();
        h = setH(h, 12, 35, 1, 3, eo);
        
        h.setMaxIterations(200);
        
        HarmonyRule rule = new HarmonyRule();
        rule.setMode(mode);
        h.setRule(rule);

        /*
        List<Collection<Playble>> objs = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            objs.add(h.harmonize(me.makePeriod(4, 4, 2)));
            
        }
        */
        List<String> objs = new ArrayList<>();
        objs.add("X");
        objs.add("e");
        objs.add("n");
        objs.add("i");
        objs.add("a");
        objs.add("l");
        objs.add("s");
        Xen x = new Xen();
        Map<List, Double> m = x.normProbs(objs);
        
        for (int i = 4; i > 0; i--) {
            m = x.normProbs(m,i,3);
        }
        
        pp(m);
        
        //p.play(p.buildSequence((List<Playble>)cnc(m.keySet().iterator().next())));
    }
    public static void pp(Map<List, Double> m)  {
        for (Map.Entry<List, Double> e : m.entrySet()) {
            System.out.print(Arrays.asList(
                e.getKey()
            ));
        }
        System.out.println("");
    }
    public static List cnc(List<List> o)  {
        List n = new ArrayList();
        for (List oo : o) {
            n.addAll(oo);
        }
        
        return n;
    }
}
