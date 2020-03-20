package com.projects.travelandshare.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason ="This place already exists")
public class ConflictException extends RuntimeException{
}
