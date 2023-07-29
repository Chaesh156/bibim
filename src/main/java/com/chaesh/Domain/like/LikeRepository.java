package com.chaesh.Domain.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    @Query("SELECT p FROM Like p ORDER BY p.likeDate DESC")
    List<Like> findAllMember();
}
