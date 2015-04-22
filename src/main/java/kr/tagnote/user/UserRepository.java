package kr.tagnote.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	// find
	public User findByEmail(String email);

	// delete
	public Long deleteByEmail(String email);
}
