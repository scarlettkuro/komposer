package komposer.external;

import org.jfugue.pattern.Pattern;
import org.jfugue.pattern.Token;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by methyliel on 26.04.17.
 */

public class ConsonanceReader {
    protected List<List<Note>> voices;

    public ConsonanceReader() {
        voices = new ArrayList<List<Note>>();
    }

    public void setPattern(Pattern p) {
        List<Integer> voiceIndexes = new ArrayList<Integer>();
        List<Token> tokens = p.getTokens();
        for (Token t : tokens) {
            if (Token.TokenType.VOICE.equals(t.getType())) {
                voiceIndexes.add(p.toString().indexOf(t.toString()));
            }
        }

        for (int i = 0; i < (voiceIndexes.size()); i++) {
            if (9 != i) {
                Pattern blah;
                if (voiceIndexes.size() != i + 1) {
                    blah = new Pattern(p.toString().substring(voiceIndexes.get(i), voiceIndexes.get(i + 1)));
                    }
                else {
                    blah = new Pattern(p.toString().substring(voiceIndexes.get(i)));
                }
                List<Token> tokensFromOneVoice = blah.getTokens();
                List<Note> notesForOneVoice = new ArrayList<Note>();
                for (Token t : tokensFromOneVoice) {
                    if (Token.TokenType.NOTE.equals(t.getType())) {
                        notesForOneVoice.add(Note.makeNotesFromToken(t).get(0));
                    }
                }
                this.voices.add(new ArrayList<Note>(notesForOneVoice));
            }
        }
    }
    public List<Integer> nextConsonance() throws Exception {
        if (null == this.voices) {
            throw new Exception("Call setPattern() before calling this method, fat fag.");
        }
        List<Integer> consonance = new ArrayList<Integer>();
        int minValue = this.voices.get(0).get(0).getValue();
        for (List<Note> voice : this.voices) {
            if (voice.get(0).getValue() < minValue) {
                minValue = voice.get(0).getValue();
            }
            consonance.add(voice.get(0).getPitch());
        }
        for (List<Note> voice : this.voices) {
            int newValue = voice.get(0).subtractValue(minValue);
            if (0 == newValue) {
                voice.remove(0);
            }
        }
        return consonance;
    }
    public List<List<Note>> getVoices() {
        return this.voices;
    }

}
