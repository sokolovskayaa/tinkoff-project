/*
 * This file is generated by jOOQ.
 */
package ru.tinkoff.edu.java.scrapper.domain.jooq.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    public Chat() {}

    public Chat(Chat value) {
        this.id = value.id;
    }

    public Chat(
        Long id
    ) {
        this.id = id;
    }

    /**
     * Getter for <code>public.chat.id</code>.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.chat.id</code>.
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Chat other = (Chat) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Chat (");

        sb.append(id);

        sb.append(")");
        return sb.toString();
    }
}
