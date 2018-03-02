import java.io.Serializable;

public class Date implements Serializable
{
	
	private int day;
	private int month;
	private int year;
	
	
	public Date()
	{
		day=1;
		month=1;
		year=2000;
		
	}
	
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
	
	public int getDay()
	{
		return day;
	}
	public int getMonth()
	{
		return month;
	}
	public int getYear()
	{
		return year;
	}
	
	public void setDate(int d, int m, int y)
	{
		if(y<1||m<1||m>12||d<1)
			return;
		if(m==1||m==3||m==5||m=7||m==8||m==10||m==12)
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
	 * funzione per il calcolo di giorni trascorsi dall'inizio del calendari Giuliano
	 * serve per il metodo diff
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
	
	public int diff(Date d)
	{
		int my_jd, jd;
		
		my_jd=julianDate(this);
		jd=julianDate(d);
		
		return (jd-my_jd);
	}
	
	/**
	 * Verifica se la data su cui è invocato il metodo 
	 * è successiva alla data passata come parametro
	 * @param d	data passata come parametro
	 * @return true se la data su cui è invocato il metodo 
	 * è successiva a d
	 */
	public boolean IsSuccessivaA (Date d)
	{
		if (julianDate(this)-julianDate(d)>0)
			return true;
		else
			return false;
	}
	
	public String toString()
	{
		return (getDay()+"\\"+getMonth()+"\\"+getYear());
	}
	
}
