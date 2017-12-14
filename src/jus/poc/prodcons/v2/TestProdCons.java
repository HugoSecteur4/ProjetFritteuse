package jus.poc.prodcons.v2;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Properties;

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
	
	public int getNbProd() {
		return nbProd;
	}



	public int getNbCons() {
		return nbCons;
	}



	public void setNbCons(int nbCons) {
		this.nbCons = nbCons;
	}



	public int getNbBuffer() {
		return nbBuffer;
	}



	public void setNbBuffer(int nbBuffer) {
		this.nbBuffer = nbBuffer;
	}



	public int getTempsMoyenProduction() {
		return tempsMoyenProduction;
	}



	public void setTempsMoyenProduction(int tempsMoyenProduction) {
		this.tempsMoyenProduction = tempsMoyenProduction;
	}



	public int getDeviationTempsMoyenProduction() {
		return deviationTempsMoyenProduction;
	}



	public void setDeviationTempsMoyenProduction(int deviationTempsMoyenProduction) {
		this.deviationTempsMoyenProduction = deviationTempsMoyenProduction;
	}



	public int getTempsMoyenConsommation() {
		return tempsMoyenConsommation;
	}



	public void setTempsMoyenConsommation(int tempsMoyenConsommation) {
		this.tempsMoyenConsommation = tempsMoyenConsommation;
	}



	public int getDeviationTempsMoyenConsommation() {
		return deviationTempsMoyenConsommation;
	}



	public void setDeviationTempsMoyenConsommation(int deviationTempsMoyenConsommation) {
		this.deviationTempsMoyenConsommation = deviationTempsMoyenConsommation;
	}



	public int getNombreMoyenDeProduction() {
		return nombreMoyenDeProduction;
	}



	public void setNombreMoyenDeProduction(int nombreMoyenDeProduction) {
		this.nombreMoyenDeProduction = nombreMoyenDeProduction;
	}



	public int getDeviationNombreMoyenDeProduction() {
		return deviationNombreMoyenDeProduction;
	}



	public void setDeviationNombreMoyenDeProduction(int deviationNombreMoyenDeProduction) {
		this.deviationNombreMoyenDeProduction = deviationNombreMoyenDeProduction;
	}



	public int getNombreMoyenNbExemplaire() {
		return nombreMoyenNbExemplaire;
	}



	public void setNombreMoyenNbExemplaire(int nombreMoyenNbExemplaire) {
		this.nombreMoyenNbExemplaire = nombreMoyenNbExemplaire;
	}



	public int getDeviationNombreMoyenNbExemplaire() {
		return deviationNombreMoyenNbExemplaire;
	}



	public void setDeviationNombreMoyenNbExemplaire(int deviationNombreMoyenNbExemplaire) {
		this.deviationNombreMoyenNbExemplaire  = deviationNombreMoyenNbExemplaire;
	}



	public void setNbProd(int nbProd) {
		this.nbProd = nbProd;
	}



	/**
	* Retreave the parameters of the application.
	* @param file the final name of the file containing the options.
	*/
	protected void init(String file) {
	// retreave the parameters of the application
	
	/*final class Properties extends java.util.Properties {
		Class<?> thisOne = getClass();
	private static final long serialVersionUID = 1L;
	public int get(String key){return Integer.parseInt(getProperty(key));}
	public Properties(String file) {
	try{
	loadFromXML(ClassLoader.getSystemResourceAsStream(file));
	}catch(Exception e){e.printStackTrace();}
	}
	}
	Properties option = new Properties("jus/poc/prodcons/options/"+file);
	// Integer.parseInt(option.getProperty("nbProd"));
	*/
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
	
	public TestProdCons(Observateur observateur) {
		super(observateur);
	}

	@Override
	protected void run() throws Exception {
		this.init("../options/option.xml");
		Producteur producteur[]=new Producteur[nbProd];
		Consommateur consommateur[]=new Consommateur[nbCons];

		
		ProdCons buffer = new ProdCons(nbBuffer, this.nbProd);
		
		for (int i =0; i<nbProd;i++){
			producteur [i] = new Producteur(observateur,buffer, tempsMoyenProduction, deviationTempsMoyenProduction, nombreMoyenDeProduction, deviationNombreMoyenDeProduction);;
			producteur[i].start();
		}
		
		for (int i =0; i<nbCons;i++){
			consommateur [i] = new Consommateur(observateur,buffer, tempsMoyenConsommation, deviationTempsMoyenConsommation);
			consommateur[i].setDaemon(true);
			consommateur[i].start();
		}
		boolean bool=false;
		
		while(bool){
			int SumMessProd=0;
			int SumMessCons=0;
			
			for (int i =0; i<nbProd;i++){
				SumMessProd+=producteur[i].nombreDeMessages();
			}
			for (int i =0; i<nbCons;i++){
				SumMessCons+=consommateur[i].nombreDeMessages();
			}
			
			bool = SumMessProd==SumMessCons;
			
			
		}



		


		
	}

	public static void main(String[] args){new TestProdCons(new Observateur()).start();}
	
}