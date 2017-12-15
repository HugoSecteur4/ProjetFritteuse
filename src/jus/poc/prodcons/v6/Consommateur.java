package jus.poc.prodcons.v6;

import jus.poc.prodcons.Acteur;
import jus.poc.prodcons.Aleatoire;
import jus.poc.prodcons.ControlException;
import jus.poc.prodcons.Observateur;
import jus.poc.prodcons._Consommateur;

public class Consommateur extends Acteur implements _Consommateur {

	private Aleatoire temps_consommation;
	protected int NbMessageConso = 0;
	private ProdCons Buff;
	private Observateur observateur;
	private MyObservateur MyObsCons;
	

	protected Consommateur(Observateur observateur, MyObservateur MyObs, ProdCons buffer, int moyenneTempsDeTraitement,
			int deviationTempsDeTraitement) throws ControlException {
		super(Acteur.typeConsommateur, observateur, moyenneTempsDeTraitement, deviationTempsDeTraitement);
		temps_consommation = new Aleatoire(moyenneTempsDeTraitement, deviationTempsDeTraitement);
		this.Buff = buffer;
		this.observateur=observateur;
		this.setMyObsCons(MyObs);
	}
	
	public Observateur getObservateur() {
		
		return this.observateur;
	}

	@Override
	public int nombreDeMessages() {
		return NbMessageConso;
	}

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
				this.MyObsCons.consommationMessage(this, m, tpscons);

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
				
				Thread.sleep(tpscons);
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

	public MyObservateur getMyObsCons() {
		return MyObsCons;
	}

	public void setMyObsCons(MyObservateur myObsCons) {
		MyObsCons = myObsCons;
	}


}
