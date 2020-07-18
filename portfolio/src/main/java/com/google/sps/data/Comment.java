
package com.google.sps.data;

/** A comment */
public final class Comment {
  private static int count = 0; 

  private final long id;
  private final String name;
  private final String comment;

  public Comment(String name, String comment) {
    this.id = count++;
    this.name = name;
    this.comment = comment;
  }

  public String getName(){
      return this.name;
  }
  public String getComment(){
      return this.comment;
  }
 public long getId(){
      return this.id;
  }
}