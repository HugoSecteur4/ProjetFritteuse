package jus.poc.prodcons.v5;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
	final Lock lock = new ReentrantLock();
	final Condition notFull  = lock.newCondition(); 
	final Condition notEmpty = lock.newCondition(); 
	final Condition mutexIn = lock.newCondition(); 
	final Condition mutexOut = lock.newCondition(); 
	
	
	public ProdCons(int taille_tampon, int nb_prod) {
		super();
		this.taille_tampon = taille_tampon;
		this.nb_producteurs_total = nb_prod;
		System.out.println("taille tampon : " + taille_tampon);
		this.in = 0;
		this.out = 0;
		this.tampon = new MessageX[taille_tampon];
		this.nb_prodcts_terminated = 0;
		this.nb_message_tampon = 0;
		this.nplein=0;
		
	}



	@Override
	public int enAttente() {
		return this.nb_message_tampon;
	}

	@Override
	public Message get(_Consommateur c) throws Exception, InterruptedException {

	    lock.lock();
	    try{
	    	while(this.nplein == 0)
	    	notEmpty.await();
			MessageX m = (MessageX) tampon[out];
			this.nplein--;
			out = (out+1)%this.taille_tampon;
			this.nb_message_tampon = this.nb_message_tampon-1;
			System.out.println("---------------------------Retrait du message : "+ m.getNumero_message());
			System.out.println();
		    Consommateur cs =(Consommateur) c;
		    cs.getObservateur().retraitMessage(c,m);
//			mutexOut.signal();
			notFull.signal();
//		    mutexOut.await();
			return m;

	    }finally{
			lock.unlock();

	    }
//	    notEmpty.await();



	}

	@Override
	public  void put(_Producteur p, Message msg) throws Exception, InterruptedException {
		lock.lock();
		try{
			while(this.nplein == this.taille_tampon)
			notFull.await();
//			mutexIn.await();
			System.out.println("+++++++++++++++++++++++++++Dépose du message : " + msg.toString());
			System.out.println();
			tampon[in] = msg;
			this.nplein++;
			in = (in+1)%this.taille_tampon;
			this.nb_message_tampon = this.nb_message_tampon + 1;
		    Producteur pr =(Producteur) p;
		    pr.getObservateur().depotMessage(p, msg);
//			mutexIn.signal();
			notEmpty.signal();
		}finally{
			lock.unlock();

		}

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
