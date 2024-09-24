package com.example.productservice.service.serviceImpl;

import com.example.productservice.dtos.CreateInventoryRequestDto;
import com.example.productservice.dtos.CreateProductRequestDto;
import com.example.productservice.dtos.ProductResponseDto;
import com.example.productservice.dtos.UpdateProductRequestDto;
import com.example.productservice.exception.CategoryNotFoundException;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.repository.InventoryRepository;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.InventoryService;
import com.example.productservice.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private InventoryService inventoryService;


    @Override
    public ProductResponseDto CreateProduct(CreateProductRequestDto createProductRequestDto) throws CategoryNotFoundException {
        Product product = CreateProductRequestDto.fromDto(createProductRequestDto);
        Category category = categoryRepository.findById(createProductRequestDto.getProductCategory()).orElseThrow(()-> new CategoryNotFoundException("Category not found"));
        product.setProductCategory(category);
        Product savedProduct = productRepository.save(product);

        //after creating the product create its inventory
        CreateInventoryRequestDto createInventoryRequestDto = new CreateInventoryRequestDto();
        createInventoryRequestDto.setProductId(savedProduct.getId());
        createInventoryRequestDto.setQuantity(createProductRequestDto.getInventory());
        inventoryService.incrementInventory(createInventoryRequestDto);

        return ProductResponseDto.from(savedProduct);
    }

    @Override
    public List<ProductResponseDto> GetAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductResponseDto::from).toList();
    }

    @Override
    public ProductResponseDto GetProductById(UUID id) throws ProductNotFoundException {
        return ProductResponseDto.from(
                productRepository.findById(id)
                        .orElseThrow(()-> new ProductNotFoundException("Product not found")));
    }

    @Override
    public ProductResponseDto UpdateProduct(UpdateProductRequestDto updateProductRequestDto, UUID productId) throws ProductNotFoundException, CategoryNotFoundException {
        productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Product not found"));
        Category category = categoryRepository.findById(updateProductRequestDto.getCategoryId()).orElseThrow(()-> new CategoryNotFoundException("Category not found"));
        Product product = UpdateProductRequestDto.fromDto(updateProductRequestDto);
        product.setProductCategory(category);
        return ProductResponseDto.from(productRepository.save(product));
    }
    @Transactional
    @Override
    public void deleteProduct(UUID id) {
        inventoryService.deleteInventory(id);
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponseDto> GetProductsByCategoryId(UUID id) {
        return productRepository.findAllByProductCategoryId(id).stream().map(ProductResponseDto::from).toList();
    }
}
