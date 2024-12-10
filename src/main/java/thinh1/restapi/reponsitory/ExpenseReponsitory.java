package thinh1.restapi.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import thinh1.restapi.entity.ExpenseEntity;

public interface ExpenseReponsitory extends JpaRepository<ExpenseEntity,Long> {


}
