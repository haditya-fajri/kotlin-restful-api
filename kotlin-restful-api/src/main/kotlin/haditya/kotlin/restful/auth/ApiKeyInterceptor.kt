package haditya.kotlin.restful.auth

import haditya.kotlin.restful.error.UnauthorizedException
import haditya.kotlin.restful.repository.ApiKeyRepository
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import java.lang.Exception

@Component
class ApiKeyInterceptor(val apiKeyRepository: ApiKeyRepository):WebRequestInterceptor {
    override fun preHandle(request: WebRequest) {
        val apiKey = request.getHeader("X-Api-Key") ?: throw UnauthorizedException()

        if (!apiKeyRepository.existsById(apiKey)) throw  UnauthorizedException()


    }

    override fun postHandle(request: WebRequest, model: ModelMap?) {
        //Nothing
    }

    override fun afterCompletion(request: WebRequest, ex: Exception?) {
        //Nothing
    }
}