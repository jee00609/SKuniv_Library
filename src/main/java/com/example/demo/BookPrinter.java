package com.example.demo;

public class BookPrinter {
	public void print(Book book) {
		System.out.printf("책 정보: ISBN=%s, 제목=%s, 저자=%s, 출판사=%s, 대출 유무=%s\n",book.getBookISBN(),book.getBookTitle(),book.getAuthor(),book.getPublisher(),book.getBookStatus());
	}
}
