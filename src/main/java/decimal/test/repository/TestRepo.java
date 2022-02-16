package decimal.test.repository;

import decimal.test.domain.Test;
import decimal.test.dto.TestDTO;
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



    @Transactional
    @Modifying
    @Query(value = "update Test u set u.address = ?3, u.name = ?2 where u.id = ?1")
    void updateId(Integer id, String name, String address);


    @Transactional
    @Modifying
    @Query(value = "update Test u set u.name = ?1 where u.address = ?2")
    void updateAddress(String name, String address);

    @Transactional
    @Modifying
    @Query(value = "update Test u set u.address = ?3 where u.id = ?1 AND u.name = ?2")
    void updateByIdAndName(Integer id, String name, String address);


    @Transactional
    @Modifying
    @Query(value = "update Test u set u.name = ?2 where u.id = ?1 AND u.address = ?3")
    void updateByIdAndAddress(Integer id, String name, String address);


    @Transactional
    @Modifying
    @Query(value = "update Test u set u.name = ?1 where u.name = ?1 AND u.address = ?2")
    void updateByNameAndAddress(String name, String address);
}
