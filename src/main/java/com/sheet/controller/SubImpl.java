package com.sheet.controller;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class SubImpl {
//	Logger LOGGER = Logger.getLogger("SUBIMPL");
	/*@Autowired
	SheetControllerRedis  mSheetControllerRedis;*/
	private CountDownLatch latch;

	@Autowired
	public SubImpl(CountDownLatch latch) {
		this.latch = latch;
	}

	/*
	 * public void receiveMessage(String message) {
	 * System.out.println("Reciver Method...."); LOGGER.info("Received <" +
	 * message + ">"); latch.countDown(); for (int i = 0; i < 10; i++) {
	 * System.out.println(i); } System.out.println("Game Info updated");
	 * 
	 * }
	 */
	public void receiveMessage(String message) {
		System.out.println("Reciver Method....");
	//	LOGGER.info("Received <" + message + ">");
		SheetControllerRedis scr=new SheetControllerRedis();
		scr.updateDatabse();
		System.out.println("Game Info updated");
		latch.countDown();
	}
}
