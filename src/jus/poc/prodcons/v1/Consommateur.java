package jus.poc.prodcons.v1;

import jus.poc.prodcons.Acteur;
import jus.poc.prodcons.Aleatoire;
import jus.poc.prodcons.ControlException;
import jus.poc.prodcons.Message;
import jus.poc.prodcons.Observateur;
import jus.poc.prodcons._Consommateur;
import jus.poc.prodcons.v1.ProdCons;
import jus.poc.prodcons.v1.TestProdCons;

public class Consommateur extends Acteur implements _Consommateur {

	private Aleatoire temps_consommation;
	protected int NbMessageConso = 0;
	ProdCons Buff;

	protected Consommateur(Observateur observateur, ProdCons Buffer, int moyenneTempsDeTraitement,
			int deviationTempsDeTraitement) throws ControlException {
		super(Acteur.typeConsommateur, observateur, moyenneTempsDeTraitement, deviationTempsDeTraitement);
		temps_consommation = new Aleatoire(moyenneTempsDeTraitement, deviationTempsDeTraitement);
		this.Buff = Buffer;
	}

	@Override
	public int nombreDeMessages() {
		// TODO Auto-generated method stub
		return NbMessageConso;
	}

	public void run() {
		int nummessage = 0;
		MessageX m;
		while (!Buff.production_terminee() || Buff.enAttente() != 0) {
			
			try {
				if (Math.random()<=0.5) {
					yield();
					System.out.println("COMMUTATION");
				}
				m = (MessageX) Buff.get(this);
				nummessage = m.getNumero_message();
				if (Math.random()<=0.5) {
					yield();
					System.out.println("COMMUTATION");
				}
				System.out.println("***************************Traitement du message par le consommateur : " +this.identification() +", message : " + m.toString());
				System.out.println();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (Math.random()<=0.5) {
					yield();
					System.out.println("COMMUTATION");
				}
				Thread.sleep(temps_consommation.next());
			} catch (InterruptedException e) {
				e.printStackTrace();
				
			}
			if (Math.random()<=0.5) {
				yield();
				System.out.println("COMMUTATION");
			}
			// Si on récupère un message :

			NbMessageConso++;
		}
		if (Math.random()<=0.5) {
			yield();
			System.out.println("COMMUTATION");
		}
		//System.out.println("consommation terminée, nbmessageconso : " + this.NbMessageConso + " message de fin : " + nummessage + " prod terminee : " + Buff.production_terminee() + " en attente : " + Buff.enAttente());
	}

	public String toString() {
		return "Consommateur" + identification();
	}
}
