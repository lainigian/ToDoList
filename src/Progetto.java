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
import utilita.TextFile;

/**
 * La classe rappresenta un progetto. ogni progetto è composto da un certo numero di atttività. Il numero massimo di 
 * attività è 20. Gli attributi sono: "denominazione" che indica il nome del progetto e "elenco attività" che è una array di attività
 * @author User
 *
 */
public class Progetto implements Serializable, ToDoInterfaccia

{
	private static final int MAX_NUMERO_ATTIVITA=20;
	private String denominazione;
	private Attivita[] elencoAttivita;

	/**
	 * Costruttore
	 * @param denominazione Il nome da assegnare al progetto
	 */
	public Progetto(String denominazione)
	{
		this.denominazione=denominazione;
		elencoAttivita=new Attivita[MAX_NUMERO_ATTIVITA];
	}
	
	/**
	 * Costruttore di copia. Crea una copia del progetto passato come parametro.
	 * @param progetto Il progetto di cui creare una copia.
	 */
	
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
	
	/**
	 * Metodo getter che restituisce la denominazione di un progetto
	 * @return
	 */
	public String getDenominazione()
	{
		return denominazione;
	}
	
	/**
	 * Consente di creare una nuova attività nel progetto istanziato.
	 * @param descrizione La descrizione dell'attività da creare
	 * @param scadenza La data di scadenza dell'attività da creare
	 * @throws MaxNumeroAttivitaRaggiunto Eccezione che si genera in seguito al tentativo di creare una nuova attività
	 * in un progetto in cui sono già presenti un numero di attività pri al numero massimo.
	 */
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
	
	/**
	 * Elimina un'attivita dal progetto istanziato
	 * @param descrizione La descrizione dell'attività da eliminare
	 * @throws AttivitaNonPresente Eccezione generata nel caso in cui si cera di eliminare una attività
	 * la cui descrizione non è presente nell'elenco delle attività del progetto
	 */
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
	
	/**
	 * Consente di aggiornare la data di scadenza di una attività.
	 * @param descrizione La descrizione dell'attività da aggiornare
	 * @param dataScadenza La nuova data di scadenza della attività.
	 * @throws AttivitaNonPresente Eccezione sollevata nel caso in cui la descrizione della attività da aggiornare non corrisponda
	 * a quella di alcuna attività presente nel progetto.
	 */
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
	 * Overloading
	 * Aggiorna la percentuale di svolgimento di una attività. Se la percentuale assegnata è <0 o >100 il valore
	 * di percentuale di svolgimento non viene modificato. Se la percentuale è =100 vine aggiornata anche la data
	 * di completamento dell'attività assegnando automaticamente a tale data la data del giorno in cui avviene l'aggiornamento.
	 * @param descrizione	identifica l'attività da aggiornare
	 * @param percentualeSvolgimento valore a cui deve essere impostata la percentuale di aggiornamento.
	 * @throws AttivitaNonPresente	Eccezione sollevata nel caso in cui la descrizione della attività da aggiornare non corrisponda
	 * a quella di alcuna attività presente nel progetto.
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
					dataAggiornamento=new Date(gc.get(Calendar.DAY_OF_MONTH),(int)(gc.get(Calendar.MONTH))+1,gc.get(Calendar.YEAR));
					elencoAttivita[i].setSvolgimento(percentualeSvolgimento, dataAggiornamento);
					return;	//attivita aggiornata
				}
			}
	
		}
		throw new AttivitaNonPresente(); //attivita non trovata
	}
	
	/**
	 * Mostra un elenco con tutti dati di tutte le attività del progetto.
	 * @return Tutte le attività del progetto. Per ogni attività presente vengono mostrate: la descrizione, la data di scadenza,
	 * la percentuale di svolgimento e la eventuale data di completamento.
	 */
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
	
	/**
	 * Mostra un elenco con tutti dati di tutte le attività completate del progetto.
	 * @return Tutte le attività del progetto. Per ogni attività presente vengono mostrate: la descrizione, la data di scadenza,
	 * la percentuale di svolgimento, la data di completamento.
	 */
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
	 * Mostra un elenco con tutti dati di tutte le attività del progetto ancora non completate
	 * in scadenza entro una determinata data.
	 * @param data La data limite di visualizzazione, verranno mostrate tutte le attività non ancora completate la cui data di scadenza
	 * è precedente rispetto a questa data limite.
	 * @return Tutte le attività del progetto non completate in scadenza entro la data passata come parametro. 
	 * Per ogni attività presente vengono mostrate: la descrizione, la data di scadenza,
	 * la percentuale di svolgimento..
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
	
	/**	 
	 * Esporta su un file di testo un elenco in formato CSV con tutti dati di tutte le attività del progetto.
	 * Per ogni attività presente vengono memorizzate : la descrizione, la data di scadenza,
	 * la percentuale di svolgimento e la eventuale data di completamento. Nel caso l'attività non sia completata
	 * viene memorizzata la stringa "no" anzichè la data di completamento.
	 * @param nomeFile Nome del file in cui salvare le attività. Il nome deve essere comprensivo del path e 
	 * dell'estensione del file di testo.
	 * @throws IOException Eccezione che vien sollevata nel caso non sia possibile accedere al file di testo
	 * @throws FileException Eccezione che si verifica nel caso in cui il file da scrivere sia aperto in lettura.
	 */
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
	
	/**
	 * Esporta su un file di testo un elenco in formato CSV con tutti dati di tutte le attività completate del progetto.
	 * Per ogni attività completata vengono memorizzate : la descrizione, la data di scadenza,
	 * la percentuale di svolgimento e la data di completamento.
	 * @param nomeFile Nome del file in cui salvare l'elenco delle attività completate. Il nome deve essere comprensivo del path e 
	 * dell'estensione del file di testo.
	 * @throws IOException Eccezione che vien sollevata nel caso non sia possibile accedere al file di testo
	 * @throws FileException Eccezione che si verifica nel caso in cui il file da scrivere sia aperto in lettura.
	 */
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
	/**
	 * Esporta su un file di testo un elenco in formato CSV con tutti dati di tutte le attività del progetto 
	 * non completate e la cui scadenza è precedente ad una determinata data fornita come parametro.
	 * Per ogni attività completata vengono memorizzate : la descrizione, la data di scadenza,
	 * la percentuale di svolgimento.
	 * @param data La data limite di visualizzazione, verranno memorizzate sul file tutte le attività non ancora completate la cui data di scadenza
	 * è precedente rispetto a questa data limite.
	 * @param nomeFile Nome del file in cui salvare l'elenco delle attività completate. Il nome deve essere comprensivo del path e 
	 * dell'estensione del file di testo.
	 * @throws IOException Eccezione che vien sollevata nel caso non sia possibile accedere al file di testo
	 * @throws FileException Eccezione che si verifica nel caso in cui il file da scrivere sia aperto in lettura.
	 */
	
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
	
	public boolean equals (Object o)
	{
		Progetto p=(Progetto)o;
		boolean uguale;
		uguale=(getDenominazione().compareTo(p.getDenominazione())==0);
		
		Attivita[] attivitaDiP=p.elencoAttivita;
		
		for (int i = 0; i < MAX_NUMERO_ATTIVITA; i++) 
		{
			if (elencoAttivita[i]!=null && attivitaDiP[i]!=null)
				uguale= (uguale && elencoAttivita[i].equals(attivitaDiP[i]));
			else
				if (elencoAttivita[i]==null && attivitaDiP[i]==null)
					uguale=uguale && true;
				else
					return false;
		}
		return uguale;
		
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

