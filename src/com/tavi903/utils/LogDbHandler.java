package com.tavi903.utils;

import java.sql.Timestamp;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.tavi903.config.ApplicationConfig;
import com.tavi903.entity.Log;

public class LogDbHandler extends StreamHandler {
	
	@Override
	public void publish(LogRecord record) {
		Log log = Log.builder()
					.date(Timestamp.from(record.getInstant()))
					.exception(record.getThrown() == null ? "" : record.getThrown().getClass().getName())
				    .level(record.getLevel().getName())
				    .message(record.getMessage())
				    .stackTrace(record.getThrown() == null ? "" : printStackTrace(record))
					.build();
		create(log);
	}

	@Override
	public void flush() {
	}

	@Override
	public void close() throws SecurityException {
	}
	
	private final Log create(Log log) {
		EntityManager entityManager = ApplicationConfig.entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(log);
		entityManager.getTransaction().commit();
		return log;
	}
	
	private String printStackTrace(LogRecord record) {
		StackTraceElement[] stackTrace = record.getThrown().getStackTrace();
		StringBuilder builder = new StringBuilder();
		for(StackTraceElement element : stackTrace) {
			builder.append(element.toString()+"\n");
		}
		builder.append("Caused by:\n");
		StackTraceElement[] stackTraceRootCause = ExceptionUtils.getRootCause(record.getThrown()).getStackTrace();
		for(StackTraceElement element : stackTraceRootCause) {
			builder.append(element.toString()+"\n");
		}
		return builder.toString();
	}

}
