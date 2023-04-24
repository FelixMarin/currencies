package virtualcave.springbootexercice1.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import virtualcave.springbootexercice1.exercise.entity.Currencies;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CurrenciesRepository extends JpaRepository<Currencies, Long> {

    @Query("FROM Currencies t " +
            "WHERE t.startDate = :startDate " +
            "AND t.productId = :productId " +
            "AND t.brandId = :brandId")
    Optional<Currencies> findByDateProductIdBrandId(@NonNull @Param("startDate") LocalDate startDate,
                                                    @NonNull @Param("productId") Long productId,
                                                    @NonNull @Param("brandId") Long brandId);


}