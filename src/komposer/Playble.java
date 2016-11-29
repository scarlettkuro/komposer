package komposer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kuro
 */
public class Playble {
    List<Integer> pitches;
    int lng;
    int vol;
    
    public Playble(int pitch) {
        pitches = new ArrayList<>();
        pitches.add(pitch);
        lng = makeLng();
        vol = makeVol();
    }
    
    public Playble(List<Integer> pitches) {
        this.pitches = pitches;
        lng = makeLng();
        vol = makeVol();
    }

    public List<Integer> getPitches() {
        return pitches;
    }

    public int getMelodyPitch() {
        return pitches.get(0);
    }

    public int getLng() {
        return lng;
    }

    public int getVol() {
        return vol;
    }

    private int makeLng() {
        return (int)Math.pow(2, 1+Utils.randomInt(2));
    }

    public static List<Integer> getMelodyPitches(List<Playble> p) {
        List<Integer> mp = new ArrayList<>();
        for(Playble playble : p) {
            mp.add(playble.getMelodyPitch());
        }
        return mp;
    }

    private int makeVol() {
        return Utils.randomInt(2);
    }
}
