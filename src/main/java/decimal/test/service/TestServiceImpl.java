package decimal.test.service;

import decimal.test.domain.Test;
import decimal.test.dto.ResponseDTO;
import decimal.test.dto.TestDTO;
import decimal.test.execption.GenralException;
import decimal.test.repository.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private TestRepo testRepo;

    @Override
    public void saveTestData(TestDTO testDTO) {
        Test test = new Test();
        test.setName(testDTO.getName());
        test.setAddress(testDTO.getAddress());
        testRepo.save(test);
    }

//    @Override
//    public TestDTO getById(Integer id) {
//        // optional class use for avoid for null:
//        Optional<Test> byId = testRepo.findById(id);
//        TestDTO testDTO =  new TestDTO();
//        byId.ifPresent( test -> {
//            testDTO.setId(test.getId());
//            testDTO.setName(test.getName());
//            testDTO.setAddress(test.getAddress());
//        });
//
//        return testDTO;
//    }

    @Override
    public ResponseDTO getById(Integer id) throws GenralException {
        // optional class use for avoid for null:
        Optional<Test> byId = testRepo.findById(id);
        TestDTO testDTO =  new TestDTO();

        if(!byId.isPresent()){
            throw new GenralException("TEST_500","user_does_not_exist",null);
        }
        byId.ifPresent( test -> {
            testDTO.setId(test.getId());
            testDTO.setName(test.getName());
            testDTO.setAddress(test.getAddress());
        });

        return new ResponseDTO("SUCCESS", "200", "user_details_fetch_successfully", testDTO);
    }

//    @Override
//    public List<TestDTO> getByName(String name) {
//        List<Test> testList =  testRepo.findByName(name);
//        List<TestDTO> testDTOList =  new ArrayList<>();
//        testList.forEach(test -> {
//            TestDTO testDTO = new TestDTO();
//            testDTO.setId(test.getId());
//            testDTO.setName(test.getName());
//            testDTO.setAddress(test.getAddress());
//            testDTOList.add(testDTO);
//        });
//        return testDTOList;
//    }



    @Override
    public ResponseDTO getByName(String name) throws GenralException {
        List<Test> testList =  testRepo.findByName(name);
        if(testList.isEmpty()){
            throw new GenralException("TEST_500", "name does not exist", null);
        }
        List<TestDTO> testDTOList =  new ArrayList<>();
        testList.forEach(test -> {
            TestDTO testDTO = new TestDTO();
            testDTO.setId(test.getId());
            testDTO.setName(test.getName());
            testDTO.setAddress(test.getAddress());
            testDTOList.add(testDTO);
        });
        return new ResponseDTO("SUCCESS", "200","name is successfully fetch", testDTOList);
    }

//    @Override
//    public List<TestDTO> getByAddress(String address) {
//        List<Test> testList = testRepo.findByAddress(address);
//        List<TestDTO> testDTOList = new ArrayList<>();
//        testList.forEach(test -> {
//            TestDTO testDTO = new TestDTO();
//            testDTO.setId(test.getId());
//            testDTO.setName(test.getName());
//            testDTO.setAddress(test.getAddress());
//            testDTOList.add(testDTO);
//        });
//        return testDTOList;
//    }


    @Override
    public ResponseDTO getByAddress(String address) {
        try{
            List<Test> testList = testRepo.findByAddress(address);
            if(testList.isEmpty()){
                throw new GenralException("TEST_500","address does not found", null);
            }
            List<TestDTO> testDTOList = new ArrayList<>();
            testList.forEach(test -> {
                TestDTO testDTO = new TestDTO();
                testDTO.setId(test.getId());
                testDTO.setName(test.getName());
                testDTO.setAddress(test.getAddress());
                testDTOList.add(testDTO);
            });
            return new ResponseDTO("SUCCESS", "200", "address is fecth from database", testDTOList);
        }catch (Exception excp){
            ResponseDTO responseDTO = null;
            if(excp instanceof GenralException){
                 responseDTO= new ResponseDTO("FAILURE", ((GenralException) excp).getStatusCode(), ((GenralException) excp).getMessage(), ((GenralException) excp).getErrorMessages());

            }
            return responseDTO;
        }
    }

    @Override
    public TestDTO getByIdAndName(Integer id, String name) {
        Test byId = testRepo.findByIdAndName(id, name);
        TestDTO testDTO = new TestDTO();
        if(byId != null){
            testDTO.setId(byId.getId());
            testDTO.setName(byId.getName());
            testDTO.setAddress(byId.getAddress());
        }
        return testDTO;
    }
    public TestDTO getByIdAndAddress(TestDTO testDTO) {
        Test byIdAndAddress = testRepo.findByIdAndAddress(testDTO.getId(), testDTO.getAddress());
        TestDTO testDto = new TestDTO();
        if(byIdAndAddress != null) {
            testDTO.setAddress(byIdAndAddress.getAddress());
            testDTO.setName(byIdAndAddress.getName());
            testDTO.setId(byIdAndAddress.getId());
        }
        return testDTO;
    }


    @Override
    public List<TestDTO> getByNameAndAddress(TestDTO testDTO) {
        List<Test> byNameAndAddress = (testRepo.findByNameAndAddress(testDTO.getName(), testDTO.getAddress()));
        List<TestDTO> lTestDto = new ArrayList<>();
        byNameAndAddress.forEach(ele->{
            TestDTO newTestDto = new TestDTO();
            newTestDto.setId(ele.getId());
            newTestDto.setName(ele.getName());
            newTestDto.setAddress(ele.getAddress());
            lTestDto.add(newTestDto);
        });
        return lTestDto;
    }



    @Override
    public void deleteById(Integer id) {
        Optional<Test> byId = testRepo.findById(id);
        byId.ifPresent(test -> {
            testRepo.deleteById(id);
        });


    }

    @Override
    public void deleteByName(String name) {
        List<Test> testList =  testRepo.findByName(name);
        if(testList != null){
            testRepo.deleteByName(name);
        }

    }

    @Override
    public void deleteByAddress(TestDTO testDTO) {
            List<Test> tList = testRepo.findByAddress(testDTO.getAddress());
            tList.forEach(ele -> testRepo.deleteByAddress(ele.getAddress()));
    }

    @Override
    public void deleteByIdAndName(TestDTO testDTO) {
        Test byIdAndName = testRepo.findByIdAndName(testDTO.getId(), testDTO.getName());
        if(byIdAndName != null){
            testRepo.deleteByIdAndName(testDTO.getId(), testDTO.getName());
        }
    }

    @Override
    public void deleteByIdAndAddress(TestDTO testDTO) {
        Test byIdAndAddress = testRepo.findByIdAndAddress(testDTO.getId(), testDTO.getAddress());
        if(byIdAndAddress != null){
            testRepo.deleteByIdAndAddress(testDTO.getId(), testDTO.getAddress());
        }
    }

    @Override
    public void deleteByNameAndAddress(TestDTO testDTO) {
        List<Test> LTest = testRepo.findByNameAndAddress(testDTO.getName(), testDTO.getAddress());
        LTest.forEach(ele -> {
            testRepo.deleteByNameAndAddress(ele.getName(), ele.getAddress());
        });
    }

    @Override
    public void updateByName(TestDTO testDTO) {
        testRepo.updateName(testDTO.getName(), testDTO.getAddress());
    }


}

