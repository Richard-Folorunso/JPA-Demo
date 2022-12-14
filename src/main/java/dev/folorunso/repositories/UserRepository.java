package dev.folorunso.repositories;

import dev.folorunso.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Query(value="update users set data = :data where user_id = :id")
    void update(int id, String data);
}
