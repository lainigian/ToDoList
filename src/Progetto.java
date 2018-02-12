import java.io.IOException;
import java.io.Serializable;

import fileTxt.*;

public class Progetto implements Serializable

{
	private static final int MAX_NUMERO_ATTIVITA=20;
	private String denominazione;
	private Attivita[] elencoAttivita;

	
	public Progetto(String denominazione)
	{
		this.denominazione=denominazione;
		elencoAttivita=new Attivita[MAX_NUMERO_ATTIVITA];
	}
	
	public String getDenominazione()
	{
		return denominazione;
	}
	
	public void creaAttivita(String descrizione, Date scadenza) throws MaxNumeroAttivitaRaggiunto
	{
		for (int i = 0; i < elencoAttivita.length; i++) 
		{
			if (elencoAttivita[i]==null)
			{
				elencoAttivita[i]=new Attivita(descrizione,scadenza);
				return;
			}	
		}
		throw new MaxNumeroAttivitaRaggiunto(); // se esco dal ciclo senza creare attività è perchè 'non c'è più posto
	}
	
	public void eliminaAttivita(String descrizione) throws AttivitaNonPresente
	{
		Attivita attivita;
		for (int i = 0; i < elencoAttivita.length; i++) 
		{
			if (elencoAttivita[i]!=null)
			{
				attivita=elencoAttivita[i];
				if (attivita.getDescrizione().equals(descrizione))
				{
					elencoAttivita[i]=null;
					return;	//attivita eliminata
				}
			}
	
		}
		throw new AttivitaNonPresente(); //attivita non trovata
	}
	
	public void aggiornaAttivita(String descrizione, Date dataScadenza) throws AttivitaNonPresente
	{
		Attivita attivita;
		for (int i = 0; i < elencoAttivita.length; i++) 
		{
			if (elencoAttivita[i]!=null)
			{
				attivita=elencoAttivita[i];
				if (attivita.getDescrizione().equals(descrizione))
				{
					elencoAttivita[i].setScadenza(dataScadenza);
					return;	//attivita aggiornata
				}
			}
	
		}
		throw new AttivitaNonPresente(); //attivita non trovata
	}
	
	
	/**
	 * Aggiorna la percentuale di svolgimento di una attività. Se la percentuale assegnata è <0 o >100 il valore
	 * di percentuale di svolgimento non viene modificato. Se la percentuale è =100 vine aggiornata nache la data
	 * di completamento dell'attività
	 * @param descrizione	identificca l'attività da aggiornare
	 * @param percentualeSvolgimento	valore a cui deve essere impostata la percentuale di aggiornamento 
	 * @param dataAggiornamento data a cui viene realizzato l'aggiornamento. Se la percentuale di aggiornamento è pari a 100
	 * questa data viene impostata come Data di completamento dell'attivitò
	 * @throws AttivitaNonPresente	viene sollevata quando l'attività identificata da "descrizione" non è prsente nell'array
	 * di attività che compongono il progetto
	 */
	public void aggiornaAttivita(String descrizione, int percentualeSvolgimento, Date dataAggiornamento) throws AttivitaNonPresente
	{
		Attivita attivita;
		for (int i = 0; i < elencoAttivita.length; i++) 
		{
			if (elencoAttivita[i]!=null)
			{
				attivita=elencoAttivita[i];
				if (attivita.getDescrizione().equals(descrizione))
				{
					elencoAttivita[i].setSvolgimento(percentualeSvolgimento, dataAggiornamento);
					return;	//attivita aggiornata
				}
			}
	
		}
		throw new AttivitaNonPresente(); //attivita non trovata
	}
	
	public  Attivita[] elencaAttivita()
	{
		
		int contatore=0;
		for (int i = 0; i < elencoAttivita.length; i++) 
		{
			if (elencoAttivita[i]!=null)
				contatore++;
		}
		
		Attivita[] elenco=new Attivita[contatore];
		int j=0;
		for (int i = 0; i < elencoAttivita.length; i++) 
		{
			if (elencoAttivita[i]!=null)
			{
				elenco[j]=new Attivita(elencoAttivita[i]);
				j++;
			}		
		}
		
		return elenco;
	}
	
	
	public  Attivita[] elencaAttivitaCompletate()
	{
		
		int contatore=0;
		for (int i = 0; i < elencoAttivita.length; i++) 
		{
			if (elencoAttivita[i]!=null)
			{
				if (elencoAttivita[i].getCompletamento()!=null)
					contatore++;
			}
				
		}
		
		Attivita[] elenco=new Attivita[contatore];
		int j=0;
		for (int i = 0; i < elencoAttivita.length; i++) 
		{
			if (elencoAttivita[i]!=null)
			{
				if (elencoAttivita[i].getCompletamento()!=null)
				{
					elenco[j]=new Attivita(elencoAttivita[i]);
					j++;
				}
			}		
		}
		
		return elenco;
	}
	
	
	/**
	 * restituisce tutte le attività non ancora completate e in scadenza
	 * prima di una determinata data
	 * @param data
	 * @return
	 */
	public  Attivita[] elencaAttivitaInScadenza(Date data)
	{
		int contatore=0;
		for (int i = 0; i < elencoAttivita.length; i++) 
		{
			if (elencoAttivita[i]!=null)
			{
				if (elencoAttivita[i].getCompletamento()==null && data.IsSuccessivaA(elencoAttivita[i].getScadenza()))
					contatore++;
			}		
		}
		Attivita[] elencoAttivitaInScadenza=new Attivita[contatore];
		int j=0;
		for (int i = 0; i < elencoAttivita.length; i++) 
		{
			if (elencoAttivita[i]!=null)
			{
				if (elencoAttivita[i].getCompletamento()==null && data.IsSuccessivaA(elencoAttivita[i].getScadenza()))
				{
					elencoAttivitaInScadenza[j]=new Attivita(elencoAttivita[i]);
					j++;
				}
			}		
		}
		
		return elencoAttivitaInScadenza;
	}
	
	
	public void esportaAttivitaSuFile(String nomeFile) throws IOException, FileException
	{
		Attivita[] elenco;
		elenco=elencaAttivita();//elenco delle attività
		
		TextFile fileAttivita=new TextFile(nomeFile,'w');
		String stringaAttivita="";
		
		for (int i = 0; i < elenco.length; i++) 
		{
			stringaAttivita=elenco[i].getDescrizione()+";"+elenco[i].getScadenza().toString()+";"+elenco[i].getSvolgimento()+";";
			if (elenco[i].getCompletamento()==null)
				stringaAttivita+="no;";
			else
				stringaAttivita+=elenco[i].getCompletamento().toString()+";";
			fileAttivita.toFile(stringaAttivita);
		}
		fileAttivita.close();
	}
	
	public void esportaAttivitaCompletateSuFile(String nomeFile) throws IOException, FileException
	{
		Attivita[] elencoAttivitaCompletate;
		elencoAttivitaCompletate=elencaAttivitaCompletate();//elenco delle attività
		
		TextFile fileAttivita=new TextFile(nomeFile,'w');
		String stringaAttivita="";
		
		for (int i = 0; i < elencoAttivitaCompletate.length; i++) 
		{
			stringaAttivita=elencoAttivitaCompletate[i].getDescrizione()+";"+elencoAttivitaCompletate[i].getScadenza().toString()+";"+elencoAttivitaCompletate[i].getSvolgimento()+";";
			stringaAttivita+=elencoAttivitaCompletate[i].getCompletamento().toString()+";";
			fileAttivita.toFile(stringaAttivita);
		}
		fileAttivita.close();
	}
	
	public void esportaAttivitaInScadenzaSuFile(Date dataScadenza, String nomeFile) throws IOException, FileException
	{
		Attivita[] elencoAttivitaInScadenza;
		elencoAttivitaInScadenza=elencaAttivitaInScadenza(dataScadenza);//elenco delle attività
		
		TextFile fileAttivita=new TextFile(nomeFile,'w');
		String stringaAttivita="";
		
		for (int i = 0; i < elencoAttivitaInScadenza.length; i++) 
		{
			stringaAttivita=elencoAttivitaInScadenza[i].getDescrizione()+";"+elencoAttivitaInScadenza[i].getScadenza().toString()+";"+elencoAttivitaInScadenza[i].getSvolgimento()+";";
			fileAttivita.toFile(stringaAttivita);
		}
		fileAttivita.close();
	}
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
	}

}
