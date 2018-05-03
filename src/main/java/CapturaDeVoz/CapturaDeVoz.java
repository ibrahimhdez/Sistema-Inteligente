package CapturaDeVoz;
 
 import java.awt.BorderLayout;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;
 
 import javax.swing.JFrame;

 import Main.Controlador;
 
 public class CapturaDeVoz extends JFrame {
 	private static final long serialVersionUID = 1L;
 	private Controlador controlador;
 	private static Microfono micro_; // clase con la que capturaremos la voz y guardar la voz
 	private static Grabador boton_;
 	private static PanelCasa panel_;
 	private static esperandoApi esperar_;
 	public CapturaDeVoz(){
 		setSize(400, 400);
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		micro_ = new Microfono();
 		setLayoutAndElements(); 
 		setBotonListener();
 		setVisible(true);
 	}
 	
	public void setLayoutAndElements(){
		setLayout(new BorderLayout());
 		boton_ = new Grabador();
 		panel_ = new PanelCasa();
 		esperar_ = new esperandoApi();
 		add(panel_ ,BorderLayout.CENTER);
		add(boton_, BorderLayout.AFTER_LAST_LINE);
 	}
 	public void setBotonListener(){
 		boton_.addMouseListener(new MouseListener() {
 			public void mouseReleased(MouseEvent e) {
 				// TODO Auto-generated method stub
 				micro_.parar();
 				boton_.changeMostrar();
 				repaint();
 				try {
 					esperar_.setVisible(true);
 					getControlador().procesarGrabacion();
					esperar_.setVisible(false);
					panel_.repaint();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
 			}
 			
 			public void mousePressed(MouseEvent e) {
 				micro_.Capturar();
 				boton_.changeMostrar();
 				repaint();
 			}
 			
 			public void mouseExited(MouseEvent e) {
 				// TODO Auto-generated method stub
 				
 			}
 			
 			public void mouseEntered(MouseEvent e) {
 
 				
 			}
 			
 			public void mouseClicked(MouseEvent e) {
 		
 			}
 		});
 		
 	}
 
 	/**
 	 * @return the micro_
 	 */
 	public static Microfono getMicro_() {
 		return micro_;
 	}
 
 	/**
 	 * @param micro_ the micro_ to set
 	 */
 	public static void setMicro_(Microfono micro_) {
 		CapturaDeVoz.micro_ = micro_;
 	}
 
 	/**
 	 * @return the boton_
 	 */
 	public static Grabador getBoton_() {
 		return boton_;
 	}
 
 	/**
 	 * @param boton_ the boton_ to set
 	 */
 	public static void setBoton_(Grabador boton_) {
 		CapturaDeVoz.boton_ = boton_;
 	}
 
 	/**
 	 * @return the panel_
 	 */
 	public static PanelCasa getPanel_() {
 		return panel_;
 	}
 
 	/**
 	 * @param panel_ the panel_ to set
 	 */
 	public static void setPanel_(PanelCasa panel_) {
 		CapturaDeVoz.panel_ = panel_;
 	}

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
 
 } 