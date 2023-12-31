package io.mountblue.blogApplication.repository;

import io.mountblue.blogApplication.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    @Query("SELECT DISTINCT p FROM Post p " +
            "LEFT JOIN p.tags t " +
            "WHERE " +
            "p.title LIKE CONCAT('%', :query, '%') OR " +
            "p.excerpt LIKE CONCAT('%', :query, '%') OR " +
            "p.author LIKE CONCAT('%', :query, '%') OR " +
            "p.content LIKE CONCAT('%', :query, '%') OR " +
            "t.name LIKE CONCAT('%', :query, '%')")
    Page<Post> searchPostByTagOrTitleOrAuthorOrContentOrExcerpt(@Param("query") String query,Pageable pageable);

    @Query("SELECT DISTINCT  p FROM Post p " +
            "LEFT JOIN  p.tags t ON " +
            "COALESCE(t.name, '') IN :tagNames "+
            "WHERE (:tagNames IS NULL OR t.name IN (:tagNames)) " +
            "AND (:authors IS NULL OR p.author IN (:authors))")
    Page<Post> findByTagNamesAndAuthors(@Param("tagNames") List<String> tagNames, @Param("authors") List<String> authors,Pageable pageable);






}
