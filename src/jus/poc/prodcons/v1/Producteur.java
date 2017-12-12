package jus.poc.prodcons.v1;

import jus.poc.prodcons.Acteur;
import jus.poc.prodcons.Aleatoire;
import jus.poc.prodcons.ControlException;
import jus.poc.prodcons.Observateur;
import jus.poc.prodcons._Producteur;

public class Producteur extends Acteur implements _Producteur{

//	protected int ID_Producteur;
//	
//	protected int nb_Prod = 0;
	
	protected Producteur(Observateur observateur, int moyenneTempsDeTraitement,
			int deviationTempsDeTraitement) throws ControlException {
		super(Acteur.typeProducteur, observateur, moyenneTempsDeTraitement, deviationTempsDeTraitement);
//		this.ID_Producteur = nb_Prod;
//		nb_Prod ++;

	}
	

	@Override
	public int nombreDeMessages() {
		// TODO Auto-generated method stub
		return Aleatoire.valeur(3, 1);
	}
	
	public void run()
	{	int nbmess=this.nombreDeMessages();
		for(int i=0;i<nbmess;i++){
			try {
				Thread.sleep(Aleatoire.valeur(moyenneTempsDeTraitement, deviationTempsDeTraitement));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				TestProdCons.buffer.put(this, new MessageX("test"+identification(), this) );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	}
	
	public String toString(){
		return "Je suis le producteur" + identification();
	}
	
	
}

