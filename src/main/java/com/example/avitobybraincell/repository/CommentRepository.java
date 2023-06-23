package com.example.avitobybraincell.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.avitobybraincell.model.Comment;

import java.util.List;

@Repository

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByAdvert_Id(Integer advertId);
}
