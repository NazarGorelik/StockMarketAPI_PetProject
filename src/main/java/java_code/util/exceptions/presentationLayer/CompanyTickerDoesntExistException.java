package java_code.util.exceptions.presentationLayer;

public class CompanyTickerDoesntExistException extends PresentationLayerException{
    public CompanyTickerDoesntExistException(String msg) {
        super(msg);
    }
}
