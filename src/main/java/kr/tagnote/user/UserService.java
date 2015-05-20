package kr.tagnote.user;

import java.util.HashMap;

import kr.tagnote.user.User.Facebook;
import kr.tagnote.user.User.Google;
import kr.tagnote.util.HttpUtils;
import kr.tagnote.util.JacksonUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestTemplate restTemplate;

	public boolean isExistsByEmail(String email){
		Boolean isExists = userRepository.isExistsByEmail(email);
		return (isExists == null)? false : isExists;
	}
	
	public boolean isExistsByUid(String uid) {
		Boolean isExists = userRepository.isExistsByUid(uid);
		return (isExists == null) ? false : isExists;
	}

	public User findById(Long userId) {
		return userRepository.findOne(userId);
	}
	
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Transactional
	public User saveUser(User user) {
		user = userRepository.save(user);
		return user;
	}

	/*
	 * public User saveUser(User.Response response){ User user =
	 * userRepository.findByEmail(response.getEmail());
	 * user.setUid(response.getUid()); user = userRepository.save(user); return
	 * modelMapper.map(user, User.Response.class); }
	 */

	public boolean deleteByEmail(String email) {
		boolean isDelete = (userRepository.deleteByEmail(email) > 0) ? true : false;
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

	// social
	public User findOrSaveByAccessTokenAndType(String accessToken, String type) {
		String json = null;
		String url = null;
		String email = null;
		User user = null;
		Facebook facebook = null;
		Google google = null;
		HashMap<String, String> params = new HashMap<String, String>();
		
		if (type.equals("facebook")) {
			url = User.Facebook.REQUEST_ME;
			params.put("fields", "name,email");
			params.put("access_token", accessToken);

			json = HttpUtils.getJson(restTemplate, url, params);

			facebook = JacksonUtils.jsonToObject(json, User.Facebook.class);
			email = facebook.email;
		} else if (type.equals("google")) {
			url = User.Google.REQUEST_ME;
			params.put("access_token", accessToken);

			json = HttpUtils.getJson(restTemplate, url, params);
			
			google = JacksonUtils.jsonToObject(json, User.Google.class);
			email = google.emails.get(0).get("value");
		}
		
		user = findByEmail(email);
		if (user == null) {
			user = new User(email, null);
			user.setAuth(new Auth() {
				{
					this.setAuthId(Auth.Role.USER.ordinal());
				}
			});
		}
		
		user.setAccessToken(accessToken);
		user.setType(type);
		user = saveUser(user);
		return user;
	}
}
