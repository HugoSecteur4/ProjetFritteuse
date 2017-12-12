package src.jus.poc.prodcons.v1;

import jus.poc.prodcons.Acteur;
import jus.poc.prodcons.Aleatoire;
import jus.poc.prodcons.ControlException;
import jus.poc.prodcons.Observateur;
import jus.poc.prodcons._Consommateur;
import jus.poc.prodcons.v1.ProdCons;
import jus.poc.prodcons.v1.TestProdCons;

public class Consommateur extends Acteur implements _Consommateur {
	
	
	private Aleatoire temps_traitement;
	protected int NbMessageConso = 0;
	ProdCons Buff;
	
	protected Consommateur(Observateur observateur, ProdCons Buffer, int moyenneTempsDeTraitement,
			int deviationTempsDeTraitement) throws ControlException {
		super(Acteur.typeConsommateur, observateur, moyenneTempsDeTraitement, deviationTempsDeTraitement);
		temps_traitement = new Aleatoire(moyenneTempsDeTraitement, deviationTempsDeTraitement);
		this.Buff = Buffer;
	}

	@Override
	public int nombreDeMessages() {
		// TODO Auto-generated method stub
		return NbMessageConso;
	}

	public void run()
	{
		for(int i=0; i< 5; i++){
		try {
			Thread.sleep(temps_traitement.next());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Si on récupère un message : 
		try {
			TestProdCons.buffer.get(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		NbMessageConso++;
		}
	}
	
	public String toString(){
		return "Consommateur" + identification();
	}
}
