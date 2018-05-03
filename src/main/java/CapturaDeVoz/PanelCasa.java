package CapturaDeVoz;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import Elementos.Objeto;

public class PanelCasa extends JPanel{
   private int CUADRARZONAS = 20;
	private String[] zonasDelHogar_;
	
	public PanelCasa(){}
	
   public PanelCasa(ArrayList<Objeto> zonas){
   	zonasDelHogar_ = new String[zonas.size()];
   	System.out.println(zonas.size());
		for(int i = 0 ; i < zonas.size() ; i++){
			zonasDelHogar_[i] = zonas.get(i).toString();
		}
   }
	
	public void setZonasDelHogar(ArrayList<Objeto> zonas){
		zonasDelHogar_ = new String[zonas.size()];
		for(int i = 0 ; i < zonas.size() ; i++){
			zonasDelHogar_[i] = zonas.get(i).toString();
		}
	}
	public String[] GetZonasDelHogar(){
		return zonasDelHogar_;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int auxCuadrarY = CUADRARZONAS;
		for(int i = 0 ; i < zonasDelHogar_.length ; i++ ){
			g.drawString(zonasDelHogar_[i], CUADRARZONAS,auxCuadrarY);
			auxCuadrarY+= CUADRARZONAS;
		}
	}

}
