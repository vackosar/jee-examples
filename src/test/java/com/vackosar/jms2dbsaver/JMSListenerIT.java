package com.vackosar.jms2dbsaver;

import java.util.Properties;
import java.util.logging.Logger;

import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Test;


public class JMSListenerIT {
	private static final Logger log = Logger.getLogger(JMSListenerIT.class.getName());
	
	private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    private static final String JBOSS_NAMING_CLIENT_EJB_CONTEXT = "jboss.naming.client.ejb.context";
    public static final String PROVIDER_URL_LOCALHOST = "remote://localhost:4447";
	
	private static String QUEUE = "jms/Queue";

	private static final String CONNECTION_FACTORY_JNDI_NAME = "jms/RemoteConnectionFactory";
	private static final boolean TRANSACTED = false;
	
	private static QueueConnection connection;
	private static QueueSession session;
	private static MessageProducer producer;
    private static QueueConnectionFactory connectionFactory;
    private static Destination destination;

	private static InitialContext initialContext;

    protected static InitialContext getInitialContext(final String providerUrl) throws NamingException {
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
		env.put(Context.PROVIDER_URL, getRemotingUrl(providerUrl));
		env.put(JBOSS_NAMING_CLIENT_EJB_CONTEXT, true);
		return new InitialContext(env);
	}
	
	private static Object getRemotingUrl(String providerUrl) {
		return PROVIDER_URL_LOCALHOST;
	}
	
	@Test
	public void shouldBeTrue() {
		Assert.assertTrue(true);
	}

	public static void main(String args[]) throws Exception {
		initialContext = getInitialContext(PROVIDER_URL_LOCALHOST);
		destination = (Destination) initialContext.lookup(QUEUE);
		connectionFactory =	(QueueConnectionFactory) initialContext.lookup(CONNECTION_FACTORY_JNDI_NAME);
		connection = connectionFactory.createQueueConnection();
		session = connection.createQueueSession(TRANSACTED, Session.AUTO_ACKNOWLEDGE);
		producer = session.createProducer(destination);
		connection.start();
		TextMessage message = session.createTextMessage();
		message.setText("TEST MESSAGE TEXT");
		producer.send(message);
	}
}
