import java.io.Serializable;
/**
 * La classe rappresenta una attività di ui è composto un progetto. Gli attributi sono: una descrizione dell'attività
 * la data di scadenza entro la quale deve essere completata l'attività, la percentuale di svolgimento dell'attività
 * la data di completamento dell'attività, quest'ultima data viene impostata quando lo svolgimento raggiunge la percentuale 
 * del 100%
 * @author Gian Marco Laini
 *
 */
public class Attivita implements Serializable
{
	
	//Attributi
	private String descrizione;
	private Date scadenza;
	private int svolgimento; //percentuale di svolgimento
	private Date completamento;
	
	
	/**
	 * Costruttore. Quando viene istanziata una nuova attività la ppercentuale di svolgimento viene posta a 0.
	 * @param descrizione Descrizione dell'attività
	 * @param scadenza Dataentro la qquale deve essere completato lo svolgimento dell'attività
	 * 
	 */
	public Attivita (String descrizione, Date scadenza)
	{
		this.descrizione=descrizione;
		this.scadenza=scadenza;
		svolgimento=0;
	}
	
	/**
	 * Costruttore di copia. Restituisce una attività copia della attività passata come parametro
	 * @param attivita l'attività di cui creare una copia
	 */
	public Attivita (Attivita attivita)
	{
		descrizione=attivita.getDescrizione();
		scadenza=attivita.getScadenza();
		svolgimento=attivita.getSvolgimento();
		completamento=attivita.getCompletamento();
	}
	
/**
 * Metodo getter che restituisce la descrizione dell' attività
 * @return La descrizione dell' attività
 */
	public String getDescrizione()
	{
		return descrizione;
	}
	
	/**
	 * Metodo getter che restituisce la data di scadenza dell'attività
	 * @return La data di scadenza dell'attività
	 */
	public Date getScadenza()
	{
		return scadenza;
	}
	
	/**
	 * Metodo getter che restituisce la percentuale di svolgimento dell'attività
	 * @return La percentuale di svolgimento dell'attività
	 */
	public int getSvolgimento()
	{
		return svolgimento;
	}
	/**
	 * Metodo setter che consente di impostare la data di scadenza dell'attività
	 * @param dataScadenza La data di scadenza dell'attività
	 */
	public void setScadenza(Date dataScadenza)
	{
		scadenza=dataScadenza;
	}
		
	/**
	 * Metodo Setter
	 * Consente di impostare la percentuale di svolgimento di una attività che viene completata
	 * Se la percentuale è negativa oppure maggiore di 100 il valore 
	 * dell'attributo "svolgimento" non viene modificato
	 * Se la percentuale è compreso fra 0 e 100 viene impostato l'attributo svolgimento
	 * Se la percentuale è uguale a 100 la data viene assegnata al parametro "completamento" in quanto 
	 * l'attività è da considerarsi completata. se l'attributo percentuale è diverso da 100 il parmetro dataCompletamento non
	 * ha alcun effetto.
	 * @param percentuale percentuale di svolgimento
	 * @param dataCompletamento data di completamento dell'attività
	 */
	public void setSvolgimento(int percentuale, Date dataCompletamento)
	{
		
		if (percentuale>=0 && percentuale <=100)
			svolgimento=percentuale;
		if (percentuale==100)
			completamento=dataCompletamento;
	}
	
	/**
	 * Metodo getter che restituisce la data di completamento dell'attività
	 * @return La data di completamento dell'attività
	 */
	public Date getCompletamento()
	{
		return completamento;
	}
	
	/**
	 * Restituisce una stringa contenente le informazioni sull'attività: Descrizione, data di scadenza, percentuale di svolgimento,
	 * data di completamento. Se la data di completamento non è stata impostata, anziche tale data la sequenza "..."
	 */
	public String toString()
	{
		String risultato;
		risultato= (descrizione + "\nScadenza:"+ getScadenza().toString()+ "\nPercentuale Svolgimento: "+getSvolgimento()+" %"+ "\nCompletamento :");
		if (getCompletamento()!=null)
			risultato+=getCompletamento().toString();
		else
			risultato+="...";
		return risultato;
			
	}
	
	/*public static void main(String[] args) 
	{
		// debug
		Date data1=new Date(20,3,2018);
		Attivita a1=new Attivita("Analisi iniziale dei requisiti ",data1);
		System.out.println(a1.toString());
		a1.setSvolgimento(50,new Date(10,2,2018));
		System.out.println(a1.toString());
		a1.setSvolgimento(100, new Date(15,2,2018));
		System.out.println(a1.toString());
	}*/

}
