package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.imf.alumnos.daw.tfg.alexdiaz.towatchback.model.UserLogs;

public interface UserLogsRepository extends JpaRepository<UserLogs, Long> {
    // @Query(name = "SELECT * FROM db_to_watch.users_logs WHERE user_id = :userId ORDER BY creation_date DESC", nativeQuery = true)
    // UserLogs getAllUserLogsById(@Param("userId") Long userId);
}
