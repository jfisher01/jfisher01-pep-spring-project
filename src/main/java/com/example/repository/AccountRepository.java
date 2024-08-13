package com.example.repository;
import java.io.Serializable;
import com.example.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository <Account,Integer>{



 */
 // @Query("")
 // List<Account> findByNameAndPassword(@Param("name") String name, @Param("password") String password);

   //@Query("");
  // List<Account> saveAccounts(@Param)


}
