package jus.poc.prodcons.v4;

import jus.poc.prodcons.Acteur;
import jus.poc.prodcons.Aleatoire;
import jus.poc.prodcons.ControlException;
import jus.poc.prodcons.Observateur;
import jus.poc.prodcons._Producteur;

public class Producteur extends Acteur implements _Producteur{

	private ProdCons Buff;
	private Aleatoire TempsProduction;
	private int nombreDeMess;
	private int NbMessageAProduire;
	private Observateur observateur;
	private int NbExemplaire;
	 
	/**
	 * 
	 * @param observateur
	 * @param Buffer
	 * @param moyenneTempsDeTraitement
	 * @param deviationTempsDeTraitement
	 * @param nombreMoyenDeProduction
	 * @param deviationNombreMoyenDeProduction
	 * @param NbExemplaire
	 * @throws ControlException
	 */
	protected Producteur(Observateur observateur,ProdCons Buffer, int moyenneTempsDeTraitement,
			int deviationTempsDeTraitement, int nombreMoyenDeProduction, int deviationNombreMoyenDeProduction,int NbExemplaire) throws ControlException {
		super(Acteur.typeProducteur, observateur, moyenneTempsDeTraitement, deviationTempsDeTraitement);
		this.Buff=Buffer;
		NbMessageAProduire = Aleatoire.valeur(nombreMoyenDeProduction,deviationNombreMoyenDeProduction);
		TempsProduction= new Aleatoire(moyenneTempsDeTraitement, deviationTempsDeTraitement);
		nombreDeMess=0;
		this.NbExemplaire=NbExemplaire;
		this.observateur=observateur;
		System.out.println("Producteur "+identification()+" vient d'etre crée");
	}
	
	/**
	 * 
	 * @return Retourne l'observateur du Producteur.
	 */
	public Observateur getObservateur (){
		return this.observateur;
	}

	@Override
	/**
	 * @return Retourne le nombre de message que doit produire le Producteur.
	 */
	public int nombreDeMessages() {
		return NbMessageAProduire;
	}
	/**
	 * <p> Methode run du Producteur. </p>
	 */
	public void run()
	{	
	int tpsprod=0;
		for(int i=0;i<NbMessageAProduire;i++){
			if (Math.random()<=0.5) {
				yield();
				System.out.println("COMMUTATION");
			}
			try {
				tpsprod=TempsProduction.next();
				Thread.sleep(tpsprod);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			try {
				if (Math.random()<=1) {
					yield();
					System.out.println("COMMUTATION");
				}
				MessageX m;
				synchronized(Buff) {
					m = new MessageX("Bonjour, je suis le producteur "+this.identification()+ " ceci est mon message n°"+this.nombreDeMess+1, this,this.NbExemplaire);
				}
				observateur.productionMessage(this, m, tpsprod);
				this.Buff.put(this,m);
				
				if (Math.random()<=0.5) {
					yield();
					System.out.println("COMMUTATION");
				}
				nombreDeMess ++;
				if (Math.random()<=0.5) {
					yield();
					System.out.println("COMMUTATION");
				}


			} catch (Exception e) {
				e.printStackTrace();
			}

			if (Math.random()<=0.5) {
				yield();
				System.out.println("COMMUTATION");
			}
		}
		if (Math.random()<=0.5) {
			yield();
			System.out.println("COMMUTATION");
		}
		this.Buff.incrNb_prodcts_terminated();
		if (Math.random()<=0.5) {
			yield();
			System.out.println("COMMUTATION");
		}
	
	}
	
	public String toString(){
		return "Producteur " + identification();

	}

	/**
	 * 
	 * @return Retourne le nombre d'exemplaire de message par le producteur.
	 */
	public int getNbExemplaire() {
		return this.NbExemplaire;
	}
	
	
}

