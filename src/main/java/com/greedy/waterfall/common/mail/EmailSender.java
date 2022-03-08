package com.greedy.waterfall.common.mail;

public interface EmailSender {
	public <T> boolean emailSender(T report);
}
