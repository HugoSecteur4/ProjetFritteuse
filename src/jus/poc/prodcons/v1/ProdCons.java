package jus.poc.prodcons.v1;


import jus.poc.prodcons.Message;
import jus.poc.prodcons.Tampon;
import jus.poc.prodcons._Consommateur;
import jus.poc.prodcons._Producteur;

public class ProdCons implements Tampon {
	private int taille_tampon;
	private int in;
	private int out;
	private int nplein;
	private int nb_prodcts_terminated;
	private int nb_producteurs_total;
	
	private int nb_message_tampon;
	private Message[] tampon;
	
	
	public ProdCons(int taille_tampon, int nb_prod) {
		super();
		this.taille_tampon = taille_tampon;
		this.nb_producteurs_total = nb_prod;
		System.out.println("taille tampon : " + taille_tampon);
		this.in = 0;
		this.out = 0;
		this.tampon = new MessageX[taille_tampon];
		this.nplein = 0;
		this.nb_prodcts_terminated = 0;
		this.nb_message_tampon = 0;
	}



	@Override
	public int enAttente() {
		return this.nb_message_tampon;
	}

	@Override
	public synchronized Message get(_Consommateur c) throws Exception, InterruptedException {
		while (nplein <= 0) {
			System.out.println("Le consommateur : " + c.identification() + " part en wait().");
			wait();
		}
		
		MessageX m = (MessageX) tampon[out];
		out = (out+1)%this.taille_tampon;
		this.nplein--;
		this.nb_message_tampon = this.nb_message_tampon-1;
		notifyAll();
		System.out.println("---------------------------Retrait du message : "+ m.getNumero_message());
		System.out.println();
		return m;
	}

	@Override
	public synchronized void put(_Producteur p, Message msg) throws Exception, InterruptedException {
		while (this.nplein >= this.taille_tampon) {
			System.out.println("Le producteur : " + p.identification() + " part en wait().");
			wait();
		}
		
		System.out.println("+++++++++++++++++++++++++++Dépose du message : " + msg.toString());
		System.out.println();
		tampon[in] = msg;
		in = (in+1)%this.taille_tampon;
		nplein++;
		this.nb_message_tampon = this.nb_message_tampon + 1;
		
		notifyAll();
		
	}

	@Override
	public int taille() {
		return this.taille_tampon;
	}
	
	public int getNb_prodcts_terminated() {
		return nb_prodcts_terminated;
	}

	public void incrNb_prodcts_terminated() {
		this.nb_prodcts_terminated = this.nb_prodcts_terminated+1;
	}
	
	public boolean production_terminee() {
		return (this.nb_producteurs_total==this.nb_prodcts_terminated);
	}

}
