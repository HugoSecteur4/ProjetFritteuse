package jus.poc.prodcons.v1;

import jus.poc.prodcons.Acteur;
import jus.poc.prodcons.Aleatoire;
import jus.poc.prodcons.ControlException;
import jus.poc.prodcons.Observateur;
import jus.poc.prodcons._Consommateur;

public class Consommateur extends Acteur implements _Consommateur {
	
	
	private Aleatoire alea;
	
	
	protected Consommateur(int type, Observateur observateur, int moyenneTempsDeTraitement,
			int deviationTempsDeTraitement) throws ControlException {
		super(type, observateur, moyenneTempsDeTraitement, deviationTempsDeTraitement);
		alea = new Aleatoire(moyenneTempsDeTraitement, deviationTempsDeTraitement);
	}

	@Override
	public int nombreDeMessages() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void run()
	{
		
	}
	
	public String toString(){
		return "Je suis le consommateur" + identification();
	}
}
