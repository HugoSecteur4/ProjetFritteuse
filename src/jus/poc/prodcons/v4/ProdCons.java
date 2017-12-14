package jus.poc.prodcons.v4;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Semaphore;

import jus.poc.prodcons.Message;
import jus.poc.prodcons.Tampon;
import jus.poc.prodcons._Consommateur;
import jus.poc.prodcons._Producteur;

public class ProdCons implements Tampon {
	private int taille_tampon;
	private int in;
	private int out;
	private int nb_prodcts_terminated;
	private int nb_producteurs_total;
	
	private int nb_message_tampon;
	private Message[] tampon;
	private Semaphore mutexIn;
	private Semaphore mutexOut;
	private Semaphore notFull;
	private Semaphore notEmpty;
	private Map<Integer,Semaphore> ConsSem;
	private Map<Integer,Semaphore> ProdSem;
	
	
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
		mutexIn = new Semaphore(1);
		mutexOut = new Semaphore(1);
		notFull = new Semaphore(taille_tampon);
		notEmpty = new  Semaphore(0);
		ProdSem = new HashMap<>();
		ConsSem = new HashMap<>();
				
	}



	@Override
	public int enAttente() {
		return this.nb_message_tampon;
	}

	@Override
	public Message get(_Consommateur c) throws Exception, InterruptedException {
		System.out.println("debut");

		notEmpty.acquire();
		System.out.println("ent");

		mutexOut.acquire();
		System.out.println("entééééééé");

		MessageX m = (MessageX) tampon[out];
		System.out.println("out : "+out);
		if(m.getNbExemplaire()==1){
			System.out.println("ggggggggggggggggggggggggggggggg");
			if(ConsSem.get(c.identification())!=null){
				//ConsSem.clear();
				//ProdSem.values()
				
				ConsSem.get(c.identification()).acquire();
			}else{
				Semaphore cc = new Semaphore(1);
				cc.acquire();
				ConsSem.put(c.identification(), cc);
				
				out = (out+1)%this.taille_tampon;
				this.nb_message_tampon = this.nb_message_tampon-1;
				System.out.println("---------------------------Retrait d'un exemplaire message : "+ m.getNumero_message() + " - " + m.getNbExemplaire()+"par "+c.identification());
				System.out.println();
			    Consommateur cs =(Consommateur) c;
			    cs.getObservateur().retraitMessage(c,m);
				
				m.DecrNbExemplaire();

				notFull.release();
				ProdSem.get(m.getP().identification()).release();
			}
			
			for(Entry<Integer, Semaphore> entry : ConsSem.entrySet()) {
				System.out.println(entry.getValue().drainPermits());
				entry.getValue().release();
				System.out.println(entry.getValue().drainPermits());
				System.out.println("on passe "+entry.getKey());
			}
			ConsSem.clear();
			System.out.println("vide ? "+ConsSem.isEmpty());
			

		}else{
			if(ConsSem.get(c.identification())!=null){
				//ConsSem.clear();
				//ProdSem.values()
				System.out.println("je dors");
				ConsSem.get(c.identification()).acquire();
				System.out.println("viens de se libérer");
			}else{
				Semaphore cc = new Semaphore(1);
				cc.acquire();
				ConsSem.put(c.identification(), cc);
				
				System.out.println("---------------------------Retrait d'un exemplaire message : "+ m.getNumero_message() + " - " + m.getNbExemplaire()+"par "+c.identification());
				System.out.println();
			    Consommateur cs =(Consommateur) c;
			    cs.getObservateur().retraitMessage(c,m);
			    m.DecrNbExemplaire();
			}
			mutexOut.release();

		}

		mutexOut.release();
		System.out.println("mutexout");

		
		System.out.println("notfull");



		return m;
	}

	@Override
	public  void put(_Producteur p, Message msg) throws Exception, InterruptedException {
		notFull.acquire();
		mutexIn.acquire();
		
		if (ProdSem.get(p.identification())!=null) {
			ProdSem.get(p.identification()).acquire();
		} else {
			Semaphore se = new Semaphore(1);
			se.acquire();
			ProdSem.put(p.identification(),se);
		}
		

		System.out.println("+++++++++++++++++++++++++++Dépose du message : " + msg.toString());
		System.out.println();
		tampon[in] = msg;
		in = (in+1)%this.taille_tampon;
		this.nb_message_tampon = this.nb_message_tampon + 1;
	    Producteur pr =(Producteur) p;
	    pr.getObservateur().depotMessage(p, msg);
	    MessageX m = (MessageX) msg;
		mutexIn.release();
		for(int i = 0;i<m.getNbExemplaire(); i++)
		notEmpty.release();
		
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
