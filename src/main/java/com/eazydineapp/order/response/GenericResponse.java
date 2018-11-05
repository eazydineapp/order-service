package com.eazydineapp.order.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class GenericResponse implements Serializable {

	private static final long serialVersionUID = -7707708104985723309L;
	private String message;
	private HttpStatus status;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
