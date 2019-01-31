package org.o7planning.tin.beans;

public class Book {

		private String title;
		private String author;
		private int ISBN;
		public Book() {
			
		}
		
		public Book(String title, String author, int ISBN) {
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

		public int getISBN() {
			return ISBN;
		}

		public void setISBN(int ISBN) {
			this.ISBN = ISBN;
		}
}
