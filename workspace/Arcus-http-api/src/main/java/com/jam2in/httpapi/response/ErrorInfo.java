package com.jam2in.httpapi.response;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class ErrorInfo {

	public String errorMsg; //���� �� json�� key ���� �ȴ�.
	
	public String getErrorMsg() { //getter�� setter�� ������ �ڵ����� ����ǰ� �ҷ��� �� ����
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public ErrorInfo(Exception errorMsg) {
		this.errorMsg = getMessage(errorMsg);
	}

	// ���� �Ǵ� ��Ʈ��ũ ��� �� ArcusClient�� queue�� ������� �۾��� ��� �Ǿ� �߻��Ѵ�.
    private static final String MESSAGE_EXECUTION_EXCEPTION = "Operation was canceled due to server or network failure.";

    // �ٸ� �����忡�� �ش� �������� �۾��� Interrupt���� �� InterruptedException�� �߻��Ѵ�
    private static final String MESSAGE_INTERRUPT_EXCEPTION = "Operation thread was interrupted by another thread.";

    // ������ �ð��� ����� �Ѿ� ���� �ʰų� JVM�� ������(��, full GC)�� request queue���� ó������ ���� ���
    private static final String MESSAGE_TIMEOUT_EXCEPTION = "Operation timeout.";

    // ArcusClient Queue�� ������ ���� Timeout ���� Operation�� ������� ���� ���
    private static final String MESSAGE_ILLEGAL_STATE_EXCEPTION = "Operation could not be processed due to a problem in the queue.";

    private static final String MESSAGE_NULLPOINTER_EXCEPTION = "Nullpointer exception is occured.";
    
    public static String getMessage(Exception e) {
        if (e instanceof IllegalStateException) {
            return MESSAGE_ILLEGAL_STATE_EXCEPTION;
        } else if (e instanceof InterruptedException) {
            return MESSAGE_INTERRUPT_EXCEPTION;
        } else if (e instanceof ExecutionException) {
            return MESSAGE_EXECUTION_EXCEPTION;
        } else if (e instanceof TimeoutException) {
            return MESSAGE_TIMEOUT_EXCEPTION;
        } else if (e instanceof NullPointerException){
        	return MESSAGE_NULLPOINTER_EXCEPTION;
        }else {
            return e.getMessage();
        }
    }

}
