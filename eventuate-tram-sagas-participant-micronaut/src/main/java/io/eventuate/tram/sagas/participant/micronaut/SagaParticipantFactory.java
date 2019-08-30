package io.eventuate.tram.sagas.participant.micronaut;

import io.eventuate.common.jdbc.EventuateSchema;
import io.eventuate.tram.messaging.consumer.MessageConsumer;
import io.eventuate.tram.messaging.producer.MessageProducer;
import io.eventuate.tram.sagas.common.SagaLockManager;
import io.eventuate.tram.sagas.common.SagaLockManagerImpl;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory;
import io.micronaut.context.annotation.Factory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Singleton;

@Factory
public class SagaParticipantFactory {

  @Singleton
  public SagaLockManager sagaLockManager(JdbcTemplate jdbcTemplate, EventuateSchema eventuateSchema) {
    return new SagaLockManagerImpl(jdbcTemplate, eventuateSchema);
  }

  @Singleton
  public SagaCommandDispatcherFactory sagaCommandDispatcherFactory(MessageConsumer messageConsumer, MessageProducer messageProducer, SagaLockManager sagaLockManager) {
    return new SagaCommandDispatcherFactory(messageConsumer, messageProducer, sagaLockManager);
  }
}