package com.xcell.PageMe.model;

public class Message {
    private Long messageId;
    private String messageTitle;
    private String messageContent;
    private Employee sender;
    private final Integer urgencyLevel;

    public Message(Long messageId, String messageTitle, String messageContent, Employee sender) {
        this.messageId = messageId;
        this.messageTitle = messageTitle;
        this.messageContent = messageContent;
        this.sender = sender;
        this.urgencyLevel = 1;
    }

    public Message(Long messageId, String messageTitle, String messageContent, Employee sender, Integer urgencyLevel) {
        this.messageId = messageId;
        this.messageTitle = messageTitle;
        this.messageContent = messageContent;
        this.sender = sender;

        if(urgencyLevel >= 1 &&  urgencyLevel <= 10)
            this.urgencyLevel = urgencyLevel;
        else
            throw new IllegalArgumentException("urgencyLevel should be between 1 and 10");
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Employee getSender() {
        return sender;
    }

    public void setSender(Employee sender) {
        this.sender = sender;
    }

    public Integer getUrgencyLevel() {
        return urgencyLevel;
    }
}
