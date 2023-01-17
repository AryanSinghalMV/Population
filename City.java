/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 * @author Aryan Singhal
 * @since January 9, 2023
 */
public class City implements Comparable<City>
{
	// fields
	private String state; // State city belongs in
	private String name; // Name of city
	private String designation; // Type of city
	private int population; // City population
	
	// constructor
	public City(String state, String name, String designation, int population)
	{
		this.state = state;
		this.name = name;
		this.designation = designation;
		this.population = population;
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	public int compareTo(City otherCity)
	{
		if(this.population != otherCity.population)
			return this.population - otherCity.population;
		else if(!this.state.equals(otherCity.state))
			return this.state.compareTo(otherCity.state);
		else
			return this.name.compareTo(otherCity.name);
	}
	
	/**	Accessor methods */
	
	/**
	 * Gets the name of the state of the current city
	 * @return	String		name of city's state
	 */
	public String getState()
	{
		return state;
	}
	
	/**
	 * Gets the name of the current city
	 * @return	String		name of current city
	 */
	public String getCityName()
	{
		return name;
	}
	
	/**	toString */
	@Override
	public String toString()
	{
		return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
						population);
	}
}
