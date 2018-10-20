import java.util.NoSuchElementException;
import java.util.Scanner;

public class Flute extends WindInstrument {
	final public static String[] FLUTE_TYPES = { "Transverse", "Bass", "Recorder" };
	private String type;

	// Regular Constructor
	public Flute(double price, String manufacturer, String material, String type) {
		super(price, manufacturer, material);
		setType(type);
	}

	// Constructor that reads from Scanner
	public Flute(Scanner s) {
		super(s);
		try {
		setType(s.next());}
		catch(NoSuchElementException ex) {
			throw new NoSuchElementException("Corrupted file, flute type is missing");
		}
	}

	// Getter for Type attribute
	public String getType() {
		return type;
	}

	// Setter for Type attribute
	public void setType(String typeOfFlute) {
		typeOfFlute = typeOfFlute.substring(0, 1).toUpperCase() + typeOfFlute.substring(1).toLowerCase();
		boolean foundType = isKeyExistInArray(FLUTE_TYPES, typeOfFlute);
		if (foundType)
			type = typeOfFlute;
		else
			throw new IllegalArgumentException("Flutes are of type " + arrayAsAString(FLUTE_TYPES) + " only!");
	}

	@Override
	public boolean equals(Object anotherObject) {
		if (!(anotherObject instanceof Flute))
			return false;

		if (!type.equals(((Flute) anotherObject).getType()))
			return false;

		return super.equals(anotherObject);
	}

	@Override
	public String toString() {
		return super.toString() + String.format("Type: %8s", type);
	}

}
