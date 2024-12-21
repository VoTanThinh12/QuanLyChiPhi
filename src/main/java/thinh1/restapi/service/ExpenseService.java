package thinh1.restapi.service;

import thinh1.restapi.dto.ExpenseDTO;

import java.util.List;

public interface ExpenseService {
    List<ExpenseDTO> getAllExpenses();
    ExpenseDTO getExpenseByExpenseId(String expenseId);
    void deleteExpenseByExpenseID(String expenseId);

    ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO);

    ExpenseDTO updateExpenseDetails(ExpenseDTO expenseDTO, String expenseId);
}
