package com.jam2in.restapi;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class HelloArcusTest {

	HelloArcus helloArcus = new HelloArcus("192.168.0.155:2181", "test");
	
	@Before
	public void sayHello() {
		helloArcus.sayHello();
	}
	
	@Test
	public void listenHello() {
		Assert.assertEquals("Hello, Arcus!", helloArcus.listenHello());
	}
	
}