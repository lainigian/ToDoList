
import java.io.Serializable;

/**
 * La classe Date rappresenta una data. Gli attributi sono tre numeri interi: day, month e year.
 * Nel caso in cui uno dei tre parametri assuma un valore non consentito, l'oggetto istanziato assume il valore della data
 * di default 1/1/2000. 
 * La classe espone i seguenti metodi: "diff" per calcolare la differenza in giornoi rispetto ad un'altra data, isSuccessivaA
 * per verificare se una data è successiva ad un'altra, toString per esportare la data come stringa nel formato gg\mm\aaaa.
 * 
 * @author Gian Marco Laini
 *
 */
public class Date implements Serializable
{
	
	private int day;
	private int month;
	private int year;
	
	/**
	 * Costruttore di default, imposta la data di default 1\1\2000
	 */
	public Date()
	{
		day=1;
		month=1;
		year=2000;
		
	}
	
	/**
	 * Costruttore. 
	 * @param d giorno
	 * @param m mese
	 * @param y anno
	 * Se i valori inseriti non corrispondono ad una data esistente (esempio 32/5/2000)
	 * l'oggetto istanziato assume la data di default 1\1\2000
	 */
	public Date(int d, int m, int y)
	{
		day=1;
		month=1;
		year=2000;
		
		if(y<1||m<1||m>12||d<1)
			return;
		if(m==1||m==3||m==5||m==7||m==8||m==10||m==12)
		{
			if(d>31)
				return;
		}
		if (m==4||m==6||m==9||m==11)
		{
			if(d>30)
				return;
		}
		if (m==2 && y%4==0)//bisestile
		{
			if (d>29)
				return;
		}
		if (m==2 && y%4!=0)	//non bisestile
		{
			if(d>28)
				return;
		}
		
		year=y;
		month=m;
		day=d;
	}
/**
 * Metodo getter che restituisce il giorno	
 */
	public int getDay()
	{
		return day;
	}
	
	/**
	 * Metodo getter che restituisce il mese	
	 */
	public int getMonth()
	{
		return month;
	}
	/**
	 * Metodo getter che restituisce l'anno	
	 */
	public int getYear()
	{
		return year;
	}
	
	/**
	 * Metodo setter che imposta la data di un oggetto date
	 * @param d giorno
	 * @param m mese
	 * @param y anno
	 * Se i valori inseriti non corrispondono ad una data esistente (esempio 32/5/2000)
	 * l'oggetto istanziato assume la data di default 1\1\2000.
	 */
	public void setDate(int d, int m, int y)
	{
		if(y<1||m<1||m>12||d<1)
			return;
		if(m==1||m==3||m==5||m==7||m==8||m==10||m==12)
		{
			if(d>31)
				return;
		}
		if (m==4||m==6||m==9||m==11)
		{
			if(d>30)
				return;
		}
		if (m==2 && y%4==0)//bisestile
		{
			if (d>29)
				return;
		}
		if (m==2 && y%4!=0)	//non bisestile
		{
			if(d>28)
				return;
		}
		
		year=y;
		month=m;
		day=d;
	}
	/**
	 * funzione privata per il calcolo di giorni trascorsi dall'inizio del calendari Giuliano,
	 *  viene utilizzata nel metodo diff
	 * @param d data
	 * @return numero di giorni trascorsi dall'inizio del calendario giuliano
	 */
	private int julianDate(Date d)
	{
		int a,y,m;
		
		a=(14-d.getMonth())/12;
		y=d.getYear()+4800-a;
		m=d.getMonth()+12*a-3;
		return d.getDay()+(153*m+2)/5+365*y+y/4-y/100+y/400-32045;
	}
	
	/**
	 * Restituisce la differenza, in giorni, dell'oggetto Date istanziato rispetto ad un oggetto Date 
	 * passato come parametro.
	 * @param d data passata come parametro
	 * @return la differenza in giorni fra l'oggetto istanziato e il prametro d. Se d è precedente all'oggetto istanziato 
	 * il valore di ritorno è negativo, se d è successivo all'oggetto istanziato il valore di ritorno è positivo.
	 */
	public int diff(Date d)
	{
		int my_jd, jd;
		
		my_jd=julianDate(this);
		jd=julianDate(d);
		
		return (jd-my_jd);
	}
	
	/**
	 * Verifica se l'oggetto Date istanziato 
	 * è successiva alla data passata come parametro
	 * @param d	data passata come parametro
	 * @return true se la data su cui è invocato il metodo 
	 * è successiva a d, false altrimenti.
	 */
	public boolean IsSuccessivaA (Date d)
	{
		if (julianDate(this)-julianDate(d)>0)
			return true;
		else
			return false;
	}
	
	/**
	 * Restituisce l'oggetto istanziato come stringa nel formato gg\mm\aaa
	 */
	public String toString()
	{
		return (getDay()+"\\"+getMonth()+"\\"+getYear());
	}
	
	
}
