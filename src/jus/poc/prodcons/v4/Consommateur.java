package jus.poc.prodcons.v4;

import jus.poc.prodcons.Acteur;
import jus.poc.prodcons.Aleatoire;
import jus.poc.prodcons.ControlException;
import jus.poc.prodcons.Observateur;
import jus.poc.prodcons._Consommateur;
import jus.poc.prodcons.v4.ProdCons;

public class Consommateur extends Acteur implements _Consommateur {

	private Aleatoire temps_consommation;
	protected int NbMessageConso = 0;
	private ProdCons Buff;
	private Observateur observateur;
	
/**
 * 
 * @param observateur
 * @param buffer
 * @param moyenneTempsDeTraitement
 * @param deviationTempsDeTraitement
 * @throws ControlException
 */
	protected Consommateur(Observateur observateur, ProdCons buffer, int moyenneTempsDeTraitement,
			int deviationTempsDeTraitement) throws ControlException {
		super(Acteur.typeConsommateur, observateur, moyenneTempsDeTraitement, deviationTempsDeTraitement);
		temps_consommation = new Aleatoire(moyenneTempsDeTraitement, deviationTempsDeTraitement);
		this.Buff = buffer;
		this.observateur=observateur;
	}
	
	/**
	 * 
	 * @return Retourne l'observateur du Consommateur.
	 */
	public Observateur getObservateur() {
		
		return this.observateur;
	}

	@Override
	/** 
	 * @return Retourne le nombre de message consommé par le Consommateur.
	 */
	public int nombreDeMessages() {
		return NbMessageConso;
	}

	/**
	 * <p> Methode run du Consommateur. </p>
	 */
	public void run() {
		int nummessage = 0;
		MessageX m;
		int tpscons =0;
		while (!Buff.production_terminee() || Buff.enAttente() != 0) {
			tpscons = temps_consommation.next();
			
			try {
				if (Math.random()<=0.5) {
					yield();
					System.out.println("COMMUTATION");
				}
				
				
				m = (MessageX) Buff.get(this);
				observateur.consommationMessage(this, m, tpscons);

				nummessage = m.getNumero_message();
				
				
				if (Math.random()<=0.5) {
					yield();
					System.out.println("COMMUTATION");
				}
				
				
				
				System.out.println("***************************Traitement du message par le consommateur : " +this.identification() +", message : " + m.toString());
				System.out.println();
				NbMessageConso++;
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if (Math.random()<=0.5) {
					yield();
					System.out.println("COMMUTATION");
				}
				
				Thread.sleep(tpscons);
			} catch (InterruptedException e) {
				e.printStackTrace();
				
			}
			if (Math.random()<=0.5) {
				yield();
				System.out.println("COMMUTATION");
			}

			// Si on récupère un message :

			
		}
		if (Math.random()<=0.5) {
			yield();
			System.out.println("COMMUTATION");
		}
		System.out.println("consommation terminée, nbmessageconso : " + this.NbMessageConso + " message de fin : " + nummessage + " prod terminee : " + Buff.production_terminee()+" conso :"+this.identification());
	}

	public String toString() {
		return "Consommateur" + identification();
	}


}
