package exception;

public class SoundNotFoundException extends Exception{

	public SoundNotFoundException() {
		System.err.println("Image is not found.");
	}

	public SoundNotFoundException(String message) {
		System.err.println(message + " is not found.");
	}
}
