import java.util.Scanner;
/**
 * Rappresenta un menu. Consente di Creare un menu con delle voci numeriche e di effettuare i controlli sulla
 * correttezza del dato inserito da parte dell'utente. Consente all'utente di inserire solamente valori numerici compresi fra 
 * 0 e (numero di voci del menu)-1. In caso di inserimento non corrretto o non conforme al formato richiesto
 * l'utente viene invitato ad eseguire nuovamente la scelta.
 * Gli attributi sono: un elenco di stringhe che rappresentano le varie voci del menu, un intero che rappresenta il numero di voci 
 * di cui è composto il menu.
 * 
 * @author Laini Gian Marco
 *
 */
public class Menu2 
{

	private String[] elencoVoci;
	int numeroVoci=0;

	/**
	 * Costruttore
	 * @param elencoVoci Array di stringhe dve ogni elemento rappresenta una voce del menu da costruire
	 */
	public Menu2(String[] elencoVoci)
	{
		numeroVoci=elencoVoci.length;
		this.elencoVoci=new String[numeroVoci];
		
		for (int i = 0; i < numeroVoci; i++) 
			this.elencoVoci[i]=elencoVoci[i];
	}
	
	/**
	 * Visualizza sul monitor tutte le voci del menu
	 */
	private void visualizzaMenu()
	{
		for (int i = 0; i < elencoVoci.length; i++) 
			System.out.println(elencoVoci[i]);
	}
	
	/**
	 * Consente all'utente di scegliere fra una delle voci numeriche del menu. In caso di scelta non corretta 
	 * o formato del dato inserito non conforme (ad esempio inserimento di una stringa anzichè di un numero)
	 * L'utente viene invitato ad effettuare nuovament la propria scelta.
	 * @return Il numero intero corrispondente alla voce del menu scelta dall'utente.
	 */
	public int sceltaMenu()
	{
		Scanner tastiera=new Scanner(System.in);
		boolean inputNumerico=true;
		int scelta=-1;

		
		do
		{
			inputNumerico=true;
			visualizzaMenu();
			System.out.println("Scegli:");
			String stringaScelta=tastiera.nextLine();
			//controllo se i caratteri sono tutti numeri
			for (int i = 0; i < stringaScelta.length(); i++) 
			{
				if (!(stringaScelta.charAt(i)>=48 && stringaScelta.charAt(i)<=57))
					inputNumerico=false;
			}
			if (inputNumerico && stringaScelta.length()>0)
				scelta=Integer.parseInt(stringaScelta);
			else
				System.out.println("Input non numerico. Ripetere la scelta");
			if(scelta<0 || scelta>=numeroVoci)
				System.out.println("Input non valido");
			
		}while(scelta<0 || scelta>=numeroVoci);		
		return scelta;
		
	}
	

	
	
}
