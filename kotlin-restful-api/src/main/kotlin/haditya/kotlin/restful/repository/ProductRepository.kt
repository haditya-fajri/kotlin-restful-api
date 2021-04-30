package haditya.kotlin.restful.repository

import haditya.kotlin.restful.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository :JpaRepository<Product,String> {
}