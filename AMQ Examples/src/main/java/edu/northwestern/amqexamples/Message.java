package edu.northwestern.amqexamples;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {

	private String data;
	private String deliveryDate;
	private String expirationDate;
	private int deliveryAttempts;
	private String contentType;
	private String messageId;

	public Message() {

	}

	@JsonProperty("data")
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@JsonProperty("deliveryDate")
	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@JsonProperty("expirationDate")
	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	@JsonProperty("deliveryAttempts")
	public int getDeliveryAttempts() {
		return deliveryAttempts;
	}

	public void setDeliveryAttempts(int deliveryAttempts) {
		this.deliveryAttempts = deliveryAttempts;
	}

	@JsonProperty("contentType")
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@JsonProperty("messageId")
	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
}