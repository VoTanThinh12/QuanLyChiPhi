package thinh1.restapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import thinh1.restapi.dto.ExpenseDTO;
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








    /*
     *phuong thuc mapper de chuyen expense dto sang expense response
     */

    private ExpenseResponse mapToExpenseResponse(ExpenseDTO expenseDTO) {
        return modelMapper.map(expenseDTO, ExpenseResponse.class);
    }
}
