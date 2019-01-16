package com.jam2in.restapi.response;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class ErrorInfo {

	public String errorMsg; //응답 시 json의 key 값이 된다.
	
	public String getErrorMsg() { //getter와 setter가 있으면 자동으로 저장되고 불러올 수 있음
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public ErrorInfo(Exception errorMsg) {
		this.errorMsg = getMessage(errorMsg);
	}

	// 서버 또는 네트워크 장애 시 ArcusClient의 queue에 대기중인 작업이 취소 되어 발생한다.
    private static final String MESSAGE_EXECUTION_EXCEPTION = "Operation was canceled due to server or network failure.";

    // 다른 쓰레드에서 해당 쓰레드의 작업을 Interrupt했을 때 InterruptedException이 발생한다
    private static final String MESSAGE_INTERRUPT_EXCEPTION = "Operation thread was interrupted by another thread.";

    // 지정한 시각에 결과가 넘어 오지 않거나 JVM의 과부하(예, full GC)로 request queue에서 처리되지 않을 경우
    private static final String MESSAGE_TIMEOUT_EXCEPTION = "Operation timeout.";

    // ArcusClient Queue에 문제가 생겨 Timeout 내에 Operation을 등록하지 못한 경우
    private static final String MESSAGE_ILLEGAL_STATE_EXCEPTION = "Operation could not be processed due to a problem in the queue.";


    public static String getMessage(Exception e) {
        if (e instanceof IllegalStateException) {
            return MESSAGE_ILLEGAL_STATE_EXCEPTION;
        } else if (e instanceof InterruptedException) {
            return MESSAGE_INTERRUPT_EXCEPTION;
        } else if (e instanceof ExecutionException) {
            return MESSAGE_EXECUTION_EXCEPTION;
        } else if (e instanceof TimeoutException) {
            return MESSAGE_TIMEOUT_EXCEPTION;
        } else {
            return e.getMessage();
        }
    }

}
