package kr.tagnote;

import kr.tagnote.util.CommonUtils;
import kr.tagnote.util.HttpUtils;

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
	public void httpJson(){
		RestTemplate rest = new RestTemplate();
		
		byte[] bytes = rest.getForObject("http://res.cloudinary.com/one-person/image/upload/v1433272795/irqr1j8xfqd9umyc6pov.png", byte[].class);
		System.out.println(bytes.length);
	}
}
