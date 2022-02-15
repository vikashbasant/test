package decimal.test.controller;

import decimal.test.dto.TestDTO;
import decimal.test.service.TestService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController1 {
    @Autowired
    private TestService1 testService1;

    @PostMapping("getIdOrName")
    public List<TestDTO> getByIdOrName(@RequestBody TestDTO testDTO){
        return testService1.getByIdOrName(testDTO);
    }
}
