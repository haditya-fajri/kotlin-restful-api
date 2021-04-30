package haditya.kotlin.restful.service.impl

import haditya.kotlin.restful.entity.Product
import haditya.kotlin.restful.error.NotFoundException
import haditya.kotlin.restful.model.CreateProductRequest
import haditya.kotlin.restful.model.ListProductRequest
import haditya.kotlin.restful.model.ProductResponse
import haditya.kotlin.restful.model.UpdateProductRequest
import haditya.kotlin.restful.repository.ProductRepository
import haditya.kotlin.restful.service.ProductService
import haditya.kotlin.restful.validation.ValidationUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors.toList

@Service
class ProductServiceImpl(
    val productRepository: ProductRepository,
    val validationUtil : ValidationUtil) : ProductService {

    override fun create(createProductRequest: CreateProductRequest): ProductResponse {
        validationUtil.validate(createProductRequest)

        val product = Product(
            id = createProductRequest.id!!,
            name = createProductRequest.name!!,
            price = createProductRequest.price!!,
            quantity = createProductRequest.quantity!!,
            createdAt = Date(),
            updatedAt = null
        )

        productRepository.save(product)

        return convertProductToProductResponse(product)
    }

    override fun get(id: String): ProductResponse {
        val product = productRepository.findByIdOrNull(id) ?: throw NotFoundException()

        return convertProductToProductResponse(product)

    }

    override fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse {
        validationUtil.validate(updateProductRequest)

        val product = productRepository.findByIdOrNull(id) ?: throw  NotFoundException()

        product.apply {
            name = updateProductRequest.name!!
            quantity = updateProductRequest.quantity!!
            price = updateProductRequest.price!!
            updatedAt = Date()
        }

        productRepository.save(product)

        return convertProductToProductResponse(product)
    }

    override fun delete(id: String) {
        val product = productRepository.findByIdOrNull(id) ?: throw  NotFoundException()

        productRepository.delete(product)
    }

    override fun list(listProductRequest: ListProductRequest): List<ProductResponse> {
        val page = productRepository.findAll(PageRequest.of(listProductRequest.page,listProductRequest.size))
        val products = page.get().collect(toList())
        return  products.map { convertProductToProductResponse(it) }

    }

    private fun convertProductToProductResponse(product:Product):ProductResponse{
        return ProductResponse(
            id = product.id,
            name = product.name,
            price = product.price,
            quantity = product.quantity,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt
        )
    }
}