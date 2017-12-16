package jus.poc.prodcons.v4;

import jus.poc.prodcons.Message;

public class MessageX implements Message{
	public static int num_message = 1;
	private int numero_message;
	private Producteur p;
	private String message;
	private int NbExemplaire;
	
	/**
	 * 
	 * @param message
	 * @param p
	 * @param NbExemplaire
	 */
	public MessageX(String message, Producteur p,int NbExemplaire) {
		super();
		this.message = message;
		
		this.numero_message = num_message++;
		
		this.p = p;
		this.NbExemplaire=NbExemplaire;

	}
	/**
	 * 
	 * @return Retourne le numero du message.
	 */
	public int getNumero_message() {
		return this.numero_message;
	}
	
	/**
	 * 
	 * @return Retourne le nombre d'exemplaire du message restant.
	 */
	public int getNbExemplaire(){
		return this.NbExemplaire;
	}
	
	/**
	 * <P> Décrémente de 1 le nombre d'exemplaire du message.
	 */
	public void DecrNbExemplaire(){
		this.NbExemplaire--;
	}

	/**
	 * 
	 * @param numero_message
	 * <p> Setter du numero de message. </p>
	 */
	public void setNumero_message(int numero_message) {
		this.numero_message = numero_message;
	}
	/**
	 * 
	 * @return Retourne le producteur du message.
	 */
	public Producteur getP() {
		return p;
	}
	/**
	 * 
	 * @param p
	 * <p> Setter du producteur du message. </p>
	 */
	public void setP(Producteur p) {
		this.p = p;
	}
	/**
	 * 
	 * @return Retourne le message.
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * 
	 * @param message
	 * <p> Setter du message. </p>
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toString(){ 

		return "id : " + this.numero_message + " msg : " + message;
	}


	


}
