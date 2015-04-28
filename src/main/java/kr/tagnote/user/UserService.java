package kr.tagnote.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	public boolean isExistsByUid(String uid) {
		return userRepository.isExistsByUid(uid);
	}
	
	public User.Response  findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		User.Response response = modelMapper.map(user, User.Response.class);
		return response;
	}
	
	public boolean saveUser(User.Request request){
		User user = modelMapper.map(request, User.class);
		boolean isSave = (userRepository.save(user) != null)? true : false;
		return isSave;
	}
	
	public boolean saveUser(User.Response response){
		User user = userRepository.findByEmail(response.getEmail());
		user.setUid(response.getUid());
		boolean isSave = (userRepository.save(user) != null)? true : false;
		return isSave;
	}
}
