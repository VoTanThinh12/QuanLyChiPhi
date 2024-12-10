package thinh1.restapi.service;

import thinh1.restapi.dto.ExpenseDTO;

import java.util.List;

public interface ExpenseService {
    List<ExpenseDTO> getAllExpenses();
}
