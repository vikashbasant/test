package decimal.test.controller;

import decimal.test.dto.ResponseDTO;
import decimal.test.dto.TestDTO;
import decimal.test.execption.GenralException;
import decimal.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestService testService;


    @PostMapping(value = "save")
    public String saveTestData(@RequestBody TestDTO testDTO)
    {
        testService.saveTestData(testDTO);
        return "SUCCESS";
    }


//    @PostMapping(value = "getById")
//    public TestDTO getById(@RequestBody TestDTO testDTO)
//    {
//        return testService.getById(testDTO.getId());
//    }

    // getById using Exception:
    @PostMapping(value = "getById")
    public ResponseEntity getById(@RequestBody TestDTO testDTO) throws GenralException {
        ResponseDTO responseDTO = testService.getById(testDTO.getId());
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }


//    @PostMapping(value = "getByName")
//    public List<TestDTO> getByName(@RequestBody TestDTO testDTO)
//    {
//        return testService.getByName(testDTO.getName());
//    }

    // getByName using Exception:
    @PostMapping(value = "getByName")
    public ResponseEntity getByName(@RequestBody TestDTO testDTO) throws GenralException {
        ResponseDTO responseDTO = testService.getByName(testDTO.getName());
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @PostMapping(value = "getByAddress")
    public List<TestDTO> getByAddress(@RequestBody TestDTO testDTO)
    {
        return testService.getByAddress(testDTO.getAddress());
    }

    @PostMapping(value = "getByIdAndName")
    public TestDTO getByIdAndName(@RequestBody TestDTO testDTO){
        return testService.getByIdAndName(testDTO.getId(), testDTO.getName());
    }


    @PostMapping(value = "getByNameAndAddress")
    public List<TestDTO> getByNameAndAddress(@RequestBody TestDTO testDTO){
        return testService.getByNameAndAddress(testDTO);
    }
    @PostMapping("getByIdAndAddress")
    public TestDTO getByIdAndAddress(@RequestBody TestDTO testDTO){
        return testService.getByIdAndAddress(testDTO);
    }

    @PostMapping(value = "deleteById")
    public String deletedById(@RequestBody TestDTO testDTO){
        testService.deleteById(testDTO.getId());
        return "SUCCESS";
    }

    @PostMapping(value = "deleteByName")
    public String deletedByName(@RequestBody TestDTO testDTO){
        testService.deleteByName(testDTO.getName());
        return "SUCCESS";
    }

    @PostMapping(value = "deleteByAddress")
    public String deleteByAddress(@RequestBody TestDTO testDTO){
        testService.deleteByAddress(testDTO);
        return "SUCCESS";
    }

    @PostMapping("deleteByIdAndName")
    public String deleteByIdAndName(@RequestBody TestDTO testDTO){
        testService.deleteByIdAndName(testDTO);
        return "SUCCESS";
    }

    @PostMapping("deleteByIdAndAddress")
    public String deleteByIdAndAddress(@RequestBody TestDTO testDTO){
        testService.deleteByIdAndAddress(testDTO);
        return "SUCCESS";
    }


    @PostMapping("deleteByNameAndAddress")
    public String deleteByNameAndAddress(@RequestBody TestDTO testDTO){
        testService.deleteByNameAndAddress(testDTO);
        return "SUCCESS";
    }


    @PostMapping("update")
    public String updateDB(@RequestBody  TestDTO testDTO){
        testService.updateByName(testDTO);
        return "Database updated";
    }

}
