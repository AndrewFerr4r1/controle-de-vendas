package stefanini.acelera.controlevendas.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import stefanini.acelera.controlevendas.dto.RegisterAmountProductDto;
import stefanini.acelera.controlevendas.repository.ProductRepository;
import stefanini.acelera.controlevendas.utils.ItemCsv;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Transactional
    public Boolean storeDataCsv(ItemCsv itemCsv) {
        if (itemCsv.getCpf() != null && itemCsv.getAmount() != null && itemCsv.getDate() != null && itemCsv.getCode() != null) {
            Integer code = Integer.parseInt(itemCsv.getCode());
            if (productRepository.findByCode(code).isPresent()) {
                Integer amount = Integer.parseInt(itemCsv.getAmount());
                productRepository.updateAmountProduct(amount, itemCsv.getDate(), code);
                return true;
            }
        }
        return false;
    }


    public ResponseEntity<Void> storeData(Integer code, RegisterAmountProductDto dto) {
        if (code != null && dto.getAmount() != null && dto.getDate() != null) {
            if (productRepository.findByCode(code).isPresent()) {
                productRepository.updateAmountProduct(dto.getAmount(), dto.getDate(), code);
                return ResponseEntity.status(201).build();
            }
        }
        return ResponseEntity.status(404).build();
    }
}
