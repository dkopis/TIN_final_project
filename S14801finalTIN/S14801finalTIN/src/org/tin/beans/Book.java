package org.tin.beans;

public class Book {

		private String title;
		private String author;
		private String ISBN;
		public Book() {
			
		}
		
		public Book(String title, String author, String ISBN) {
			this.setTitle(title);
			this.setAuthor(author);
			this.setISBN(ISBN);
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getISBN() {
			return ISBN;
		}

		public void setISBN(String iSBN2) {
			this.ISBN = iSBN2;
		}
}
