package pl.altkom.shop.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.altkom.shop.model.SaleDocument;

@Repository
public interface SaleDocumentRepo extends JpaRepository<SaleDocument, Long>, SaleDocumentRepoCustom {
	SaleDocument findByNumber(Long number);

	@Query("SELECT MAX(number) From SaleDocument")
	Optional<Long> getLastNumber();

}
