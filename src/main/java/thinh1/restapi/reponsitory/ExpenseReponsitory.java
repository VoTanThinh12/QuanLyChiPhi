package thinh1.restapi.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import thinh1.restapi.entity.ExpenseEntity;

import java.util.Optional;

public interface ExpenseReponsitory extends JpaRepository<ExpenseEntity,Long> {

    Optional<ExpenseEntity> findByExpenseId(String expenseId);



}
