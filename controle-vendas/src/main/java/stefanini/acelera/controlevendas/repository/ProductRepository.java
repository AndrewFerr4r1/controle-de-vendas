package stefanini.acelera.controlevendas.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import stefanini.acelera.controlevendas.domain.Product;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE Product set amount = :amount , date = :date WHERE code = :code ", nativeQuery = true)
    Integer updateAmountProduct(Integer amount, String date, Integer code);

    Optional<Product> findByCode(Integer code);


}
