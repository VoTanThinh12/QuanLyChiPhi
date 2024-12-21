package thinh1.restapi.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import thinh1.restapi.dto.ExpenseDTO;
import thinh1.restapi.io.ExpenseRequest;
import thinh1.restapi.io.ExpenseResponse;
import thinh1.restapi.reponsitory.ExpenseReponsitory;
import thinh1.restapi.service.ExpenseService;
import java.util.List;
import java.util.stream.Collectors;
/*
*đây là phần Controller cho module expense
*@author Tan Thinh
* */
@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")


public class ExpenseController {


    private final ExpenseService expenseService;
    private final ModelMapper modelMapper;
    /*
     * nó sẽ fetch  expense từ db
     * return list
     */

    @GetMapping("/expenses")
    public List<ExpenseResponse> getExpenses() {
        log.info("API GET /expenses called");
        //gọi method service
        List<ExpenseDTO> list = expenseService.getAllExpenses();
        log.info("Printing the data from service {}", list);
        //chuyen ExpenseDTO sang ExpenseResponse
        List<ExpenseResponse> responses =  list.stream().map(expenseDTO -> mapToExpenseResponse(expenseDTO)).collect(Collectors.toList());
        //tra ve list
        return responses;

    }


    @GetMapping("/expenses/{expenseId}")
    public ExpenseResponse getExpenseById(@PathVariable String expenseId) {
        log.info("API GET /expenses/{expenseId} called");
        ExpenseDTO expenseDTO = expenseService.getExpenseByExpenseId(expenseId);
        return mapToExpenseResponse(expenseDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses/{expenseId}")
    public void deleteExpenseById(@PathVariable String expenseId) {
        log.info("API DELETE /expenses/{expenseId} called");
        expenseService.deleteExpenseByExpenseID(expenseId);
    }

    /*
     *phuong thuc mapper de chuyen expense dto sang expense response
//     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/expenses")
    public ExpenseResponse saveExpenseDetails(@Valid @RequestBody ExpenseRequest expenseRequest){
        log.info("API POST /expenses called{}",expenseRequest);
        ExpenseDTO expenseDTO = mapToExpenseDTO(expenseRequest);
        expenseDTO = expenseService.saveExpenseDetails(expenseDTO);
        log.info("Printing the expense dto {}", expenseDTO);
        return mapToExpenseResponse(expenseDTO);
    }

    private ExpenseDTO mapToExpenseDTO( ExpenseRequest expenseRequest) {
        return modelMapper.map(expenseRequest, ExpenseDTO.class);
    }


    private ExpenseResponse mapToExpenseResponse(ExpenseDTO expenseDTO) {
        return modelMapper.map(expenseDTO, ExpenseResponse.class);
    }

}
