
package com.google.sps.data;
import lombok.AllArgsConstructor;
import lombok.Getter;

/** A comment **/

@AllArgsConstructor
public final class Comment {
  
 @Getter private final long id;
 @Getter private final String name;
 @Getter private final String comment;

}