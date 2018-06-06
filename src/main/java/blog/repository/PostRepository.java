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
    void addPostValues(int id, String body,String title,String username);

}