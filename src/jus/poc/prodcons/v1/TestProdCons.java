package jus.poc.prodcons.v1;

import jus.poc.prodcons.Observateur;
import jus.poc.prodcons.Simulateur;

public class TestProdCons extends Simulateur{

	private int nbProd = 0;
	private int nbCons = 0;
	private int nbBuffer = 0;
	private int tempsMoyenProduction = 0;
	private int deviationTempsMoyenProduction = 0;
	private int TempsMoyenConsommation = 0;
	private int deviationTempsMoyenConsomation = 0;
	private int nombreMoyenDeProduction = 0;
	private int deviationNombreMoyenDeProduction = 0;
	public int getNbProd() {
		return nbProd;
	}

	public void setNbProd(int nbProd) {
		this.nbProd = nbProd;
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
		return TempsMoyenConsommation;
	}

	public void setTempsMoyenConsommation(int tempsMoyenConsommation) {
		TempsMoyenConsommation = tempsMoyenConsommation;
	}

	public int getDeviationTempsMoyenConsomation() {
		return deviationTempsMoyenConsomation;
	}

	public void setDeviationTempsMoyenConsomation(int deviationTempsMoyenConsomation) {
		this.deviationTempsMoyenConsomation = deviationTempsMoyenConsomation;
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

	/**
	* Retreave the parameters of the application.
	* @param file the final name of the file containing the options.
	*/
	protected static void init(String file) {
	// retreave the parameters of the application
	
	final class Properties extends java.util.Properties {
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
	
	
	}
	
	public TestProdCons(Observateur observateur) {
		super(observateur);	}

	@Override
	protected void run() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args){new TestProdCons(new Observateur()).start();}
	
}
