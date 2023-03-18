package dataSet;


/**
 * We don't allow the datasize be too small. Specifically, smaller than 6.
 * @author haopengwu
 *
 */
public class DataScaleTooSmallException extends Exception {
	DataScaleTooSmallException(String errorMessage){
		super(errorMessage);
	}
}
