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
	private Message[] tampon;
	
	
	public ProdCons(int taille_tampon) {
		super();
		this.taille_tampon = taille_tampon;
		System.out.println("taille tampon : " + taille_tampon);
		this.in = 0;
		this.out = 0;
		this.tampon = new MessageX[taille_tampon];
		this.nplein = 0;
	}

	@Override
	public int enAttente() {
		// TODO Auto-generated method stub
		return in-out;
	}

	@Override
	public synchronized Message get(_Consommateur c) throws Exception, InterruptedException {
		while (nplein <= 0) {
			System.out.println("Le consommateur : " + c.identification() + " part en wait().");
			wait();
		}
		
		Message m = tampon[out];
		System.out.println("Retrait du message : " + m.toString());
		out = (out+1)%this.taille_tampon;
		this.nplein--;
		notifyAll();
		// TODO Auto-generated method stub
		return m;
	}

	@Override
	public synchronized void put(_Producteur p, Message msg) throws Exception, InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("tampon : " + this.taille_tampon + " nplein : " + this.nplein);
		while (this.nplein >= this.taille_tampon) {
			System.out.println("Le producteur : " + p.identification() + " part en wait().");
			wait();
		}
		
		System.out.println("Dépose du message : " + msg.toString());
		tampon[in] = msg;
		in = (in+1)%this.taille_tampon;
		nplein++;
		
		notifyAll();
		
	}

	@Override
	public int taille() {
		// TODO Auto-generated method stub
		return this.taille_tampon;
	}

}
