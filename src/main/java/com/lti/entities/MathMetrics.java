package com.lti.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "math_metrics")
public class MathMetrics {

	@Id
	private int metric_id;
	private String metric;
	private float value;
	
	public int getMetric_id() {
		return metric_id;
	}
	public void setMetric_id(int metric_id) {
		this.metric_id = metric_id;
	}
	public String getMetric() {
		return metric;
	}
	public void setMetric(String metric) {
		this.metric = metric;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	
	
}
