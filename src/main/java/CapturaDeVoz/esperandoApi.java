package CapturaDeVoz;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class esperandoApi extends JDialog {

   private ImageIcon esperando;
	private JLabel contenedor;
	
	public esperandoApi(){
		this.setSize(500,500);
		esperando = new ImageIcon("esperando.gif");
		contenedor = new JLabel("Esperando datos API...", esperando , JLabel.CENTER);
		this.add(contenedor);
	}
}
