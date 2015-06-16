package kr.tagnote;

import java.util.List;

import kr.tagnote.util.CommonUtils;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class CommonTests {
//	@Test
	public void getURLToBytes(){
		byte[] bytes = CommonUtils.getURLToBytes("http://res.cloudinary.com/one-person/image/upload/v1433272795/irqr1j8xfqd9umyc6pov.png", 95374);
		/*for(int i=0; i<bytes.length; i++)
			System.out.print(bytes[i] + " ");*/
	}
	
	@Test
	@Ignore
	public void httpJson(){
		RestTemplate rest = new RestTemplate();
		
		byte[] bytes = rest.getForObject("http://res.cloudinary.com/one-person/image/upload/v1433272795/irqr1j8xfqd9umyc6pov.png", byte[].class);
		System.out.println(bytes.length);
	}
	
	@Test
	public void commonTests(){
		List files = CommonUtils.convertStrToList("[]");
		System.out.println(files.size());
	}
}
