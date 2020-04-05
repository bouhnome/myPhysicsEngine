/**
 * La fenêtre pour faire des dessins
 * 
 */

// Chargement des bibliothèques Swing et AWT
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList; 
public class FenetrePlotCourbe extends JFrame implements ActionListener{
	
	// Les Widgets à déclarer en dehors du constructeur
	private LinkedList<Particle> mesParticules;
    private Timer monChrono;
    private int temps;
    private static int DELTA_T = 40;
    public double dt = ((double)DELTA_T)/100;
    public double limite_sol=0.80;
    public int niter =0;
	
	/**
	 * Le constructeur qui rend la fenêtre non visible à sa création
	 */	
	public FenetrePlotCourbe(LinkedList<Particle> mesParticules){
		this.mesParticules = mesParticules;
		this.setTitle("IHM Courbe - Graphisme ");
		this.setLayout(null);
		this.setSize(1400,800);
		this.setLocation(700,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Pour empêcher le redimensionnement de la fenêtre
		this.setResizable(true);
		// Pour cacher la fenêtre à sa création
		this.setVisible(true);
        
        // Création du chronomètre
        monChrono = new Timer(DELTA_T,this);
        // Initialisation de la variable temps
        temps = 0;
        
		
	}
	

	
	
	/**
	 * Pour faire des dessins simples
	 * @param l'objet graphics
	 */ 
	public void paint(Graphics g){
		//On remplit le background
		g.setColor(Color.blue);
		g.fillRect(0,0,this.getWidth(),(int)(limite_sol*this.getHeight()));
		g.setColor(Color.cyan);
		g.fillRect(0,(int)(limite_sol*this.getHeight()),this.getWidth(),(int)((1-limite_sol)*this.getHeight()));

		if (!mesParticules.isEmpty())
			for (Particle a : mesParticules){
				g.setColor(a.myColor);
				a.dessine(g);
			}

	}
	
	
	/**
	 * Méthode exécutée à chaque réveil du Timer
	 */
	public void actionPerformed(ActionEvent e){
        temps+=DELTA_T;
        niter++;

		this.setTitle("IHM Courbe - Graphisme / temps : "+temps);

		test.simulate(dt,niter);

		repaint();		
	}
    
    /**
     * Méthode exécutée lorsque l'utilisateur a appuyé sur le bouton "Afficher"
     * Elle lance simplement le Timer et donc la méthode actionPerformed
     */ 
    public void lancement(){
        monChrono.start();
    }


	
	
}
		

		
		

		
		
		
