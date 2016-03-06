package cn.zsy.webim.action;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class InitServer {

	@PostConstruct
	public void intiNettyServer() {
		Thread th = new Thread(new WebImServer(7397), "websokserver");
		th.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
