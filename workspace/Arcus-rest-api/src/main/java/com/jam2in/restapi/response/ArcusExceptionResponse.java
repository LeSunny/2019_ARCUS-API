package com.jam2in.restapi.response;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.spy.memcached.compat.log.Logger;

@ControllerAdvice
public class ArcusExceptionResponse {
	/*InterruptedException
	ExecutionException
	TimeoutException
	IOException
	*/
	 
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TimeoutException.class)
    @ResponseBody
    public ErrorInfo handleBadRequest(HttpServletRequest req, Exception ex) {
    	
        return new ErrorInfo(req.getRequestURL().toString(), ex);
    }

	
}
