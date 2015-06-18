package de.uni_hamburg.informatik.swt.se2.kino.materialien;

import java.util.regex.Pattern;

public class Geldbetrag {
	
	int _euro;
	int _cent;
	
	public Geldbetrag(String euro) 
	{
		parseEuroCent(parseString(euro));
	}
	
	public Geldbetrag(int eurocent) 
	{
		parseEuroCent(eurocent);
	}
	
    private int parseString(String eurocent)
    {
    	//Pattern p = Pattern.compile("[0-9]++,{0,1}[0-9]{0,2}");
    	//if (!p.matcher(eurocent).matches()) return 0;
    	int euro = 0;
    	int cent = 0;
    	if (!eurocent.equals(""))
    	{
    		String[] geldArray = eurocent.split(",");
    		euro = Integer.valueOf(geldArray[0]);
    		if (geldArray.length == 2) 
    		{
    			cent = Integer.valueOf(geldArray[1]);
    			if (geldArray[1].length() == 1) {
    				cent *= 10;
    			} 
    		} 
    	}
    	return euro*100 + cent;
    }
    
    private void parseEuroCent(int eurocent)
    {
		_cent = eurocent%100;
		_euro = eurocent/100;
    }
    
    public Geldbetrag subtract(int eurocent) 
    {
		return new Geldbetrag(getEuroCentInt() - eurocent);
	}
    
    public Geldbetrag subtract(String eurocent) 
    {
		return new Geldbetrag(getEuroCentInt() - parseString(eurocent));
	}
    
    public Geldbetrag subtract(Geldbetrag geldbetrag) 
    {
		return new Geldbetrag(getEuroCentInt() - geldbetrag.getEuroCentInt());
	}
    
    public int getEuroCentInt() 
    {
    	return _euro*100 + _cent;
    }
    
    public String getEuroCentString() 
    {
    	//return "" + _euro + "," + (_cent < 10 ? (_cent < 0 ? (_cent < -9 ? _cent*-1 : "0" + _cent*-1) : "0" + _cent) : _cent);
    	
    	String cent = "" + _cent;
    	
    	if(_cent < -9)
    	{
    		cent = "" + _cent*-1;
    	}
    	
    	else if(_cent < 0)
    	{
    		cent = "0" + _cent*-1;
    	}
    	
    	else if(_cent < 10)
    	{
    		cent = "0" + _cent;
    	}
    	
    	return "" + _euro + "," + cent; 
    	
    	
    }
    
   
}
