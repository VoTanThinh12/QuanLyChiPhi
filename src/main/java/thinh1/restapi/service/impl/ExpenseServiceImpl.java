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
import java.util.UUID;
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

//    @Override
//    public ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO) {
//        ExpenseEntity newExpenseEntity = maptoExpenseEntity(expenseDTO);
//        newExpenseEntity = setExpenseId(UUID.randomUUID().toString());
//        newExpenseEntity =  expenseReponsitory.save(newExpenseEntity);
//        return mapToExpenseDTO(newExpenseEntity);
//    }
    @Override
    public ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO) {
        ExpenseEntity newExpenseEntity = maptoExpenseEntity(expenseDTO);
        newExpenseEntity.setExpenseId(UUID.randomUUID().toString()); // Cách dùng đúng
        newExpenseEntity = expenseReponsitory.save(newExpenseEntity);
        log.info("Printing the new expense entity details {}",newExpenseEntity);
        return mapToExpenseDTO(newExpenseEntity);
    }

    @Override
    public ExpenseDTO updateExpenseDetails(ExpenseDTO expenseDTO, String expenseId) {
        ExpenseEntity existExpense = getExpenseEntity(expenseId);
        ExpenseEntity updateExpenseEntity = maptoExpenseEntity(expenseDTO);
        updateExpenseEntity.setId(existExpense.getId());
        updateExpenseEntity.setExpenseId(existExpense.getExpenseId());
        updateExpenseEntity.setCreatedAt(existExpense.getCreatedAt());
        updateExpenseEntity.setUpdatedAt(existExpense.getUpdatedAt());
        updateExpenseEntity =  expenseReponsitory.save(updateExpenseEntity);
        log.info("Printing the update expense dto entity details {}",updateExpenseEntity);
        return mapToExpenseDTO(updateExpenseEntity);
    }

    private ExpenseEntity maptoExpenseEntity(ExpenseDTO expenseDTO) {
        return modelMapper.map(expenseDTO, ExpenseEntity.class);
    }


    private ExpenseDTO mapToExpenseDTO(ExpenseEntity expenseEntity) {
        return modelMapper.map(expenseEntity, ExpenseDTO.class);
    }


}
