package model;

public class Error {
	
	private static int[] errCodeList = {1, 42};	// hein quoi json? nooon c'est tellement mieux d'écrire en dûr dans le code
	private static String[] errStrList = {
			"Oh non, une erreure!",
			"erreur code 42"};
	private int errCodeAct;
	private String errStrAct;
	
	public Error(int code) {
		this.errCodeAct=code;
		this.errStrAct=errStrList[java.util.Arrays.asList(errCodeList).indexOf(this.errCodeAct)];
	}

	public void alertError() {
		// ENVOYER A L'ERREURE A LA VUE
	}
	
	
	// getters
	public int getErrCodeAct() {
		return errCodeAct;
	}

	public String getErrStrAct() {
		return errStrAct;
	}
}
