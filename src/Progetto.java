import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import fileTxt.*;

public class Progetto implements Serializable, ToDoInterfaccia

{
	private static final int MAX_NUMERO_ATTIVITA=20;
	private String denominazione;
	private Attivita[] elencoAttivita;

	
	public Progetto(String denominazione)
	{
		this.denominazione=denominazione;
		elencoAttivita=new Attivita[MAX_NUMERO_ATTIVITA];
	}
	
	public Progetto (Progetto progetto)
	{
		this.denominazione=progetto.getDenominazione();
		elencoAttivita=new Attivita[MAX_NUMERO_ATTIVITA];
		for (int i = 0; i < MAX_NUMERO_ATTIVITA; i++) 
		{
			if (progetto.elencoAttivita[i]!=null)
				elencoAttivita[i]=new Attivita(progetto.elencoAttivita[i]);
		}
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
	 * di percentuale di svolgimento non viene modificato. Se la percentuale è =100 vine aggiornata anche la data
	 * di completamento dell'attività impostando la data del giorno in cui avviene l'aggiornamento.
	 * @param descrizione	identifica l'attività da aggiornare
	 * @param percentualeSvolgimento valore a cui deve essere impostata la percentuale di aggiornamento 
	 * @param dataAggiornamento data in cui viene realizzato l'aggiornamento. Se la percentuale di aggiornamento è pari a 100
	 * questa data viene impostata come Data di completamento dell'attivitò
	 * @throws AttivitaNonPresente	viene sollevata quando l'attività identificata da "descrizione" non è prsente nell'array
	 * di attività che compongono il progetto
	 */
	public void aggiornaAttivita(String descrizione, int percentualeSvolgimento) throws AttivitaNonPresente
	{
		Attivita attivita;
		for (int i = 0; i < elencoAttivita.length; i++) 
		{
			if (elencoAttivita[i]!=null)
			{
				attivita=elencoAttivita[i];
				if (attivita.getDescrizione().equals(descrizione))
				{
					Date dataAggiornamento;
					GregorianCalendar gc=new GregorianCalendar();
					dataAggiornamento=new Date(gc.get(Calendar.DAY_OF_MONTH),gc.get(Calendar.MONTH+1),gc.get(Calendar.YEAR));
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
/*	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		//---------------------------------------------------------------------------------------------------------------	
		Progetto p1=new Progetto ("Corso informatica");
		 
		try 
		{
			p1.creaAttivita("Introduzione alla OOP", new Date(15,10,2017));
			p1.creaAttivita("Linguaggio JAVA", new Date(1,12,2017));
			p1.creaAttivita("Array in Java", new Date(20,1,2018));
			p1.creaAttivita("Eccezioni in Java", new Date(28,2,2018));
			p1.creaAttivita("File di testo in Java", new Date(10,3,2018));
			p1.creaAttivita("Serializzazione in Java", new Date(20,3,2018));
		} 
		catch (MaxNumeroAttivitaRaggiunto e) 
		{
			System.out.println(e.toString());
			e.printStackTrace();
		}
	
		try 
		{
		//	p1.eliminaAttivita("Array in Java");
			p1.aggiornaAttivita("Array in Java", new Date(25,3,2018));
			p1.aggiornaAttivita("Array in Java", 100,new Date(12,2,2018));
		} 
		catch (AttivitaNonPresente e)
		{
			System.out.println(e.toString());
			e.printStackTrace();
		}
		
		Attivita[] elenco;
		elenco=p1.elencaAttivita();
		System.out.println(p1.getDenominazione());
		for (int i = 0; i < elenco.length; i++) 
		{
			System.out.println(elenco[i].toString());
			System.out.println("-----------------------------");
		}	

		Attivita[] elencoAttivitaCompletate;
		elencoAttivitaCompletate=p1.elencaAttivitaCompletate();
		System.out.println(p1.getDenominazione()+"\nElenco attività completate\n");
		for (int i = 0; i < elencoAttivitaCompletate.length; i++) 
		{
			System.out.println(elencoAttivitaCompletate[i].toString());
			System.out.println("-----------------------------");
		}	
		
	
		Attivita[] elencoAttivitaInScadenza;
		Date dataScadenza=new Date(12,2,2018);
		elencoAttivitaInScadenza=p1.elencaAttivitaInScadenza(dataScadenza);
		System.out.println(p1.getDenominazione()+"\nElenco attività in scadenza prima di "+ dataScadenza.toString()+"\nnon ancora completate\n");
		for (int i = 0; i < elencoAttivitaInScadenza.length; i++) 
		{
			System.out.println(elencoAttivitaInScadenza[i].toString());
			System.out.println("-----------------------------");
		}	
		
		String workingDir = System.getProperty("user.dir"); //directory del progetto corrente
		try 
		{
			p1.esportaAttivitaSuFile(workingDir+"\\stampeProgetti\\"+p1.getDenominazione()+".txt");
			p1.esportaAttivitaCompletateSuFile(workingDir+"\\stampeProgetti\\"+p1.getDenominazione()+"_completate.txt");
			Date dataScadenza2=new Date(15,2,2018);
			p1.esportaAttivitaInScadenzaSuFile(dataScadenza,workingDir+"\\stampeProgetti\\"+p1.getDenominazione()+"_in_Scadenza_"+dataScadenza.getDay()+
					"_"+dataScadenza.getMonth()+"_"+dataScadenza.getYear()+".txt");
		} 
		catch (IOException e) 
		{
			System.out.println(e.toString());
			e.printStackTrace();
		} 
		catch (FileException e) 
		{
			System.out.println(e.toString());
			e.printStackTrace();
		}

		
		FileOutputStream f1;
		String directoryProgetti = System.getProperty("user.dir")+"\\elencoProgetti\\"; //directory del progetto corrente
		

		try 
		{
			
			f1 = new FileOutputStream(directoryProgetti+"Corso informatica.bin");
			ObjectOutputStream outputStream=new ObjectOutputStream(f1);
			outputStream.writeObject(p1);
			outputStream.flush();
			f1.close();			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
					
//-----------------------------------------------------------------------------------------------------
		
		//-----------------------deserializzazione di un progetto   OK funziona--------------------------------
		Attivita[] elenco;
		String directoryProgetti = System.getProperty("user.dir")+"\\elencoProgetti\\"; //directory del progetto corrente
		Progetto p2 = null;
			try 
			{
				FileInputStream f2;
				f2 = new FileInputStream(directoryProgetti+"Corso informatica.bin");
				ObjectInputStream streamInput= new ObjectInputStream(f2);
				p2=(Progetto)streamInput.readObject();
				f2.close();
			} 
			catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (ClassNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			elenco=p2.elencaAttivita();
			System.out.println(p2.getDenominazione());
			for (int i = 0; i < elenco.length; i++) 
			{
				System.out.println(elenco[i].toString());
				System.out.println("-----------------------------");
			}

		} 
*/		
}

