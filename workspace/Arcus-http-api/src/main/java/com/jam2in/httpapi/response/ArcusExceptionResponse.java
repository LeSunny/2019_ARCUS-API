package com.jam2in.httpapi.response;


import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jam2in.httpapi.Controller.HomeController;

@ControllerAdvice //����ó�� : ����ó���� �ô� Class
public class ArcusExceptionResponse {
	/*InterruptedException
	ExecutionException
	TimeoutException
	IOException
	*/
	 
//    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(InterruptedException.class)
//    @ResponseBody
//    public ErrorInfo handleBadRequest1(HttpServletRequest req, InterruptedException ex)  {
//    	
//        return new ErrorInfo(ex);
//    }
//	
//    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(ExecutionException.class)
//    @ResponseBody
//    public ErrorInfo handleBadRequest2(HttpServletRequest req, ExecutionException ex)  {
//    	
//        return new ErrorInfo(ex);
//    }
//	
//    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(TimeoutException.class)
//    @ResponseBody
//    public ErrorInfo handleBadRequest3(HttpServletRequest req, TimeoutException ex)  {
//    	
//        return new ErrorInfo(ex);
//    }
//	
//    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(IOException.class)
//    @ResponseBody
//    public ErrorInfo handleBadRequest4(HttpServletRequest req, IOException ex)  {
//    	
//        return new ErrorInfo(ex);
//    }
    
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorInfo handleBadRequest(HttpServletRequest req, Exception ex)  {

    	Logger log = LoggerFactory.getLogger(HomeController.class);

    	log.error("response error ", ex);
        return new ErrorInfo(ex);
    }
	
    
}
