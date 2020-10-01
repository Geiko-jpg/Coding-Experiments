package com.java.firstExperiment;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class MicTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("COMMENCE SOUND TEST...");
		
		try {
			AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
			
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
			if(!AudioSystem.isLineSupported(info)) {
				System.err.println("!error: Line not Supported");
			}
			
			final TargetDataLine targetLine = (TargetDataLine) AudioSystem.getLine(info);
			targetLine.open();
			
			System.out.println("Start Recording... [10 seconds]");
			targetLine.start();
		
			Thread thread = new Thread() {
				
				@Override
				public void run() {
					AudioInputStream audioStream = new AudioInputStream(targetLine);
					File audioFile = new File("recording.wav");
					
					try {
						AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, audioFile);	
					} catch(IOException e) {
						e.printStackTrace();
					}
					
					System.out.println("Audio Stopped Recording...");
				}
				
			};
			
			thread.start();
			Thread.sleep(10000); // 10 seconds
			targetLine.stop();
			targetLine.close();
			
			System.out.println("Ended Sound Test...");
			
		} catch(LineUnavailableException e) {
			e.printStackTrace();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
