package kr.tagnote.user;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String email);
	@Transactional
	public Long deleteByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.uid = :uid")
	public boolean isExistsByUid(String uid);
}
