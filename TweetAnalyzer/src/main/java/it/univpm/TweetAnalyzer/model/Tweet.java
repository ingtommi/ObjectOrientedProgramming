package it.univpm.TweetAnalyzer.model;

public class Tweet {


	public Tweet() {
        this.created_at = null;
		this.id = 0;
		this.text = "";
	}



	private String created_at;
    private long id;
    private String text;



	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
