package de.uni_hamburg.informatik.swt.se2.kino.materialien;

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
    	return "" + _euro + "," + (_cent < 10 ? _cent + "0" : _cent);
    }
}
