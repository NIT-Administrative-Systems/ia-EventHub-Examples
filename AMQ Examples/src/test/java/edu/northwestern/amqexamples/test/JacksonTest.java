package edu.northwestern.amqexamples.test;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.northwestern.amqexamples.Message;

public class JacksonTest {

	@Test
	public void marshallJSON() {
		try {
			String expectedJSON = "{\"data\":\"{ \\\"name\\\" : \\\"Brent\\\", \\\"greeting\\\" : \\\"Hello\\\" } \",\"deliveryDate\":null,\"expirationDate\":\"2018-12-20T09:19:20.243\",\"deliveryAttempts\":0,\"contentType\":\"application/json\",\"messageId\":\"1\"}";

			Message message = new Message();
			ObjectMapper mapper = new ObjectMapper();

			message.setData("{ \"name\" : \"Brent\", \"greeting\" : \"Hello\" } ");
			message.setContentType("application/json");
			message.setDeliveryAttempts(0);
			message.setExpirationDate("2018-12-20T09:19:20.243");
			message.setMessageId("1");

			String jsonString = mapper.writeValueAsString(message);

			Assert.assertEquals(expectedJSON, jsonString);

			//Usees the ObjectMapper to unmarshall a JSON string to an Object
			Message newMessage = mapper.readValue(jsonString, Message.class);

			Assert.assertEquals(message.getData(), newMessage.getData());
			Assert.assertEquals(message.getContentType(), newMessage.getContentType());
			Assert.assertEquals(message.getDeliveryAttempts(), newMessage.getDeliveryAttempts());
			Assert.assertEquals(message.getExpirationDate(), newMessage.getExpirationDate());
			Assert.assertEquals(message.getMessageId(), newMessage.getMessageId());
		}
		catch(Exception e) {
			System.out.println("Error");
			e.printStackTrace(System.out);
			Assert.fail(e.getMessage());
		}
	}

	
	@Test
	public void marshallXML() {
		try {
			String expectedXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Message><contentType>application/xml</contentType><data>&lt;data&gt;&lt;name&gt;Brent&gt;/name&gt;&lt;greeting&gt;Hello&lt;/greeting&gt;&lt;/data&gt;</data><deliveryAttempts>0</deliveryAttempts><expirationDate>2018-12-20T09:19:20.243</expirationDate><messageId>1</messageId></Message>";
			JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);

			Message message = new Message();

			message.setData("<data><name>Brent>/name><greeting>Hello</greeting></data>");
			message.setContentType("application/xml");
			message.setDeliveryAttempts(0);
			message.setExpirationDate("2018-12-20T09:19:20.243");
			message.setMessageId("1");

			StringWriter sw = new StringWriter();
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.marshal(message, sw);
			String xmlString = sw.toString();
			
			Assert.assertEquals(expectedXML, xmlString);

			StringReader xml = new StringReader(xmlString);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Message newMessage = (Message) jaxbUnmarshaller.unmarshal(xml);

			Assert.assertEquals(message.getData(), newMessage.getData());
			Assert.assertEquals(message.getContentType(), newMessage.getContentType());
			Assert.assertEquals(message.getDeliveryAttempts(), newMessage.getDeliveryAttempts());
			Assert.assertEquals(message.getExpirationDate(), newMessage.getExpirationDate());
			Assert.assertEquals(message.getMessageId(), newMessage.getMessageId());
		}
		catch(Exception e) {
			System.out.println("Error");
			e.printStackTrace(System.out);
			Assert.fail(e.getMessage());
		}
	}

//	@Test
//	public void unmarshallJSON() {
//		try {
//			Message message = new Message();
//
//			message.setData("{ \"name\" : \"Brent\", \"greeting\" : \"Hello\" } ");
//			message.setContentType("application/json");
//			message.setDeliveryAttempts(0);
//			message.setExpirationDate("2018-12-20T09:19:20.243");
//			message.setMessageId("1");
//		}
//		catch(Exception e) {
//			System.out.println("Error");
//			e.printStackTrace(System.out);
//			Assert.fail(e.getMessage());
//		}
//	}
}