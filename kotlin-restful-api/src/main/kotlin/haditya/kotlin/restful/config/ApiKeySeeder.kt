package haditya.kotlin.restful.config

import haditya.kotlin.restful.entity.ApiKey
import haditya.kotlin.restful.repository.ApiKeyRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class ApiKeySeeder(val apiKeyRepository:ApiKeyRepository): ApplicationRunner {

    val secret = "SECRET"

    override fun run(args: ApplicationArguments?) {
        if(!apiKeyRepository.existsById(secret)){
            val apiKey = ApiKey(secret)
            apiKeyRepository.save(apiKey)
        }
    }
}