package thinh1.restapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import thinh1.restapi.dto.ExpenseDTO;
import thinh1.restapi.entity.ExpenseEntity;
import thinh1.restapi.exceptions.ResourceNotFoundException;
import thinh1.restapi.reponsitory.ExpenseReponsitory;
import thinh1.restapi.service.ExpenseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseReponsitory expenseReponsitory;
    private final ModelMapper modelMapper;
    @Override
    public List<ExpenseDTO> getAllExpenses() {
        //call the reponsitory method
        List<ExpenseEntity> list = expenseReponsitory.findAll();
        log.info("Printing the data from reponsitory {}",list);
        //covert the Entity object to DTO object
        List<ExpenseDTO> listOfExpenses =  list.stream().map(expenseEntity -> mapToExpenseDTO(expenseEntity)).collect(Collectors.toList());
        //return the list
        return listOfExpenses;
    }

    @Override
    public ExpenseDTO getExpenseByExpenseId(String expenseId) {
        ExpenseEntity expenseEntity = getExpenseEntity(expenseId);
        return mapToExpenseDTO(expenseEntity);
    }

    private ExpenseEntity getExpenseEntity(String expenseId) {
         return  expenseReponsitory.findByExpenseId(expenseId)
        .orElseThrow(() -> new RuntimeException("Expense not found for the expense id " + expenseId));

    }

    @Override
    public void deleteExpenseByExpenseID(String expenseId) {
        ExpenseEntity expenseEntity =  getExpenseEntity(expenseId);
        log.info("Deleting expense {}",expenseEntity);
        expenseReponsitory.delete(expenseEntity);
    }


    private ExpenseDTO mapToExpenseDTO(ExpenseEntity expenseEntity) {
        return modelMapper.map(expenseEntity, ExpenseDTO.class);
    }


}
