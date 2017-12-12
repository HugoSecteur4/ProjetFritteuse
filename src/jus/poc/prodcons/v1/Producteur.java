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
	private ProdCons Buff;
	private Aleatoire TempsProduction;
	private int nombreDeMess;
	private int NbMessageAProduire;
	
	protected Producteur(Observateur observateur,ProdCons Buffer, int moyenneTempsDeTraitement,
			int deviationTempsDeTraitement, int nombreMoyenDeProduction, int deviationNombreMoyenDeProduction) throws ControlException {
		super(Acteur.typeProducteur, observateur, moyenneTempsDeTraitement, deviationTempsDeTraitement);
//		this.ID_Producteur = nb_Prod;
//		nb_Prod ++;
		this.Buff=Buffer;
		NbMessageAProduire = Aleatoire.valeur(nombreMoyenDeProduction,deviationNombreMoyenDeProduction);
		TempsProduction= new Aleatoire(moyenneTempsDeTraitement, deviationTempsDeTraitement);
		nombreDeMess=0;
		System.out.println("Producteur "+identification()+" vient d'etre crée");
	}
	

	@Override
	public int nombreDeMessages() {
		// TODO Auto-generated method stub
		return nombreDeMess;
	}
	
	public void run()
	{	
	
		for(int i=0;i<NbMessageAProduire;i++){
			try {
				Thread.sleep(TempsProduction.next());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				this.Buff.put(this, new MessageX("test"+identification(), this) );
				nombreDeMess ++;
				this.Buff.toString();


			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
		System.out.println(toString()+"a tout fini");
	
	}
	
	public String toString(){
		return "Producteur " + identification();

	}
	
	
}

