package jus.poc.prodcons.v4;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Properties;

import jus.poc.prodcons.Aleatoire;
import jus.poc.prodcons.Observateur;
import jus.poc.prodcons.Simulateur;


public class TestProdCons extends Simulateur{

	private int nbProd = 0;
	private int nbCons = 0;
	private int nbBuffer = 0;
	private int tempsMoyenProduction = 0;
	private int deviationTempsMoyenProduction = 0;
	private int tempsMoyenConsommation = 0;
	private int deviationTempsMoyenConsommation = 0;
	private int nombreMoyenDeProduction = 0;
	private int deviationNombreMoyenDeProduction = 0;
	private int nombreMoyenNbExemplaire = 0;
	private int deviationNombreMoyenNbExemplaire = 0;
	/**
	 * 
	 * @return Retourne le nombre de Producteur.
	 */
	public int getNbProd() {
		return nbProd;
	}


	/**
	 * 
	 * @return Retourne le nombre de Consommateur.
	 */
	public int getNbCons() {
		return nbCons;
	}


	/**
	 * 
	 * @param nbCons
	 * <p> Setter du nombre de Consommateur. 
	 */
	public void setNbCons(int nbCons) {
		this.nbCons = nbCons;
	}


	/**
	 * 
	 * @return Retourne la taille du tampon de message.
	 */
	public int getNbBuffer() {
		return nbBuffer;
	}


	/**
	 * 
	 * @param nbBuffer
	 * <p> Setter de la taille du Buffer. </p>
	 */
	public void setNbBuffer(int nbBuffer) {
		this.nbBuffer = nbBuffer;
	}


	/**
	 * 
	 * @return Retourne le temps moyen de production d'un message.
	 */
	public int getTempsMoyenProduction() {
		return tempsMoyenProduction;
	}


	/**
	 * 
	 * @param tempsMoyenProduction
	 * <p> Setter du temps moyen de production. </p>
	 */
	public void setTempsMoyenProduction(int tempsMoyenProduction) {
		this.tempsMoyenProduction = tempsMoyenProduction;
	}



	/**
	 * 
	 * @return Retourne la deviation du temps moyen de production.
	 */
	public int getDeviationTempsMoyenProduction() {
		return deviationTempsMoyenProduction;
	}


	/**
	 * 
	 * @param deviationTempsMoyenProduction
	 * <p> Setter de deviation du temps moyen de production. </p>
	 */
	public void setDeviationTempsMoyenProduction(int deviationTempsMoyenProduction) {
		this.deviationTempsMoyenProduction = deviationTempsMoyenProduction;
	}


	/**
	 * 
	 * @return Retourne le temps moyen de consommation.
	 */
	public int getTempsMoyenConsommation() {
		return tempsMoyenConsommation;
	}


	/**
	 * 
	 * @param tempsMoyenConsommation
	 * <p> Setter du temps moyen de consommation. </p>
	 */
	public void setTempsMoyenConsommation(int tempsMoyenConsommation) {
		this.tempsMoyenConsommation = tempsMoyenConsommation;
	}


	/**
	 * 
	 * @return Retourne la deviation du temps moyen de consommation.
	 */
	public int getDeviationTempsMoyenConsommation() {
		return deviationTempsMoyenConsommation;
	}


	/**
	 * 
	 * @param deviationTempsMoyenConsommation
	 * <p> Setter de deviation du temps moyen de consommation.
	 */
	public void setDeviationTempsMoyenConsommation(int deviationTempsMoyenConsommation) {
		this.deviationTempsMoyenConsommation = deviationTempsMoyenConsommation;
	}


	/**
	 * 
	 * @return Retourne le nombre moyen de production.
	 */
	public int getNombreMoyenDeProduction() {
		return nombreMoyenDeProduction;
	}


	/** 
	 * 
	 * @param nombreMoyenDeProduction
	 * <p> Setter du nombre moyen de production.
	 */
	public void setNombreMoyenDeProduction(int nombreMoyenDeProduction) {
		this.nombreMoyenDeProduction = nombreMoyenDeProduction;
	}


	/**
	 * 
	 * @return Retourne la deviation du nombre moyen de production.
	 */
	public int getDeviationNombreMoyenDeProduction() {
		return deviationNombreMoyenDeProduction;
	}


	/**
	 * 
	 * @param deviationNombreMoyenDeProduction
	 * <p> Setter de deviation du nombre moyen de production.
	 */
	public void setDeviationNombreMoyenDeProduction(int deviationNombreMoyenDeProduction) {
		this.deviationNombreMoyenDeProduction = deviationNombreMoyenDeProduction;
	}


	/**
	 * 
	 * @return Retourne le nombre moyen d'exemplaire d'un message.
	 */
	public int getNombreMoyenNbExemplaire() {
		return nombreMoyenNbExemplaire;
	}


	/**
	 * 
	 * @param nombreMoyenNbExemplaire
	 * <p> Setter du nombre moyen d'exemplaire pour le message.
	 */
	public void setNombreMoyenNbExemplaire(int nombreMoyenNbExemplaire) {
		this.nombreMoyenNbExemplaire = nombreMoyenNbExemplaire;
	}


	/**
	 * 
	 * @return Retourne la deviation du nombre moyen d'exemplaire. 
	 */
	public int getDeviationNombreMoyenNbExemplaire() {
		return deviationNombreMoyenNbExemplaire;
	}


	/**
	 * 
	 * @param deviationNombreMoyenNbExemplaire
	 * <p> Setter de deviation du nombre moyen d'exemplaire.</p>
	 */
	public void setDeviationNombreMoyenNbExemplaire(int deviationNombreMoyenNbExemplaire) {
		this.deviationNombreMoyenNbExemplaire  = deviationNombreMoyenNbExemplaire;
	}


	/**
	 * 
	 * @param nbProd
	 * <p> Setter du nombre de Producteur.
	 */
	public void setNbProd(int nbProd) {
		this.nbProd = nbProd;
	}



	/**
	* Retreave the parameters of the application.
	* @param file the final name of the file containing the options.
	*/
	protected void init(String file) {
		System.out.println("initialisation");
		Properties properties = new Properties();
		
		try {
			
			properties.loadFromXML(getClass().getResourceAsStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		String key;
		int value;
		Class<?> thisOne = getClass();
		for(Map.Entry<Object,Object> entry : properties.entrySet()) {
		key = (String)entry.getKey();
		value = Integer.parseInt((String)entry.getValue());
		try {
			thisOne.getDeclaredField(key).set(this,value);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		}
	
	}
	/**
	 * 
	 * @param observateur
	 */
	public TestProdCons(Observateur observateur) {
		super(observateur);
	}

	@Override
	protected void run() throws Exception {
		this.init("../options/option.xml");
		observateur.init( nbProd, nbCons, nbBuffer);
		Producteur producteur[]=new Producteur[nbProd];
		Consommateur consommateur[]=new Consommateur[nbCons];

		
		ProdCons buffer = new ProdCons(nbBuffer, this.nbProd);
		Aleatoire a = new Aleatoire(this.nombreMoyenNbExemplaire,this.deviationNombreMoyenNbExemplaire);
		for (int i =0; i<nbProd;i++){
			
			producteur [i] = new Producteur(observateur,buffer, tempsMoyenProduction, deviationTempsMoyenProduction, nombreMoyenDeProduction, deviationNombreMoyenDeProduction,a.next());;
			producteur[i].start();
			observateur.newProducteur(producteur[i]);
		}
		
		for (int i =0; i<nbCons;i++){
			consommateur [i] = new Consommateur(observateur,buffer, tempsMoyenConsommation, deviationTempsMoyenConsommation);
			consommateur[i].setDaemon(true);
			consommateur[i].start();
			observateur.newConsommateur(consommateur[i]);
		}
		boolean bool=false;
		int summessprod=0;
		int summesscons=0;
		while(bool==false){
			summessprod=0;
			summesscons=0;
			
			for (int i =0; i<nbProd;i++){
				summessprod+=producteur[i].nombreDeMessages()*producteur[i].getNbExemplaire();
			}
			for (int i =0; i<nbCons;i++){
				summesscons+=consommateur[i].nombreDeMessages();
			}
			
			bool = summesscons==summessprod;
			
			
		}
		System.out.println("nb produit = "+summessprod+" nb conso = "+summesscons);

		
	}

	
	public static void main(String[] args){new TestProdCons(new Observateur()).start();}
	
}