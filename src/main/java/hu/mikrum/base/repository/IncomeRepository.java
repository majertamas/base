package hu.mikrum.base.repository;

import hu.mikrum.base.model.entity.Income;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends CrudRepository<Income, Long> {
}
