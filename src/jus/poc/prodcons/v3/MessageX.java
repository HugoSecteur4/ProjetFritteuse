package jus.poc.prodcons.v3;

import jus.poc.prodcons.Message;

public class MessageX implements Message{
	public static int num_message = 1;
	private int numero_message;
	private Producteur p;
	private String message;
	
	
	public MessageX(String message, Producteur p) {
		super();
		this.message = message;
		
		this.numero_message = num_message++;
		
		this.p = p;
		
		//num_message = num_message +1;

	}
	
	public int getNumero_message() {
		return this.numero_message;
	}

	public void setNumero_message(int numero_message) {
		this.numero_message = numero_message;
	}

	public Producteur getP() {
		return p;
	}

	public void setP(Producteur p) {
		this.p = p;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toString(){ 
		//return "Message " + numero_message + " du producteur " + p.identification() + " : " + message;
		return "id : " + this.numero_message + " msg : " + message;
	}


	


}
