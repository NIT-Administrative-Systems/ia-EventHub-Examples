package edu.northwestern.amq.test;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import edu.northwestern.amq.AMQConsumer;
import edu.northwestern.amq.AcknowledgeResult;
import edu.northwestern.amq.MessageResult;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AMQConsumerTest {

	private static AMQConsumer amqConsumer = null;

	@Test
	public void aagetMessage() {
		try {
			String topic = System.getProperty("edu.northwestern.topic");
			String apiKey = System.getProperty("edu.northwestern.apikey");
			String env = System.getProperty("edu.northwestern.env");
			
			amqConsumer = AMQConsumer.ConsumerBuilder
					.create()
					.setEnv(env)
					.setTopic(topic)
					.setAPIKey(apiKey)
					.build();
			MessageResult messageResult = amqConsumer.getMessage();
			
			Assert.assertNotNull("MessageResult should not be null", messageResult);

			AcknowledgeResult ackResult = amqConsumer.acknowledgeMessage();

			Assert.assertTrue("Message was not Acknowledged", ackResult.isSuccess());
		}
		catch(Exception e) {
			System.out.println("Error");
		}
	}
	
	@Test(expected = RuntimeException.class)
	public void acknowledgeMessage() throws Exception {
		amqConsumer.acknowledgeMessage();
	}
}