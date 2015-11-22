package edu.uniandes.data;

public class Date {
	
	private String dia;
	private String mes;
	private String year;
	
	public Date(String dia, String mes, String year) 
	{
		super();
		this.dia = dia;
		this.mes = mes;
		this.year = year;
	}

	public String getDia() 
	{
		return dia;
	}

	public String getMes() 
	{
		return mes;
	}

	public String getYear() 
	{
		return year;
	}
	
	public String toString()
	{
		return dia + "-" + mes + "-" + year;
	}
	
}
