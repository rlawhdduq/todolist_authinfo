package todolist.authinformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import todolist.authinformation.domain.AuthInfo;

@Repository
public interface AuthInfoRepository extends JpaRepository<AuthInfo, Long>{
    
}
