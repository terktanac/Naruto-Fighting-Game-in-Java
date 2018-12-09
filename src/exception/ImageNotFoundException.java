package exception;

public class ImageNotFoundException extends Exception{

	public ImageNotFoundException() {
		System.err.println("Image is not found.");
	}

	public ImageNotFoundException(String message) {
		System.err.println(message + " is not found.");
	}
	
}
