package nl.creationinc.swopp2;

import com.parse.ParseObject;

public class swopObject
{
	private String id;
	private String imgUrl;
	private String name;
	private String description;
	private int likes;

	public swopObject(ParseObject object)
	{
		id = object.getObjectId();
		imgUrl = object.getParseFile("image").getUrl();
		name = object.getString("name");
		description = object.getString("description");
		likes = object.getInt("likes");
	}

	public swopObject()
	{
		id = "";
		imgUrl = "";
		name = "";
		description = "";
		likes = 0;
	}

	public String getId()
	{
		return id;
	}

	public String getImgUrl()
	{
		return imgUrl;
	}

	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public int getLikes()
	{
		return likes;
	}
}
