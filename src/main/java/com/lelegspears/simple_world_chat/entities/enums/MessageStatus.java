package com.lelegspears.simple_world_chat.entities.enums;

public enum MessageStatus {
	Enviada(1),
	Recebida(2),
	Lida(3);
	
	private int code;
	
	private MessageStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static MessageStatus valueOf(int code) {
		for(MessageStatus ms:MessageStatus.values()) {
			if(ms.getCode() == code) {
				return ms;
			}
		}
		throw new IllegalArgumentException("Invalid MessageStatus code");
	}
}
