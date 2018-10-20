import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MusicalInstrument {
	private double price;
	private String manufacturer;

	// Regular Constructor
	public MusicalInstrument(double price, String manufacturer) {
		setPrice(price);
		setManufacturer(manufacturer);
	}
	
	// Constructor that reads from Scanner
	public MusicalInstrument(Scanner s) {
		try {
			setPrice(s.nextDouble());
		} catch (InputMismatchException ex) {
			throw new InputMismatchException("Price must be a number!");
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("Corrupted file, " + getClass().getCanonicalName() + " price is missing");
		}
		try {
			setManufacturer(s.next());
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("Corrupted file, " + getClass().getCanonicalName() + " manufacturer is missing");
		}
	}

	// Getter for Price attribute
	public double getPrice() {
		return price;
	}

	// Setter for Price attribute
	public void setPrice(double priceOfInstrument) {
		if (priceOfInstrument <= 0)
			throw new IllegalArgumentException("Price must be a positive number!");
		else
			price = priceOfInstrument;
	}

	// Getter for Manufacturer attribute
	public String getManufacturer() {
		return manufacturer;
	}

	// Setter for Manufacturer attribute
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer.substring(0, 1).toUpperCase() + manufacturer.substring(1).toLowerCase();
	}
	
	// Checks if the key exists in the Array of Strings
	public boolean isKeyExistInArray(String[] arrayString, String key) {
		boolean foundKey = false;
		for (int i = 0; i < arrayString.length; i++) {
			if (key.equals(arrayString[i])) {
				foundKey = true;
			}
		}
		return foundKey;
	}
	
	// Returns a String that is the printed version of an Array of Strings
	public String arrayAsAString(String[] arrayString) {
		String arrayAsAString = "";
		for (int i = 0; i < arrayString.length; i++) {
			arrayAsAString += arrayString[i];
			if (i < arrayString.length - 2)
				arrayAsAString += ", ";
			else if (i == arrayString.length - 2)
				arrayAsAString += " or ";
		}
		return arrayAsAString;
	}
	
	@Override
	public boolean equals(Object anotherObject) {
		if (!(anotherObject instanceof MusicalInstrument))
			return false;

		if (price != ((MusicalInstrument) anotherObject).getPrice())
			return false;

		if (!manufacturer.equals(((MusicalInstrument) anotherObject).getManufacturer()))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return String.format("%-8s %-9s| Price: %7.2f, ", manufacturer, getClass().getCanonicalName(), price);
	}

}
