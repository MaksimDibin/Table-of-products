package ru.dibin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dibin.persist.Product;
import ru.dibin.persist.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductRepr> findAll() {
        return productRepository
                .findAll()
                .stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductRepr> findById(Long id) {
        return productRepository
                .findById(id)
                .map(ProductRepr::new);
    }

    @Transactional
    @Override
    public void save(ProductRepr productRepr) {
        Product productToSave = new Product(productRepr);
        productRepository.save(productToSave);

        if (productRepr.getId() != null){
            productRepr.setId(productToSave.getId());
        }
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
