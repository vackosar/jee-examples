package com.vackosar.jms2dbsaver.control;

import java.io.StringWriter;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.xml.bind.JAXB;

import com.vackosar.jms2dbsaver.entity.MessageEntity;

@Stateless
public class MessageProcessor {
	private static final String COMMAND_LIST = "list";

	private static final Logger log = Logger.getLogger(MessageProcessor.class
			.getName());

	@PersistenceContext(unitName = "MessageUnit")
	private EntityManager entityManager;

	public void process(Message inMessage) {
		TextMessage msg = null;

		try {
			if (inMessage instanceof TextMessage) {
				msg = (TextMessage) inMessage;
				log.info("MESSAGE BEAN: Message received: " + msg.getText());
				if (COMMAND_LIST.equals(msg.getText())) {
					listMessages();
				} else {
					MessageEntity messageEntity = new MessageEntity(msg.getText());
					entityManager.persist(messageEntity);
					StringWriter writer = new StringWriter();
					JAXB.marshal(messageEntity, writer);
					log.info("Persisting message entity:\n" + writer.toString()); 
				}
			} else {
				log.warning("Message of wrong type: "
						+ inMessage.getClass().getName());
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	private void listMessages() {
		CriteriaBuilder criteriaBuilder = entityManager
				.getCriteriaBuilder();
		CriteriaQuery<MessageEntity> query = criteriaBuilder
				.createQuery(MessageEntity.class);
		Root<MessageEntity> from = query.from(MessageEntity.class);
		query.select(from);
		TypedQuery<MessageEntity> tc = entityManager.createQuery(query);
		log.info(tc.getResultList().toString());
	}
}
