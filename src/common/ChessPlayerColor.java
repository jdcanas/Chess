package common;

public enum ChessPlayerColor
{
	WHITE("White", "W"), 
	BLACK("Black", "B");
	
	private final String printableName;
	private final String symbol;
	
	/**
	 * The constructor for each enumerable item sets up the state so that
	 * the symbol for each item and the printable name are set up.
	 * 
	 * @param printableName the value returned from toString
	 * @param symbol a one character string that can be used when printing the board.
	 */
	private ChessPlayerColor(String printableName, String symbol)
	{
		this.printableName = printableName;
		this.symbol = symbol;
	}

	/**
	 * @return the printableName
	 */
	public String getPrintableName()
	{
		return printableName;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol()
	{
		return symbol;
	}
	
	@Override
	public String toString()
	{
		return printableName;
	}
}
