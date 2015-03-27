package net.blaklizt.symbiosis.sym_persistence;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * SymbiosisUser: tkaviya
 * Date: 8/7/13
 * Time: 9:02 PM
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "Channel")
public class Channel implements Serializable{
	private Integer channelID;
	private String description;

	@javax.persistence.Column(name = "ChannelID")
	@javax.persistence.Id
	public Integer getChannelID() {
		return channelID;
	}

	public void setChannelID(Integer channelID) {
		this.channelID = channelID;
	}

	@javax.persistence.Column(name = "Description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Channel channel = (Channel) o;

		if (channelID != null ? !channelID.equals(channel.channelID) : channel.channelID != null)
			return false;
		else if (description != null ? !description.equals(channel.description) : channel.description != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = channelID != null ? channelID.hashCode() : 0;
		result = 31 * result + (description != null ? description.hashCode() : 0);
		return result;
	}
}
