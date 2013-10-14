package com.redip.config;

/**
 * {Insert class description here}
 *
 * @author bcivel
 */
public enum MessageGeneralEnum {

    /**
     * Message is a generic Message that is used to feedback the result of any Cerberus execution.
     * For every message, we have:
     * - a number
     * - a 2 digit code that report the status of the event.
     * - a clear message that will be reported to the user. describing what was done or the error that occured.
     */

    NOT_IMPLEMEMTED(101, "FA", "Missing data."),
    NO_DATA_FOUND(102, "FA", "Missing data."),
    SQLLIB_NOT_FOUND(103, "FA", "SQL Library was not found.")
    
    
    ;

    private final int code;
    private final String codeString;
    private final String description;

    private MessageGeneralEnum(int tempCode, String tempCodeString, String tempDesc) {
        this.code = tempCode;
        this.codeString = tempCodeString;
        this.description = tempDesc;
    }

    public int getCode() {
        return this.code;
    }

    public String getCodeString() {
        return codeString;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean equals(MessageGeneralEnum msg) {
        return this.code == msg.code;
    }
}
