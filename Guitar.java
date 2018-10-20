import java.util.NoSuchElementException;
import java.util.Scanner;

public class Guitar extends StringInstrument {
	private String type;
	final public static String[] GUITAR_TYPES = { "Electric", "Acoustic", "Classical" };
	final public static int ELECTRIC_MIN_NUM_OF_STRINGS = 6, ELECTRIC_MAX_NUM_OF_STRINGS = 8;
	final public static int DEFAULT_NUM_OF_STRINGS = 6;

	// Regular Constructor
	public Guitar(int price, String manufacturer, int numberOfStrings, String type) {
		super(price, manufacturer, numberOfStrings);
		setType(type);
	}

	// Regular Constructor with the default number of strings
	public Guitar(int price, String manufacturer, String type) {
		super(price, manufacturer, DEFAULT_NUM_OF_STRINGS);
		setType(type);
	}

	// Constructor that reads from Scanner
	public Guitar(Scanner s) {
		super(s);
		try {
			setType(s.next());
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException("Corrupted file, guitar type is missing");
		}
	}
	
	// Getter for Type attribute
	public String getType() {
		return type;
	}

	// Setter for Type attribute
	public void setType(String typeOfGuitar) {
		typeOfGuitar = typeOfGuitar.substring(0, 1).toUpperCase() + typeOfGuitar.substring(1).toLowerCase();
		if (!isKeyExistInArray(GUITAR_TYPES, typeOfGuitar)) {
			throw new IllegalArgumentException("Guitars are of type " + arrayAsAString(GUITAR_TYPES) + " only!");
		}

		if (typeOfGuitar.equals("Electric")) {
			if (ELECTRIC_MIN_NUM_OF_STRINGS <= this.getNumberOfStrings()
					&& this.getNumberOfStrings() <= ELECTRIC_MAX_NUM_OF_STRINGS)
				type = typeOfGuitar;
			else
				throw new IllegalArgumentException("Electric Guitars number of strings is a number between "
						+ ELECTRIC_MIN_NUM_OF_STRINGS + " and " + ELECTRIC_MAX_NUM_OF_STRINGS);
		}

		else {
			if (DEFAULT_NUM_OF_STRINGS == this.getNumberOfStrings())
				type = typeOfGuitar;
			else
				throw new IllegalArgumentException(typeOfGuitar + " Guitars have " + DEFAULT_NUM_OF_STRINGS
						+ " strings, not " + this.getNumberOfStrings());

		}

	}

	@Override
	public boolean equals(Object anotherObject) {
		if (!(anotherObject instanceof Guitar))
			return false;

		if (!type.equals(((Guitar) anotherObject).getType()))
			return false;

		return super.equals(anotherObject);
	}

	@Override
	public String toString() {
		return super.toString() + String.format("Type: %8s", type);
	}

}
