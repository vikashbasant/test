package decimal.test.repository;

import decimal.test.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
public interface TestRepo extends JpaRepository<Test,Integer> {

    List<Test> findByName(String name);

    List<Test> findByAddress(String address);

    Test findByIdAndName(Integer id, String name);

    List<Test> findByNameAndAddress(String name, String address);

    Test findByIdAndAddress(Integer id, String address);

    @Transactional
    void deleteByName(String name);

    @Transactional
    void deleteByAddress(String address);

    @Transactional
    void deleteByIdAndName(Integer id, String name);

    @Transactional
    void deleteByIdAndAddress(Integer id, String address);

    @Transactional
    void deleteByNameAndAddress(String name, String address);

    @Transactional
    @Modifying
    @Query(value = "update Test u set u.address = ?2 where u.name = ?1")
    void updateName(String name, String address);
}
