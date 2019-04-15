package dev.nhh.echoserver.server;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Set;
import java.util.UUID;

@XmlType( propOrder = { "id", "name", "channels"} )
@XmlRootElement( name = "Server" )
public class Server {

    private UUID id = UUID.randomUUID();
    private String name;
    private Set<Channel> channels;

    @XmlElement(name = "id")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @XmlElement(name = "channel", type = Channel.class)
    @XmlElementWrapper(name="channels")
    public Set<Channel> getChannels() {
        return channels;
    }

    public void setChannels(Set<Channel> channels) {
        this.channels = channels;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
