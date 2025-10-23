package com.example.product.service;
import com.example.product.dto.ProductDTO;
import com.example.product.entity.Product;
import com.example.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<ProductDTO> getAll() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public ProductDTO getById(Long id) {
        return repo.findById(id).map(this::toDto).orElse(null);
    }

    public ProductDTO create(ProductDTO dto) {
        Product p = new Product();
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());
        return toDto(repo.save(p));
    }

    public ProductDTO update(Long id, ProductDTO dto) {
        return repo.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setDescription(dto.getDescription());
            existing.setPrice(dto.getPrice());
            return toDto(repo.save(existing));
        }).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    private ProductDTO toDto(Product p) {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(p.getId());
        productDTO.setName(p.getName());
        productDTO.setDescription(p.getDescription());
        productDTO.setPrice(p.getPrice());

        return productDTO;
    }

}
