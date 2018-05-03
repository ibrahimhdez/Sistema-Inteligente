package CapturaDeVoz;


import java.io.File;
import javax.sound.sampled.*;


public class Microfono {
	private AudioFileFormat.Type aFF_T = AudioFileFormat.Type.WAVE;
	private AudioFormat aF = new AudioFormat(8000.0F, 16, 1, true, false);
	private TargetDataLine tD;
	private File f = new File("Grabacion.wav");
	
	public Microfono(){}
	
	
	public void Capturar(){
		try {
			DataLine.Info dLI = new DataLine.Info(TargetDataLine.class,aF);
			tD = (TargetDataLine)AudioSystem.getLine(dLI);
			new CapThread().start();

			}catch (Exception e) {}
	}
	private class CapThread extends Thread {
		public void run() {
			try {
					tD.open(aF);
					tD.start();
					AudioSystem.write(new AudioInputStream(tD), aFF_T, f);
			}catch (Exception e){}
		}
	}
	public void parar(){
		try{
			
			tD.close();
		}
		catch(Exception e){}
			
	}
}

