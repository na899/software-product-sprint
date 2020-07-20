
package com.google.sps.data;
import lombok.AllArgsConstructor;

/** A comment **/

@AllArgsConstructor
public final class Comment {
  
  private final long id;
  private final String name;
  private final String comment;

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