package haditya.kotlin.restful.service

import haditya.kotlin.restful.model.CreateProductRequest
import haditya.kotlin.restful.model.ListProductRequest
import haditya.kotlin.restful.model.ProductResponse
import haditya.kotlin.restful.model.UpdateProductRequest

interface ProductService {

    fun create(createProductRequest: CreateProductRequest): ProductResponse

    fun get(id:String):ProductResponse

    fun update(id:String,updateProductRequest: UpdateProductRequest):ProductResponse

    fun delete(id:String)

    fun list(listProductRequest: ListProductRequest):List<ProductResponse>
}