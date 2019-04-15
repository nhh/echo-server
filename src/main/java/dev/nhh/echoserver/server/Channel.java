package dev.nhh.echoserver.server;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.Set;
import java.util.UUID;

@XmlType( propOrder = { "id", "name", "users"} )
public class Channel {

    private UUID id = UUID.randomUUID();
    private String name;
    private Set<String> users;

    @XmlElement(name = "id")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @XmlElementWrapper(name="users")
    @XmlElement(name = "user")
    public Set<String> getUsers() {
        return users;
    }

    public void setUsers(Set<String> users) {
        this.users = users;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
