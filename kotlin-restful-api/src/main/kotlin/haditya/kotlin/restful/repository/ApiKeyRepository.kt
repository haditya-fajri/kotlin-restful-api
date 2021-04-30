package haditya.kotlin.restful.repository

import haditya.kotlin.restful.entity.ApiKey
import org.springframework.data.jpa.repository.JpaRepository

interface  ApiKeyRepository:JpaRepository<ApiKey,String> {
}