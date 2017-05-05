package komposer.external;

import org.jfugue.pattern.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by methyliel on 27.04.17.
 */
public class Note {
    private int pitch;
    private int value;
    public static final int PAUSE_PITCH = -1000;

    protected Note(Token t) {
        int value = this.findNoteValue(t);
        if (0 != value) {
            this.value = value;
        }
        int pitch = this.convertStaccato(t);
        if (999 != pitch) {
            this.pitch = pitch;
        }
    }
    public int getPitch() {
        return this.pitch;
    }
    public int getValue() {
        return this.value;
    }
    public int subtractValue(int subtractionValue) {
        int value = this.value - subtractionValue;
        this.value = value;
        return value;
    }
    public static List<Note> makeNotesFromToken(Token t) {
        List<Note> notes = new ArrayList<Note>();
        if (t.toString().contains("+")) {
            String str = t.toString();
            int index = 0, newIndex;
            boolean hasSymbol = str.substring(index).contains("+");
            while (hasSymbol) {
                newIndex = str.indexOf("+", index);
                notes.add(new Note(new Token(str.substring(index, newIndex), Token.TokenType.NOTE)));
                index = newIndex;
                hasSymbol = str.substring(index + 1).contains("+");
            }
            notes.add(new Note(new Token(str.substring(index + 1), Token.TokenType.NOTE)));
        }
        else {
            notes.add(new Note(t));
        }

        return notes;
    }
    private int findNoteValue(Token note){
        Map<String, Integer> lettersValue = new HashMap<String, Integer>();
        lettersValue.put("t", 1);
        lettersValue.put("s", 2);
        lettersValue.put("s.", 3);
        lettersValue.put("i", 4);
        lettersValue.put("i.", 6);
        lettersValue.put("q", 8);
        lettersValue.put("q.", 12);
        lettersValue.put("h", 16);
        lettersValue.put("h.", 24);
        lettersValue.put("w", 32);

        for (Map.Entry<String, Integer> entry : lettersValue.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if ((note.toString().toLowerCase()).contains(key) && !(note.toString().toLowerCase()).contains(key + ".")) {
                return value;
            }
        }
        return 0;
    }
    private int convertStaccato(Token note) {
        int noteInteger = 999;
        int[] octaveNumbers = new int[12];
        for (int i = 0; i < 12; i++) {
            octaveNumbers[i] = i - 1;
        }
        String noteLetter = "";
        int noteOctaveNumber = -2;
        //Find note and octave number
        if (note.toString().contains("R")) {
            noteLetter = "R";
            noteOctaveNumber = 3;
        }
        for (int i = 0; i < 12; i++) {
            if (note.toString().contains(String.valueOf(octaveNumbers[i]))) {
                noteOctaveNumber = octaveNumbers[i];
                noteLetter = note.toString().substring(0, note.toString().indexOf(String.valueOf(noteOctaveNumber)));
            }
        }
        if (!"".equals(noteLetter) && -2 != noteOctaveNumber) {
            Map<String, Integer> pitch = new HashMap<String, Integer>();
            pitch.put("C", 0); pitch.put("C#", 1); pitch.put("CB", -1);
            pitch.put("D", 2); pitch.put("D#", 3); pitch.put("DB", 1);
            pitch.put("E", 4); pitch.put("E#", 5); pitch.put("EB", 3);
            pitch.put("F", 5); pitch.put("F#", 6); pitch.put("FB", 4);
            pitch.put("G", 7); pitch.put("G#", 8); pitch.put("GB", 6);
            pitch.put("A", 9); pitch.put("A#", 10); pitch.put("AB", 8);
            pitch.put("B", 11); pitch.put("B#", 12); pitch.put("BB", 10);
            pitch.put("R", Note.PAUSE_PITCH);
            //define note
            for (Map.Entry<String, Integer> entry : pitch.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                if ((key.toUpperCase()).equals(noteLetter)) {
                    noteInteger = value;
                }
            }
            //correct note
            if (999 != noteInteger) {
                noteInteger += (noteOctaveNumber - 3) * 12;
            }
        }
        return noteInteger;
    }
}
