package CustomizedExceptionsPackage;

public class ImportantDetailsMissingException extends Exception {

	public ImportantDetailsMissingException(String info) {
		super(info);
	}
}
