package com.example.korea_sleepTech_springboot.시험.service;

import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.시험.dto.request.ProductCreateRequestDto;
import com.example.korea_sleepTech_springboot.시험.dto.request.ProductUpdateRequestDto;
import com.example.korea_sleepTech_springboot.시험.dto.response.ProductResponseDto;
import com.example.korea_sleepTech_springboot.시험.entity.Product;
import com.example.korea_sleepTech_springboot.시험.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ResponseDto<ProductResponseDto> createProcuct(ProductCreateRequestDto dto) {
        try {
            Product newProduct = new Product(
                    null,
                    dto.getName(),
                    dto.getDescription(),
                    dto.getPrice(),
                    null,
                    null
            );

            Product savedProduct = productRepository.save(newProduct);

            ProductResponseDto responseDto = new ProductResponseDto(
                    savedProduct.getName(),
                    savedProduct.getDescription(),
                    savedProduct.getPrice()
            );

            return ResponseDto.setSuccess("새로운 제품이 정상적으로 등록되었습니다.", responseDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("제품 등록 중 오류가 발생하였습니다.");
        }
    }

    public List<ProductResponseDto> getAllProducts() {
        List<ProductResponseDto> responseDtos = null;

        try {
            List<Product> products = productRepository.findAll();

            responseDtos = products.stream()
                    .map(product -> new ProductResponseDto(
                            product.getName(),
                            product.getDescription(),
                            product.getPrice()
                    ))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseDtos;
    }

    public ProductResponseDto getProductById(Long id) {
        ProductResponseDto responseDto = null;

        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 id의 제품을 찾을 수 없습니다." + id));

            responseDto = new ProductResponseDto(
                    product.getName(),
                    product.getDescription(),
                    product.getPrice()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseDto;
    }

    public ProductResponseDto updateProduct(Long id, ProductUpdateRequestDto dto) {
        ProductResponseDto responseDto = null;

        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 id의 제품을 찾을 수 없습니다." + id));

            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());
            product.setUpdatedAt(null);

            Product updateProduct = productRepository.save(product);

            responseDto = new ProductResponseDto(
                    updateProduct.getName(),
                    updateProduct.getDescription(),
                    updateProduct.getPrice()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseDto;
    }

    public void deleteProduct(Long id) {
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 id의 제품을 찾을 수 없습니다." + id));

            productRepository.delete(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
