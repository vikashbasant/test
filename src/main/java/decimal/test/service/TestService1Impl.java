package decimal.test.service;

import decimal.test.domain.Test;
import decimal.test.dto.TestDTO;
import decimal.test.repository.TestRepo1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TestService1Impl implements TestService1{
    @Autowired
    private TestRepo1 testRepo1;

    @Override
    public List<TestDTO> getByIdOrName(TestDTO testDTO) {
        List<Test> lTest = testRepo1.findByIdOrName(testDTO.getId(), testDTO.getName());
        List<TestDTO> lList = new ArrayList<>();
        lTest.forEach(ele->{
            TestDTO test = new TestDTO();
            test.setId(ele.getId());
            test.setName(ele.getName());
            test.setAddress(ele.getAddress());
            lList.add(test);
        });

        return lList;
    }
}
