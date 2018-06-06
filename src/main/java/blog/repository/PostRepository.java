package blog.repository;

import blog.model.Post;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
@Repository
public interface PostRepository extends CrudRepository<Post, Integer>{

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="INSERT INTO post (id,body,date,title,user_username) VALUES (?1,?2,NOW(),?3,?4)")
    void addPostValues(Long id, String body, String title, String username);

    @Query(nativeQuery = true,value="select user_username from post where id=?1")
    String findUserByPostId(Long id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="update post set body=?1,title=?2,date=now() where id=?3")
    void editPostValues(String body,String title,Long id);

    @Query(nativeQuery = true,value="select id from post where id=?1")
    Long findPostById(Long id);

}
