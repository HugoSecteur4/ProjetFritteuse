package jus.poc.prodcons.v1;

import jus.poc.prodcons.Acteur;
import jus.poc.prodcons.Aleatoire;
import jus.poc.prodcons.ControlException;
import jus.poc.prodcons.Observateur;
import jus.poc.prodcons._Consommateur;

public class Consommateur extends Acteur implements _Consommateur {
	
//	protected int ID_Conso;
//	
//	protected int nb_Conso = 0;
	
	private Aleatoire alea;
	
	
	protected Consommateur(int type, Observateur observateur, int moyenneTempsDeTraitement,
			int deviationTempsDeTraitement) throws ControlException {
		super(type, observateur, moyenneTempsDeTraitement, deviationTempsDeTraitement);
//		this.ID_Conso = nb_Conso;
//		nb_Conso ++;
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
//		return "Je suis le consommateur" + ID_Producteur;
		return "Je suis le consommateur" + identification();
	}
}
