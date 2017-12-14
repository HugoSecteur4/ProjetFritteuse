package jus.poc.prodcons.v6;

import jus.poc.prodcons.ControlException;
import jus.poc.prodcons.Message;
import jus.poc.prodcons._Consommateur;
import jus.poc.prodcons._Producteur;

public class MyObservateur{
	
	public void init(int nbProducteurs, int nbConsommateurs, int nbBuffers) throws ControlException{
		
		if (nbProducteurs <= 0) {
			throw new ControlException(this.getClass(),"nbProd <= 0");
		}
		else if (nbConsommateurs <= 0) {
			throw new ControlException(this.getClass(),"nbCons <= 0");

		}
		else if (nbBuffers<=0) {
			throw new ControlException(this.getClass(),"taille buffer <= 0");
		} else {
			System.out.println("L'initialisation à réussie !");
		}
	}
	
	public void newProducteur(_Producteur P) throws ControlException{
		if (P==null) {
			throw new ControlException(this.getClass(),"Vous avez utilisé un Producteur qui avait pour valeur null. PAS BIEN");
		} else {
			System.out.println("Le producteur "+ P.identification() +" a bien été créé.");
		}
	}
	
	
	public void newConsommateur(_Consommateur C) throws ControlException{
		if (C==null) {
			throw new ControlException(this.getClass(),"Vous avez utilisé un Consommateur qui avait pour valeur null. PAS BIEN");
		} else {
			System.out.println("Le consommateur "+ C.identification() +" a bien été créé.");
		}
	}
	
	public void productionMessage(_Producteur P, Message M, int T) throws ControlException{
		if (P==null) {
			throw new ControlException(this.getClass(),"Vous avez utilisé un Producteur qui avait pour valeur null. PAS BIEN");
		} else if (M==null){
			throw new ControlException(this.getClass(),"Vous avez utilisé un Message qui avait pour valeur null. PAS BIEN");
		} else if (T<=0) {
			throw new ControlException(this.getClass(),"Le temps de traitement doit être > 0.");
		} else {
			System.out.println("Production du message réussie.");
		}
	}
	
	public void consommationMessage(_Consommateur C, Message M, int T) throws ControlException{
		if (C==null) {
			throw new ControlException(this.getClass(),"Vous avez utilisé un Consommateur qui avait pour valeur null. PAS BIEN");
		} else if (M==null){
			throw new ControlException(this.getClass(),"Vous avez utilisé un Message qui avait pour valeur null. PAS BIEN");
		} else if (T<=0) {
			throw new ControlException(this.getClass(),"Le temps de traitement doit être > 0.");
		} else {
			System.out.println("Production du message réussie.");
		}
	}
	
	public void depotMessage(_Producteur P, Message M) throws ControlException{
		if (P==null) {
			throw new ControlException(this.getClass(),"Vous avez utilisé un Producteur qui avait pour valeur null. PAS BIEN");
		} else if (M==null) {
			throw new ControlException(this.getClass(),"Vous avez utilisé un Message qui avait pour valeur null. PAS BIEN");
		} else {
			System.out.println("Le message à bien été déposé dans le buffer.");
		}
	}
	
	public void retraitMessage(_Consommateur C, Message M) throws ControlException{
		if (C==null) {
			throw new ControlException(this.getClass(),"Vous avez utilisé un Consommateur qui avait pour valeur null. PAS BIEN");
		} else if (M==null) {
			throw new ControlException(this.getClass(),"Vous avez utilisé un Message qui avait pour valeur null. PAS BIEN");
		} else {
			System.out.println("Le message à bien été retiré du buffer.");
		}
	}
	
	public void nbMsgProduits_eg_nbMsgConsumes(int nbproduits, int nbconsumes) throws ControlException{
		if (nbproduits < nbconsumes) {
			throw new ControlException(this.getClass(),"Vous avez consommés plus que vous avez produits -> PROBLEME ");
		} else if (nbproduits > nbconsumes) {
			throw new ControlException(this.getClass(),"Vous n'avez pas consommés tous les messages produits -> PROBLEME ");

		} else {
			System.out.println("L'ensemble des messages produits ont été consommés.");
		}
	}
}
