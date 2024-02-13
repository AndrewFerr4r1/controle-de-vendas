package stefanini.acelera.controlevendas.controllers;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stefanini.acelera.controlevendas.dto.RegisterAmountProductDto;
import stefanini.acelera.controlevendas.repository.ProductRepository;
import stefanini.acelera.controlevendas.service.ProductService;
import stefanini.acelera.controlevendas.utils.ItemCsv;
import stefanini.acelera.controlevendas.utils.Utils;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/registerAmount/{code}")
    @Transactional
    public ResponseEntity<Void> registerAmountProduct(@PathVariable Integer code, @RequestBody RegisterAmountProductDto dto) {
        return productService.storeData(code, dto);

    }

    @PutMapping("/registerXlsx")
    public ResponseEntity<Void> registerXlsx() {
        List<ItemCsv> itemCsvs = Utils.leitorCsv();
        for (int i = 1; i <= itemCsvs.size(); i++) {
            Boolean isCode = productService.storeDataCsv(itemCsvs.get(i - 1));
            if (!isCode){
                return ResponseEntity.status(404).build();
            }
        }
        return ResponseEntity.status(204).build();
    }
}
