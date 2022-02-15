package decimal.test.service;

import decimal.test.dto.TestDTO;


import java.util.List;

public interface TestService1 {

    List<TestDTO> getByIdOrName(TestDTO testDTO);
}
