package com.redip.exception;

import com.redip.config.MessageGeneral;

/**
 * {Insert class description here}
 *
 * @author bcivel
 */
public class QualityException extends Exception {

    private MessageGeneral MessageError;

    public QualityException(MessageGeneral message) {
        this.MessageError = message;
    }

    public MessageGeneral getMessageError() {
        return MessageError;
    }

    public void setMessageError(MessageGeneral MessageError) {
        this.MessageError = MessageError;
    }

}
