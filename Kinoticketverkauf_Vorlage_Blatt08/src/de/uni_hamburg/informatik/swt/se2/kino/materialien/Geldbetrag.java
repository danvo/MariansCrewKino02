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
	
	/**
	 * Verarbeitet einen eingegebenen Geldbetrag als String und gibt ihn als Integer in Cent aus.
	 * @param eurocent Geldbetrag in Cent
	 * @return
	 */
    private int parseString(String eurocent)
    {
    	//Pattern p = Pattern.compile("[0-9]++,{0,1}[0-9]{0,2}");
    	//if (!p.matcher(eurocent).matches()) return 0;
    	int euro = 0;
    	int cent = 0;
    	if (!eurocent.equals(""))
    	{
    	    int komma = eurocent.indexOf(",");
    		String[] geldArray = eurocent.split(",");
    		if (komma != 0)
    		{
    		    euro = Integer.valueOf(geldArray[0]);
    		}
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
    
    /**
     * Verarbeitet einen Geldbetrag als int und belegt die Variablen _cent und _euro mit dem korrekten Wert.
     * @param eurocent Der Betrag in Cent
     */
    private void parseEuroCent(int eurocent)
    {
		_cent = eurocent%100;
		_euro = eurocent/100;
    }
    
    /**
     * Zieht einen übergebenen Betrag vom Geldbetrag ab.
     * @param eurocent Abzuziehender Betrag in Cent
     * @return Geldbetrag
     */
    public Geldbetrag subtract(int eurocent) 
    {
		return new Geldbetrag(getEuroCentInt() - eurocent);
	}
    
    /**
     * Zieht einen als String übergebenen Betrag vom Geldbetrag ab.
     * @param eurocent Betrag in Cent
     * @return Geldbetrag
     */
    public Geldbetrag subtract(String eurocent) 
    {
		return new Geldbetrag(getEuroCentInt() - parseString(eurocent));
	}
    
    /**
     * Zieht einen als Geldbetrag übergebenen Betrag vom Geldbetrag ab.
     * @param eurocent Betrag in Cent
     * @return Geldbetrag
     */
    public Geldbetrag subtract(Geldbetrag geldbetrag) 
    {
		return new Geldbetrag(getEuroCentInt() - geldbetrag.getEuroCentInt());
	}
    
    /**
     * Gibt den Geldbetrag in Cent als int aus.
     * @return
     */
    public int getEuroCentInt() 
    {
    	return _euro*100 + _cent;
    }
    
    /**
     * Gibt den Geldbetrag in Cent als String aus.
     * @return
     */
    public String getEuroCentString() 
    {
    	//return "" + (_cent < 0 ? "-" : "" ) + _euro + "," + (_cent < 10 ? (_cent < 0 ? (_cent < -9 ? _cent*-1 : "0" + _cent*-1) : "0" + _cent) : _cent);
    	
    	String cent = "" + Math.abs(_cent);
    	String vorzeichen = "";
    	if(_cent > -10 && _cent < 10)
    	{
    		cent = "0" + Math.abs(_cent);
    	}
    	if (_cent < 0 && _euro == 0) 
    	{
    		vorzeichen = "-";
    	}
    	return vorzeichen + _euro + "," + cent; 
    }
    
   
}
