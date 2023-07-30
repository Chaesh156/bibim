package com.chaesh.Domain.likes;

import com.chaesh.Domain.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes,Long> {
    @Query("SELECT l FROM Likes l WHERE l.id = :id ORDER BY l.createdDate DESC")
    List<Member> findAllMember(@Param("id")Long id);

    Optional<Likes> findByMemberAndPostsId(Long memberID, Long postsId);
}
