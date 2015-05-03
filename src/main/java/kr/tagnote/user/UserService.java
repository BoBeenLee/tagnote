package kr.tagnote.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public boolean isExistsByUid(String uid) {
		Boolean isExists = userRepository.isExistsByUid(uid);
		return (isExists == null)? false : isExists;
	}
	
	public User  findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}
	
	public User saveUser(User user){
		user = userRepository.save(user);
		return user;
	}
	
/*	public User saveUser(User.Response response){
		User user = userRepository.findByEmail(response.getEmail());
		user.setUid(response.getUid());
		user = userRepository.save(user);
		return modelMapper.map(user, User.Response.class);
	}*/
	
	public boolean deleteByEmail(String email){
		boolean isDelete = (userRepository.deleteByEmail(email) > 0)? true : false;
		return isDelete;
	}

	public User findByUid(String uid) {
		User user = userRepository.findByUid(uid);
		return user;
	}

	public User findByEmailAndUid(String email, String uid) {
		User user = userRepository.findByEmailAndUid(email, uid);
		return user;
	}
}
