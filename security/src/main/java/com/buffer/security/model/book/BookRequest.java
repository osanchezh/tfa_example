package com.buffer.security.model.book;

import java.time.LocalDateTime;
import java.util.Objects;

public class BookRequest {

    private Integer id;
    private String author;
    private String isbn;
    private Integer createBy;
    private LocalDateTime createDate;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(author, createBy, createDate, id, isbn);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookRequest other = (BookRequest) obj;
		return Objects.equals(author, other.author) && Objects.equals(createBy, other.createBy)
				&& Objects.equals(createDate, other.createDate) && Objects.equals(id, other.id)
				&& Objects.equals(isbn, other.isbn);
	}
	@Override
	public String toString() {
		return "BookRequest [id=" + id + ", author=" + author + ", isbn=" + isbn + ", createBy=" + createBy
				+ ", createDate=" + createDate + "]";
	}
	public BookRequest(Integer id, String author, String isbn, Integer createBy, LocalDateTime createDate) {
		super();
		this.id = id;
		this.author = author;
		this.isbn = isbn;
		this.createBy = createBy;
		this.createDate = createDate;
	}
	public BookRequest() {
		super();
	}
	
}