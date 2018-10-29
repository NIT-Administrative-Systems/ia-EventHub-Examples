package edu.northwestern.amq.test;

import org.junit.Assert;
import org.junit.Test;

import edu.northwestern.amq.AMQConsumer;
import edu.northwestern.amq.AcknowledgeResult;
import edu.northwestern.amq.MessageResult;

public class AMQConsumerTest {

	private AMQConsumer amqConsumer = null;
	
	private void createConsumer() {
		if(amqConsumer == null) {
			String topic = System.getProperty("edu.northwestern.topic");
			String apiKey = System.getProperty("edu.northwestern.apikey");
			String env = System.getProperty("edu.northwestern.env");
			
			amqConsumer = AMQConsumer.ConsumerBuilder
					.create()
					.setEnv(env)
					.setTopic(topic)
					.setAPIKey(apiKey)
					.build();
		}
	}

	@Test
	public void getMessage() {
		try {
			createConsumer();

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
	public void deleteMessage() throws Exception {
		createConsumer();

		amqConsumer.acknowledgeMessage();
	}
}