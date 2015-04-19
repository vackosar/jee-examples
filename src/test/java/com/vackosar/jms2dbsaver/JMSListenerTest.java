package com.vackosar.jms2dbsaver;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

import com.vackosar.jms2dbsaver.boundary.JMSListener;
import com.vackosar.jms2dbsaver.control.MessageProcessor;
import com.vackosar.jms2dbsaver.entity.MessageEntity;

public class JMSListenerTest {
	@InjectMocks
	private MessageProcessor messageProcessor = new MessageProcessor();
	
	@Mock
	private EntityManager entityManager;
	
	@Before public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void messageShouldBePersisted() {
		final List<MessageEntity> l = new ArrayList<>();
		TextMessageImpl inMessage = new TextMessageImpl("TEST TEXT");
		Mockito.doAnswer(
				new Answer<Void>() {
		
					@Override
					public Void answer(InvocationOnMock invocation) throws Throwable {
						l.add((MessageEntity) invocation.getArguments()[0]);
						return null;
					}
				}).when(entityManager).persist(any(MessageEntity.class));
		messageProcessor.process(inMessage);
		assertEquals(1, l.size());
	}
	
	static class TextMessageImpl implements TextMessage {
		String text;
		
		public TextMessageImpl(String text) {
			this.text = text;
		}

		@Override
		public void acknowledge() throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void clearBody() throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void clearProperties() throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public <T> T getBody(Class<T> arg0) throws JMSException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean getBooleanProperty(String arg0) throws JMSException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public byte getByteProperty(String arg0) throws JMSException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public double getDoubleProperty(String arg0) throws JMSException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public float getFloatProperty(String arg0) throws JMSException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getIntProperty(String arg0) throws JMSException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getJMSCorrelationID() throws JMSException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public byte[] getJMSCorrelationIDAsBytes() throws JMSException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getJMSDeliveryMode() throws JMSException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public long getJMSDeliveryTime() throws JMSException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Destination getJMSDestination() throws JMSException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getJMSExpiration() throws JMSException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getJMSMessageID() throws JMSException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getJMSPriority() throws JMSException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean getJMSRedelivered() throws JMSException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Destination getJMSReplyTo() throws JMSException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getJMSTimestamp() throws JMSException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getJMSType() throws JMSException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getLongProperty(String arg0) throws JMSException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getObjectProperty(String arg0) throws JMSException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Enumeration getPropertyNames() throws JMSException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public short getShortProperty(String arg0) throws JMSException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getStringProperty(String arg0) throws JMSException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isBodyAssignableTo(Class arg0) throws JMSException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean propertyExists(String arg0) throws JMSException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setBooleanProperty(String arg0, boolean arg1)
				throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setByteProperty(String arg0, byte arg1) throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setDoubleProperty(String arg0, double arg1)
				throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setFloatProperty(String arg0, float arg1)
				throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setIntProperty(String arg0, int arg1) throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setJMSCorrelationID(String arg0) throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setJMSCorrelationIDAsBytes(byte[] arg0) throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setJMSDeliveryMode(int arg0) throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setJMSDeliveryTime(long arg0) throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setJMSDestination(Destination arg0) throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setJMSExpiration(long arg0) throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setJMSMessageID(String arg0) throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setJMSPriority(int arg0) throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setJMSRedelivered(boolean arg0) throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setJMSReplyTo(Destination arg0) throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setJMSTimestamp(long arg0) throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setJMSType(String arg0) throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setLongProperty(String arg0, long arg1) throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setObjectProperty(String arg0, Object arg1)
				throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setShortProperty(String arg0, short arg1)
				throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setStringProperty(String arg0, String arg1)
				throws JMSException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getText() throws JMSException {
			return this.text;
		}

		@Override
		public void setText(String arg0) throws JMSException {
			// TODO Auto-generated method stub
			
		}
		
	}

}
