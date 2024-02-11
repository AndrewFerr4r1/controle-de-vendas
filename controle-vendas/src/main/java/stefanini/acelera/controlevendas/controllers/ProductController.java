package stefanini.acelera.controlevendas.controllers;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stefanini.acelera.controlevendas.dto.RegisterAmountProductDto;
import stefanini.acelera.controlevendas.repository.ProductRepository;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/registerAmount/{code}")
    @Transactional
    public ResponseEntity<Void> registerAmountProduct(@PathVariable Integer code, @RequestBody RegisterAmountProductDto dto) {
        if (code != null && dto.getAmount() != null && dto.getDate() != null) {
            if (productRepository.findByCode(code).isPresent()) {
                productRepository.updateAmountProduct(dto.getAmount(), dto.getDate(), code);
                return ResponseEntity.status(201).build();
            }
        }
        return ResponseEntity.status(404).build();
    }
}
