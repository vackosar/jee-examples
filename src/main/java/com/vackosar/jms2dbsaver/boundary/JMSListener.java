package com.vackosar.jms2dbsaver.boundary;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.vackosar.jms2dbsaver.control.MessageProcessor;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/Queue") })
public class JMSListener implements MessageListener {
	private static final String COMMAND_LIST = "list";

	private static final Logger log = Logger.getLogger(JMSListener.class
			.getName());

	@EJB
	MessageProcessor messageProcessor;

	@Override
	public void onMessage(Message inMessage) {
		messageProcessor.process(inMessage);
	}
}
