package edu.northwestern.amqexamples.test;

import org.junit.Assert;
import org.junit.Test;


public class WebhookTest {

	@Test
	public void putMessage() {
		try {

		}
		catch(Exception e) {
			System.out.println("Error");
			e.printStackTrace(System.out);
			Assert.fail(e.getMessage());
		}
	}
}