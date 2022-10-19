package org.BinarAcademy.Challenge_4.repository.user;

import org.BinarAcademy.Challenge_4.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.nama= :nama")
    User findUserByNama(String nama);

    @Query("SELECT f FROM User f WHERE f.id= :id")
    User findUserById(int id);

    @Query("SELECT u FROM User u WHERE u.email= :email")
    User findUserByEmail(String email);


}
