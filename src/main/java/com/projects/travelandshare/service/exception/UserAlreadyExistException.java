package com.projects.travelandshare.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason ="This user already have an account")
public class UserAlreadyExistException extends RuntimeException {
}
