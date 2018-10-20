import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class AfekaInstruments {

	public static void main(String args[]) throws FileNotFoundException {
		// Loads the file from the user
		Scanner instrumentsScanner = loadFileFromUser();
		ArrayList afekaStore = new ArrayList();
		try {
			// Adds all the Instruments from the file to the Store Array List
			addAllInstruments(afekaStore, loadGuitars(instrumentsScanner));
			addAllInstruments(afekaStore, loadBasses(instrumentsScanner));
			addAllInstruments(afekaStore, loadFlutes(instrumentsScanner));
			addAllInstruments(afekaStore, loadSaxophones(instrumentsScanner));

		} catch (InputMismatchException ex) {
			System.err.println("\n" + ex.getMessage());
			System.exit(1);
		} catch (IllegalArgumentException ex) {
			System.err.println("\n" + ex.getMessage());
			System.exit(2);
		} catch (NoSuchElementException ex) {
			System.err.println("\n" + ex.getMessage());
			System.exit(3);
		}

		instrumentsScanner.close();
		System.out.println("\nInstruments loaded from file successfully!\n");

		// Prints all sorts of details on the Store
		afekaStoreDetails(afekaStore);
		System.exit(0);

	}

	// Prints all sorts of details on the Store
	public static void afekaStoreDetails(ArrayList afekaStore) {
		if (afekaStore.size() > 0) {
			// Prints all the Instruments in the Store
			printInstruments(afekaStore);
			// Prints number of different Instruments in the Store
			System.out.println("\n\nDifferent Instruments: " + getNumOfDifferentElements(afekaStore));
			// Prints the most expensive Instrument in the Store
			System.out.println("\n\nMost Expensive Instrument:\n" + getMostExpensiveInstrument(afekaStore));
		} else
			// Prints that the Store is empty
			System.out.println("There are no instruments in the store currently");
	}

	// Loads file from user. It will loop until the user will type in an existing
	// file
	public static Scanner loadFileFromUser() {
		String fileName = "";
		boolean isFindExist = false;
		Scanner fileNamceScanner = new Scanner(System.in);
		Scanner instrumentsScanner = null;
		do {
			try {
				System.out.println("Please enter instruments file name / path:");
				fileName = fileNamceScanner.next();
				instrumentsScanner = new Scanner(new File(fileName));
				isFindExist = true;

			} catch (FileNotFoundException ex) {
				System.out.println("\nFile Error! Please try again:\n\n");
			}
		} while (!isFindExist);
		fileNamceScanner.close();
		return instrumentsScanner;
	}

	// Loads Guitars from Scanner
	public static ArrayList loadGuitars(Scanner s) {
		int numOfGuitar = getNumOfMusicalInstrumentsToBeLoaded(s, "guitars");
		ArrayList guitarList = new ArrayList();
		for (int i = 0; i < numOfGuitar; i++) {
			guitarList.add(new Guitar(s));
		}
		return guitarList;
	}

	// Loads Basses from Scanner
	public static ArrayList loadBasses(Scanner s) {
		int numOfBass = getNumOfMusicalInstrumentsToBeLoaded(s, "basses");
		ArrayList bassList = new ArrayList();
		for (int i = 0; i < numOfBass; i++) {
			bassList.add(new Bass(s));
		}
		return bassList;
	}

	// Loads Flutes from Scanner
	public static ArrayList loadFlutes(Scanner s) {
		int numOfFlute = getNumOfMusicalInstrumentsToBeLoaded(s, "flutes");
		ArrayList fluteList = new ArrayList();
		for (int i = 0; i < numOfFlute; i++) {
			fluteList.add(new Flute(s));
		}
		return fluteList;
	}

	// Loads Saxophones from Scanner
	public static ArrayList loadSaxophones(Scanner s) {
		int numOfSaxophone = getNumOfMusicalInstrumentsToBeLoaded(s, "saxophones");
		ArrayList saxophoneList = new ArrayList();
		for (int i = 0; i < numOfSaxophone; i++) {
			saxophoneList.add(new Saxophone(s));
		}
		return saxophoneList;
	}

	// Reads from Scanner the number of Musical Instruments to be loaded
	public static int getNumOfMusicalInstrumentsToBeLoaded(Scanner s, String instruments) {
		int numberOfInstruments = 0;
		try {
			numberOfInstruments = s.nextInt();
		} catch (InputMismatchException ex) {
			throw new InputMismatchException("Number of " + instruments + " must be a number!");
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("Corrupted file, number of " + instruments + " to be loaded is missing");
		}
		if (numberOfInstruments < 0)
			throw new IllegalArgumentException("Number of " + instruments + " must be a non-negative number!");
		else
			return numberOfInstruments;
	}

	// Adds one Array List to another Array List
	public static void addAllInstruments(ArrayList arrayList1, ArrayList arrayList2) {
		for (int i = 0; i < arrayList2.size(); i++) {
			arrayList1.add(arrayList2.get(i));
		}
	}

	// Print an Array List to Console
	public static void printInstruments(ArrayList arrayList) {
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println(arrayList.get(i));
		}
	}

	// Gets the most expensive Instrument in an Array List (of Instruments)
	public static MusicalInstrument getMostExpensiveInstrument(ArrayList arrayList) {
		MusicalInstrument mostExpensive = (MusicalInstrument) arrayList.get(0);
		for (int i = 0; i < arrayList.size(); i++) {
			if (mostExpensive.getPrice() < ((MusicalInstrument) arrayList.get(i)).getPrice())
				mostExpensive = (MusicalInstrument) arrayList.get(i);
		}

		return mostExpensive;
	}

	// Gets the number of different elements in an Array List
	public static int getNumOfDifferentElements(ArrayList arrayList) {
		ArrayList arrayListTemp = new ArrayList();
		for (int i = 0; i < arrayList.size(); i++) {
			boolean inTemp = false;
			for (int j = 0; j < arrayListTemp.size(); j++) {
				if ((arrayList.get(i)).equals(arrayListTemp.get(j))) {
					inTemp = true;
					break;
				}
			}
			if (!inTemp)
				arrayListTemp.add(arrayList.get(i));
		}
		return arrayListTemp.size();
	}
}
