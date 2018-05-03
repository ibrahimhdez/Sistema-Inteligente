package Main;

public class Main {
	private static final String USERNAME = "pi";
    private static final String HOST = "172.20.10.5";
    private static final int PORT = 22;
    private static final String PASSWORD = "sistemas";
    
	static public void main(String args[]) throws Exception {
		Controlador controlador = new Controlador();
		
		controlador.init();
		controlador.conectarSSH(USERNAME, PASSWORD, HOST, PORT);
		//controlador.desconectarSSH(); 
	}
}
