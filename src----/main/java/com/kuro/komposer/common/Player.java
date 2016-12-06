/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kuro.komposer.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

/**
 *
 * @author kuro
 */
public class Player {
    
    public void setInstrument(Track t, int i) throws InvalidMidiDataException {
        ShortMessage instrumentChange = new ShortMessage();
             instrumentChange.setMessage(ShortMessage.PROGRAM_CHANGE, 0, i,0);
             //MidiEvent instrumentChange = new MidiEvent(ShortMessage.PROGRAM_CHANGE,drumPatch.getBank(),drumPatch.getProgram());
             t.add(new MidiEvent(instrumentChange,0));
    }
    
    public Sequence buildSequence(List<Playble> music) {
        Sequence s = null;
    
        try {
            s = new Sequence(Sequence.PPQ, 4);
            List<Track> trs = new ArrayList<>();
            
            trs.add(s.createTrack());
           /* trs.add(s.createTrack());
            trs.add(s.createTrack());
            trs.add(s.createTrack());
            */
            //trs.add(s.createTrack());
            
            
            //setInstrument(trs.get(0), Utils.randomInt(8));
            /*setInstrument(trs.get(1), 40 + Utils.randomInt(8));
            setInstrument(trs.get(2), 25 + Utils.randomInt(8));
            setInstrument(trs.get(2), 33 + Utils.randomInt(8));
            */
            //setInstrument(trs.get(3), 120 + Utils.randomInt(6));
            
            
            
            //trs.add(s.createTrack());
            
            setInstrument(trs.get(0), 0);
            /*setInstrument(trs.get(0), 3);
            setInstrument(trs.get(0), 5);
            setInstrument(trs.get(0), 7);*/
            int time = 0;
            for (Playble p : music) {
                    
                for (int i = 0; i < p.getPitches().size(); i++) {
                    int pitch = p.getPitches().get(i);
                    Track t = trs.get(i % trs.size());
                    t.add(
                        new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 0, pitch + 48,60 + 25*p.getVol()), time)
                    );
                    t.add(
                        new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, 0, pitch + 48,50 + 25*p.getVol()), time + p.getLng())
                    );
                }
                time += p.getLng();
            }
        } catch (Exception ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
            return s;
            
    }
    
    
    public void save(Sequence s, String name) {
       
            for (int t : MidiSystem.getMidiFileTypes()) {
                try {
                MidiSystem.write(s, t, new File(name + " - " + t + ".mid")  );
        } catch (Exception e) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, e);
        }
            }
    }
    
    
    public void play(Sequence s) {
        
            // Obtains the default Sequencer connected to a default device.
		Sequencer sequencer;
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            
            sequencer.setSequence(s);
            sequencer.start();
           /* sequencer.addControllerEventListener(new ControllerEventListener() {
                @Override
                public void controlChange(ShortMessage event) {
                    //if (event.getStatus() == ShortMessage.STOP) {
                        System.out.println("suka");
                    //}
                    
                }
            }, new int[]{0,1,2,3,4,5,6});
*/
	    } catch (Exception ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void play(List<Accord> music) {
               try {
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            MidiChannel[] channels = synth.getChannels();
            channels[0].programChange(30);
            channels[1].programChange(45);
            channels[2].programChange(60);
            channels[3].programChange(75);
            for (Accord accord : music) {
                play(accord.getPitches(), 2, channels);
            }
            synth.close();
       }  catch (Exception e) {
            e.printStackTrace();
       } 
    }
    /*
    public void play(List<List> music) {
               try {
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            MidiChannel[] channels = synth.getChannels();
            channels[0].programChange(30);
            channels[1].programChange(45);
            channels[2].programChange(60);
            channels[3].programChange(75);
            for (List<Integer> pitches : music) {
                play(pitches.subList(0, pitches.size()-1), pitches.get(pitches.size()-1), channels);
            }
            synth.close();
       }  catch (Exception e) {
            e.printStackTrace();
       } 
    }
    */
    public void play(List<Integer> pitches, int lng, MidiChannel channel) throws InterruptedException {
            for (Integer pitch : pitches) {
                 channel.noteOn(48 + pitch, 50);
            }
            Thread.sleep(1000/lng); // in milliseconds
            for (Integer pitch : pitches) {
                 channel.noteOff(48 + pitch);
            }
    }
    
    public void play(List<Integer> pitches, int lng, MidiChannel channels[]) throws InterruptedException {
        for(int i = 0; i < pitches.size(); i++) {
            channels[i].noteOn(48 + pitches.get(i), 50 + 5*i);
        }
            Thread.sleep(1000/lng); // in milliseconds
        for(int i = 0; i < pitches.size(); i++) {
            channels[i].noteOff(48 + pitches.get(i));
        }
    }
    
}
