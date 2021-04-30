package haditya.kotlin.restful.controller

import haditya.kotlin.restful.error.NotFoundException
import haditya.kotlin.restful.error.UnauthorizedException
import haditya.kotlin.restful.model.WebResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(value=[ConstraintViolationException::class])
    fun validationHandler(constraintsViolationException: ConstraintViolationException):WebResponse<String>{
        return WebResponse(
            code = 400,
            status = "BAD REQUEST",
            data = constraintsViolationException.message!!
        )
    }

    @ExceptionHandler(value=[NotFoundException::class])
    fun notFoundHandler(notFoundException: NotFoundException):WebResponse<String>{
        return WebResponse(
            code = 404,
            status = "NOT FOUND",
            data= "Not Found"
        )
    }

    @ExceptionHandler(value=[UnauthorizedException::class])
    fun unauthorizedHandler(unauthorizedException: UnauthorizedException):WebResponse<String>{
        return  WebResponse(
            code = 401,
            status = "UNAUTHORIZED",
            data = "Please put your X-Api-Key"
        )
    }
}