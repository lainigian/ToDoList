import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.crypto.NoSuchMechanismException;

public class MainClass 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		String[] vociMenu= {"0 --> ESCI","1 --> Elenca progetti", "2 --> Apri un progetto","3 --> Crea un nuovo progetto"};
		
		Menu2 menuIniziale=new Menu2(vociMenu);
		Progetto progetto;
		ConsoleInput tastiera= new ConsoleInput();
		int sceltaMenu=0;
		String[] elencoProgetti=ToDo.elencaProgetti();
		
		do
		{
			sceltaMenu=menuIniziale.sceltaMenu();
			try 
			{
				switch (sceltaMenu) 
				{
				case 1:
					elencoProgetti=ToDo.elencaProgetti();
					for (int i = 0; i < elencoProgetti.length; i++) 
						System.out.println(elencoProgetti[i]);
					break;
				case 2:
					System.out.println("Inserisci il nome del progetto da aprire: ");
					String nomeProgetto=tastiera.readString();
					progetto=new Progetto (ToDo.caricaProgetto(nomeProgetto));
					gestioneProgetto(progetto); 
					break;
				
				case 3:
					System.out.println("Inserisci il nome del nuovo progetto");
					nomeProgetto=tastiera.readString();
					progetto=new Progetto(ToDo.creaProgetto(nomeProgetto));
					gestioneProgetto(progetto);
					break;
	
				default:
					break;
				}
			} 
			
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				System.out.println("Errore nell'apertura del file");
				System.out.println(e.getMessage());
				//e.printStackTrace();
			} 
			catch (ClassNotFoundException e) 
			{
				// TODO Auto-generated catch block
				System.out.println("Errore nel caricamento del progetto");
				//e.printStackTrace();
			}

		}while (sceltaMenu!=0);
		
		

	}
	
	private static void gestioneProgetto(Progetto progetto)
	{
		//Gestisci il progetto
		String[] vociMenuProgetto= {"0 --> Torna al menu prcedente","1 --> Aggiorna data di scadenza di una attività", 
						"2 --> Aggiorna percentuale di svolgimento di una attività", "3 --> Crea nuova attività", "4 --> Elimina attività",
					"5 --> Visualizza tutte le attività","6 --> Visualizza attività completate", "7 --> Visualizza attività in scadenza", 
						"8 --> Esporta su file tutte le attività", "9 --> Esporta su file attività completate", 
						"10 --> Esporta su file attività in scadenza", "11 --> Salva modifiche al progetto","12 --> Elimina progetto" };
		Menu2 menuProgetto=new Menu2(vociMenuProgetto);
		ConsoleInput tastiera= new ConsoleInput();
		String descrizioneAttivita;
		int sceltaMenu=0;
		GregorianCalendar gc = new GregorianCalendar();
		int oggiAnno = gc.get(Calendar.YEAR);
		int oggiMese=gc.get(Calendar.MONTH);
		int oggiGiorno=gc.get(Calendar.DAY_OF_MONTH);
		
		Attivita[] elencoAttivita;
	do
	{
		try 
		{
			sceltaMenu=menuProgetto.sceltaMenu();
			switch (sceltaMenu) 
			{
			case 1:
				System.out.println("Inserisci il nome dell'attività da aggiornare");
				descrizioneAttivita=tastiera.readString();
				System.out.println("Inserisci la nuova data di scadenza.\nInserisci il giorno:");
				int d=tastiera.readInt();
				System.out.println("Inserisci il mese:");
				int m=tastiera.readInt();
				System.out.println("Inserisci l'anno:");
				int y=tastiera.readInt();
				Date nuovaData= new Date(d,m,y);	
				progetto.aggiornaAttivita(descrizioneAttivita, nuovaData);
				break;

			case 2:
				System.out.println("Inserisci il nome dell'attività da aggiornare");
				descrizioneAttivita=tastiera.readString();
				System.out.println("Inserisci la nuova percentuale di svolgimento:");
				int p=tastiera.readInt();
				progetto.aggiornaAttivita(descrizioneAttivita, p);
				break;
				
			case 4:
				System.out.println("Inserisci il nome dell'attività da eliminare");
				descrizioneAttivita=tastiera.readString();
				System.out.println(" Attività da eliminare: ");
				elencoAttivita=progetto.elencaAttivita();
				String conferma="";
				for (int i = 0; i < elencoAttivita.length; i++) 
				{
					if (elencoAttivita[i].getDescrizione().compareToIgnoreCase(descrizioneAttivita)==0)
					{
						System.out.println("L'attività "+descrizioneAttivita+" verrà eliminata. Procedere? (si/no)");
						conferma=tastiera.readString();
						break;
					}	
				}
				
				if (conferma.compareTo("si")==0)
				{
					progetto.eliminaAttivita(descrizioneAttivita);
					System.out.println("Attività eliminata!");
				}		
				else
					System.out.println("Operazione annullata");
				break;
			
			
			case 3:
				System.out.println("Inserisci il nome della nuova attività");
				String nomeAttivita=tastiera.readString();
				System.out.println("Inserisci la data di scadenza\nGiorno:");
				int giorno=tastiera.readInt();
				System.out.println("Mese:");
				int mese=tastiera.readInt();
				System.out.println("Anno:");
				int anno=tastiera.readInt();
				
				progetto.creaAttivita(nomeAttivita, new Date(giorno,mese, anno));
				break;
			
				
			case 5:
				elencoAttivita=progetto.elencaAttivita();
				for (int i = 0; i < elencoAttivita.length; i++) 
				{
					System.out.println(elencoAttivita[i].toString());
				}
				break;
			
				
				
			case 11:
				ToDo.salvaProgetto(progetto);
				break;
			
			case 12:
				System.out.println("Il progetto: "+progetto.getDenominazione()+ "\nverrà definitivamente eliminato! Continuare? (scegli si o annulla)");
				String continua=tastiera.readString();
				if (continua.compareToIgnoreCase("si")==0)
				{
					if (ToDo.eliminaProgetto(progetto.getDenominazione()))
						System.out.println("Progetto eliminato!");
					else
						System.out.println("Non è stato possibile eliminare il progetto");	
				}
				else
					System.out.println("Operazione annullata");	
			
			default:
				break;
			}
			
		
		} 
		catch (AttivitaNonPresente e) 
		{
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		catch (MaxNumeroAttivitaRaggiunto e) 
		{
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		catch (progettoNonPresente e) 
		{
			System.out.println(e.getMessage());		
			//e.printStackTrace();
		}
	} while (sceltaMenu!=0 && sceltaMenu!=12);
		
		
		
	}

}
