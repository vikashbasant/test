package decimal.test.service;

import decimal.test.dto.TestDTO;

import java.util.List;

public interface TestService {

    void saveTestData(TestDTO testDTO);

    TestDTO getById(Integer id);

    List<TestDTO> getByName(String name);

    List<TestDTO> getByAddress(String address);

    TestDTO getByIdAndName(Integer id, String name);

    List<TestDTO> getByNameAndAddress(TestDTO testDTO);

    void deleteById(Integer id);

    void deleteByName(String name);

    void deleteByAddress(TestDTO testDTO);


    TestDTO getByIdAndAddress(TestDTO testDTO);

    void deleteByIdAndName(TestDTO testDTO);

    void deleteByIdAndAddress(TestDTO testDTO);

    void deleteByNameAndAddress(TestDTO testDTO);

    void updateByName(TestDTO testDTO);
}
