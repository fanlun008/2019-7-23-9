package com.thoughtworks.parking_lot.repo;

import com.thoughtworks.parking_lot.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TodoRepository extends JpaRepository<Todo, String> {

    @Modifying
    @Query("update Todo as todo set todo.title = :title, todo.complete = :complete where todo.id = :id")
    void updateTodoById(@Param("id") String id, @Param("title") String title, @Param("complete") boolean complete);

    Todo findByTitle(String title);

}
