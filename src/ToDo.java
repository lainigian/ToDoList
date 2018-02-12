import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import fileTxt.FileException;

public class ToDo 
{
	
	private static Progetto p1=new Progetto("Corso informatica");
	
	
	public static void salvaProgetto()
	{
		String workingDir = System.getProperty("user.dir"); //directory del progetto corrente
		FileOutputStream fileProgetto;

		try 
		{
			fileProgetto = new FileOutputStream(workingDir+"\\progetti\\"+p1.getDenominazione()+".bin");
			ObjectOutputStream outputStream=new ObjectOutputStream(fileProgetto);
			outputStream.writeObject(p1);
			outputStream.flush();
			fileProgetto.close();			
		} 
		catch (IOException e) 
		{
			System.out.println("Impossibile scrivere il file di dati");
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) 
	{
		
		//Progetto p1=new Progetto ("Corso informatica");
		 
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
			p1.esportaAttivitaSuFile(workingDir+"\\progetti\\"+p1.getDenominazione()+".txt");
			p1.esportaAttivitaCompletateSuFile(workingDir+"\\progetti\\"+p1.getDenominazione()+"_completate.txt");
			Date dataScadenza2=new Date(15,2,2018);
			p1.esportaAttivitaInScadenzaSuFile(dataScadenza,workingDir+"\\progetti\\"+p1.getDenominazione()+"_in_Scadenza_"+dataScadenza.getDay()+
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

		salvaProgetto();
	}

}
