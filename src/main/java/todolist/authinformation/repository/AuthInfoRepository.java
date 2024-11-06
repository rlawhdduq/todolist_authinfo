package todolist.authinformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import todolist.authinformation.domain.AuthInfo;

@Repository
public interface AuthInfoRepository extends JpaRepository<AuthInfo, Long>{
    
    @Query("Select Case When Count(a) > 0 Then true Else false End From AuthInfo as a Where a.user_id = :user_id")
    Boolean existsByUserId(@Param("user_id") Long user_id);

    @Modifying
    @Transactional
    @Query(value = "Delete From authinformation Where user_id = :user_id", nativeQuery = true)
    void deleteByUserId(@Param("user_id") Long user_id);
}
