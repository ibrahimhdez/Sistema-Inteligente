package CapturaDeVoz;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class Grabador extends JButton{
	private static int RADIO = 10;
	private static String texto = "Presioname y Graba";
	private static boolean mostrar;
	public Grabador (){
		super(texto);
		mostrar = false;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(mostrar){
			g.setColor(Color.RED);
			g.drawString("Rec", getWidth() - RADIO * 6 , 20);
			g.fillOval(getWidth() - RADIO * 3 , 4 , RADIO * 2, RADIO * 2);
		}
	}
	
	public void changeMostrar(){
		
		if(mostrar){
			mostrar = false;
		}
		else{
			mostrar = true;
		}
		
	}
}
