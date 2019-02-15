package com.jam2in.httpapi;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import net.spy.memcached.ArcusClient;
import net.spy.memcached.ConnectionFactoryBuilder;

public class HelloArcus {

	private String arcusAdmin;
	private String serviceCode;
	private ArcusClient arcusClient;

	public HelloArcus(String arcusAdmin, String serviceCode) {
		this.arcusAdmin = arcusAdmin;
		this.serviceCode = serviceCode;
		
		// log4j logger�� ����ϵ��� �����մϴ�.
		// �ڵ忡 ���� �߰����� �ʰ� �Ʒ��� JVM ȯ�溯���� ����ص� �˴ϴ�.
		//   -Dnet.spy.log.LoggerImpl=net.spy.memcached.compat.log.Log4JLogger
		System.setProperty("net.spy.log.LoggerImpl", "net.spy.memcached.compat.log.Log4JLogger");

		// Arcus Ŭ���̾�Ʈ ��ü�� �����մϴ�.
		// - arcusAdmin : Arcus ĳ�� �������� �׷��� �����ϴ� admin ����(ZooKeeper)�� �ּ��Դϴ�.
		// - serviceCode : ����ڿ��� �Ҵ�� Arcus ĳ�� �������� ���տ� ���� �ڵ尪�Դϴ�. 
		// - connectionFactoryBuilder : Ŭ���̾�Ʈ ���� �ɼ��� ������ �� �ֽ��ϴ�.
		//
		// �����ϸ� arcusAdmin�� serviceCode�� ������ ���� ������ ĳ�� �������� ������ ��� ������ �� �ִ� ���Դϴ�.
		this.arcusClient = ArcusClient.createArcusClient(arcusAdmin, serviceCode, new ConnectionFactoryBuilder());
	}

	public boolean sayHello() {
		//Exception e;
		//e.
		Future<Boolean> future = null;
		boolean setSuccess = false;

		// Arcus�� "test:hello" Ű�� "Hello, Arcus!"��� ���� �����մϴ�.
		// �׸��� Arcus�� ���� ��� API�� Future�� �����ϵ��� �Ǿ� �����Ƿ�
		// �񵿱� ó���� Ưȭ�� ������ �ƴ϶�� �ݵ�� ���������� future.get()�� �����Ͽ�
		// ��ȯ�Ǵ� ������ ��ٷ��� �մϴ�.
		future = this.arcusClient.set("test:hello", 600, "Hello, Arcus!");
		
		try {
			setSuccess = future.get(700L, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			if (future != null) future.cancel(true);
			e.printStackTrace();
		}
		
		return setSuccess;
	}
	
	public String listenHello() {
		Future<Object> future = null;
		String result = "Not OK.";
		
		// Arcus�� "test:hello" Ű�� ���� ��ȸ�մϴ�.
		// Arcus������ ������ ��� ���ɿ� ���������� timeout ���� �����ϵ��� ���̵� �ϰ� ������
		// ����ڴ� set�� ������ ��� ��û�� async�� �����ϴ� API�� ����ϼž� �մϴ�.
		future = this.arcusClient.asyncGet("test:hello");
		
		try {
			result = (String)future.get(700L, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			if (future != null) future.cancel(true);
			e.printStackTrace();
		}
		
		return result;
	}

}