import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *	This program sorts a list of over 30,000 USA cities and their populations
 * to answer important questions. Using Selection Sort, Insertion Sort, and	Merge Sort,
 * this program sorts for city population or city name.
 * These sorts handle 6 different important queries a user might want to ask.
 *
 *	Requires FileUtils and Prompt classes.
 *
 * @author Aryan Singhal
 * @since January 9, 2023
 */
public class Population
{
	// List of cities
	private List<City> cities;
	
	// List of states
	private List<String> states;
	
	// List of city names
	private List<String> cityNames;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	private Scanner cityScan; // File scanner
	private SortMethods sm; // instance of Sort Methods class
	
	// Constructor
	public Population()
	{
		cities = new ArrayList<City>();
		states = new ArrayList<String>();
		cityNames = new ArrayList<String>();
		sm = new SortMethods();
	}
	
	// Main method
	public static void main(String[] args)
	{
		Population p = new Population();
		p.printIntroduction();
		p.loadData();
		p.printMenu();
		p.askSelect();
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction()
	{
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu()
	{
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
	/**
	 * Reads the usPopData2017.txt file and loads each line into an List of City
	 * objects (cities).
	 */
	public void loadData()
	{
		String line;
		
		cityScan = FileUtils.openToRead(DATA_FILE);
		cityScan.useDelimiter("[\t\n]");
		
		while(cityScan.hasNextLine())
		{
			String state = "";
			String name = "";
			String designation = "";
			int population = 0;
			
			state = cityScan.next();
			name = cityScan.next();
			designation = cityScan.next();
			population = Integer.parseInt(cityScan.nextLine().trim());
			
			cities.add(new City(state, name, designation, population));
		}
			
		System.out.printf("\n%d cities in database\n\n", cities.size());
	}
	
	/**
	 * Asks the user what query they want to select. Use switch case for each
	 * query. If the user doesn't enter a valid input, prompts user again 
	 * and tells them to to input a valid input.
	 */
	public void askSelect()
	{
		int selection = Prompt.getInt("Enter selection");
		
		switch(selection)
		{
			case 1:
				leastPop();
				printMenu();
				askSelect();
				break;
			case 2:
				mostPop();
				printMenu();
				askSelect();
				break;
			case 3:
				firstFiftyByName();
				printMenu();
				askSelect();
				break;
			case 4:
				lastFiftyByName();
				printMenu();
				askSelect();
				break;
			case 5:
				fiftyMostPop();
				printMenu();
				askSelect();
				break;
			case 6:
				matchingCities();
				printMenu();
				askSelect();
				break;
			case 9:
				System.out.println("\nThanks for using Population!\n\n");
				return;
			default:
				System.out.println("\nPlease make a valid selection!\n");
				printMenu();
				askSelect();
		}
	}
	
	/**
	 * First choice for the user. Sorts the 50 least populous cities in the USA
	 * using Selection Sort.
	 */
	public void leastPop()
	{
		long startMillisec = System.currentTimeMillis();
		sm.selectionSortAscPop(cities);
		long endMillisec = System.currentTimeMillis();
		
		System.out.println("\nFifty least populous cities");
		printHeader();
		
		int count = 0;
		for(int i = 0; i < 50; i++)
		{
			count++;
			System.out.printf("%2d: %s\n", count, cities.get(i).toString());
		}
		
		System.out.println("\nElapsed time " + (endMillisec - startMillisec) + " milliseconds\n");
	}
	
	/**
	 * Second choice for the user. Sorts the 50 least most cities in the USA
	 * using Merge Sort.
	 */
	public void mostPop()
	{		
		long startMillisec = System.currentTimeMillis();
		sm.mergeSortDescPop(cities, 0, cities.size() - 1);
		long endMillisec = System.currentTimeMillis();
		
		System.out.println("\nFifty most populous cities");
		printHeader();
		
		int count = 0;
		for(int i = 0; i < 50; i++)
		{
			count++;
			System.out.printf("%2d: %s\n", count, cities.get(i).toString());
		}
		
		System.out.println("\nElapsed time " + (endMillisec - startMillisec) + " milliseconds\n");
	}
	
	/**
	 * Third choice for the user. Sorts the first 50 cities sorted by name
	 * using Insertion Sort.
	 */
	public void firstFiftyByName()
	{		
		long startMillisec = System.currentTimeMillis();
		sm.insertionSortAscName(cities);
		long endMillisec = System.currentTimeMillis();
		
		System.out.println("\nFifty cities sorted by name");
		printHeader();
		
		int count = 0;
		for(int i = 0; i < 50; i++)
		{
			count++;
			System.out.printf("%2d: %s\n", count, cities.get(i).toString());
		}
		
		System.out.println("\nElapsed time " + (endMillisec - startMillisec) + " milliseconds\n");
	}
	
	/**
	 * Fourth choice for the user. Sorts the last 50 cities sorted by name
	 * using Merge Sort.
	 */
	public void lastFiftyByName()
	{		
		long startMillisec = System.currentTimeMillis();
		sm.mergeSortDescName(cities, 0, cities.size() - 1);
		long endMillisec = System.currentTimeMillis();
		
		System.out.println("\nFifty cities sorted by name descending");
		printHeader();
		
		int count = 0;
		for(int i = 0; i < 50; i++)
		{
			count++;
			System.out.printf("%2d: %s\n", count, cities.get(i).toString());
		}
		
		System.out.println("\nElapsed time " + (endMillisec - startMillisec) + " milliseconds\n");
	}
	
	/**
	 * Fifth choice for the user. Sorts for the most populous cities in a state
	 * given by the user using merge sort.
	 */
	public void fiftyMostPop()
	{
		findUniqueStates();
		String userState = getUserState();
		
		sm.mergeSortDescPop(cities, 0, cities.size() - 1);
		
		System.out.println("\nFifty most populous cities in " + userState);
		printHeader();
		
		int count = 0;
		for(int i = 0; i < cities.size() && count < 50; i++)
		{
			if(cities.get(i).getState().equals(userState))
			{
				count++;
				System.out.printf("%2d: %s\n", count, cities.get(i).toString());
			}
		}
		System.out.println();
	}
	
	/**
	 * Finds and creates a list of all 50 unique states in the USA.
	 */
	public void findUniqueStates()
	{
		for(City c : cities)
		{
			String cityState = c.getState();
			boolean isFound = false;
			for(String s : states)
			{
				if(s.equals(cityState))
					isFound = true;
			}
								
			if(!isFound)
				states.add(cityState);
		}
	}
	
	/**
	 * Gets the state to be used to sort with from the user.
	 * Invalid inputs prompt user again for valid input.
	 * @return String		the valid state the user inputs
	 */
	public String getUserState()
	{
		String userState = "";
		userState = Prompt.getString("\nEnter state name (ie. Alabama)");
		
		if(!isValidState(userState))
		{
			System.out.printf("ERROR: %s is not valid", userState);
			return getUserState();
		}
		
		return userState;
	}
	
	/**
	 * Check if the state the user input is a valid state. User input is case
	 * sensitive.
	 * @return	boolean		if user's state input is valid
	 */
	public boolean isValidState(String state)
	{
		boolean valid = false;
		for(String s : states)
		{
			if(state.equals(s))
			  valid = true;
		}
		return valid; 
	}
	
	/**
	 * Sixth choice for the user. Sorts for all the cities in the USA with a matching
	 * name by population given by the user using merge sort.
	 */
	public void matchingCities()
	{
		findUniqueCities();
		String userCity = getCityName();
		
		sm.mergeSortDescPop(cities, 0, cities.size() - 1);
		
		System.out.println("\nCity " + userCity);
		printHeader();
		
		int count = 0;
		for(int i = 0; i < cities.size() && count < 50; i++)
		{
			if(cities.get(i).getCityName().equals(userCity))
			{
				count++;
				System.out.printf("%2d: %s\n", count, cities.get(i).toString());
			}
		}
		System.out.println();
	}
	
	/**
	 * Finds and creates a list of all the unique cities in the USA.
	 */
	public void findUniqueCities()
	{
		for(City c : cities)
		{
			String cityName = c.getCityName();
			boolean isFound = false;
			for(String name : cityNames)
			{
				if(name.equals(cityName))
					isFound = true;
			}

			if(!isFound)
				cityNames.add(cityName);
		}
	}
	
	/**
	 * Gets the city to be used to sort with from the user.
	 * Invalid inputs prompt user again for valid input.
	 * @return String		the valid city the user inputs
	 */
	public String getCityName()
	{
		String userCityName = "";
		userCityName = Prompt.getString("\nEnter city name");
		
		if(!isValidCity(userCityName))
		{
			System.out.printf("ERROR: %s is not valid", userCityName);
			return getCityName();
		}
		
		return userCityName;
	}
	
	/**
	 * Check if the city the user input is a valid city. User input is case
	 * sensitive.
	 * @return	boolean		if user's city input is valid
	 */
	public boolean isValidCity(String cityName)
	{
		boolean valid = false;
		for(String c : cityNames)
		{
			if(cityName.equals(c))
			  valid = true;
		}
		return valid; 
	}
	
	/**
	 * Prints the header for sorted query output.
	 */
	public void printHeader()
	{
		System.out.printf("%9s %21s %22s %20s\n", "State", "City", "Type", "Population");
	}
}
