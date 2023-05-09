/*
 * This file is generated by jOOQ.
 */

package ru.tinkoff.edu.java.scrapper.domain.jooq.tables.pojos;

import java.io.Serializable;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class ChatLink implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long chatId;
    private Long linkId;

    public ChatLink() {
    }

    public ChatLink(ChatLink value) {
        this.chatId = value.chatId;
        this.linkId = value.linkId;
    }

    public ChatLink(
        Long chatId,
        Long linkId
    ) {
        this.chatId = chatId;
        this.linkId = linkId;
    }

    /**
     * Getter for <code>public.chat_link.chat_id</code>.
     */
    public Long getChatId() {
        return this.chatId;
    }

    /**
     * Setter for <code>public.chat_link.chat_id</code>.
     */
    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    /**
     * Getter for <code>public.chat_link.link_id</code>.
     */
    public Long getLinkId() {
        return this.linkId;
    }

    /**
     * Setter for <code>public.chat_link.link_id</code>.
     */
    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChatLink other = (ChatLink) obj;
        if (this.chatId == null) {
            if (other.chatId != null) {
                return false;
            }
        } else if (!this.chatId.equals(other.chatId)) {
            return false;
        }
        if (this.linkId == null) {
            if (other.linkId != null) {
                return false;
            }
        } else if (!this.linkId.equals(other.linkId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.chatId == null) ? 0 : this.chatId.hashCode());
        result = prime * result + ((this.linkId == null) ? 0 : this.linkId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ChatLink (");

        sb.append(chatId);
        sb.append(", ").append(linkId);

        sb.append(")");
        return sb.toString();
    }
}
