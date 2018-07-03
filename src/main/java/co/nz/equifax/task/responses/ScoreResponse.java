package co.nz.equifax.task.responses;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class ScoreResponse {

	private String score;

	private String labels[];
	private String series[];
	private String[] data;

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String[] getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	public String[] getSeries() {
		return series;
	}

	public void setSeries(String[] series) {
		this.series = series;
	}

	public String[] getData() {
		return data;
	}

	public void setData(String[] data) {
		this.data = data;
	}
	
	

}
