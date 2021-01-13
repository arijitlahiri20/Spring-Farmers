package com.lti.dto;

import java.util.List;

public class ListStatus<E> extends Status {

	private List<E> list;

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}	
}
