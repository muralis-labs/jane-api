package br.com.muralis.core.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;

import java.util.List;

@Getter
@RegisterForReflection
public class CustomPage<T> {

	private final List<T> content;

	private final int totalPages;

	private final long totalElements;

	private final int size;

	private final int number;

	private final boolean first;

	private final boolean last;

	public CustomPage(List<T> colaboradores, int page, int size, Long total) {
		this.content = colaboradores;
		this.totalElements = total;
		this.size = size;
		this.number = page;
		this.totalPages = (int) Math.ceil((double) total / size);
		this.first = page == 0;
		this.last = page == totalPages - 1;
	}

}
