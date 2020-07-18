
package com.google.sps.servlets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.gson.Gson;
import com.google.sps.data.Comment;
import java.util.ArrayList;
import java.util.List;

/** Servlet that processes text. */
@WebServlet("/comment")
public final class CommentsServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.        
		BufferedReader br = 
		new BufferedReader(new InputStreamReader(request.getInputStream()));
		String newComment = "";
		if(br != null){
			newComment = br.readLine();
			System.out.println(newComment);
		}

    //convert to object
    Gson g = new Gson(); 
    Comment c = g.fromJson(newComment, Comment.class);

    //create comment entity
    Entity commentEntity = new Entity("Comment");
    commentEntity.setProperty("name", c.getName());
    commentEntity.setProperty("comment",c.getComment());
    

    //Store comment in datastore
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(commentEntity);
    

    // Respond with the result.
    response.setContentType("text/json");
    response.getWriter().println(newComment);
  }

    @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    

    //get comments from datastore
    Query query = new Query("Comment");
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);

    //handling result

    List<Comment> commentHistory = new ArrayList<>();
    for (Entity e : results.asIterable()) {
      long id = e.getKey().getId();
      String name = (String) e.getProperty("name");
      String comment = (String) e.getProperty("comment");

      Comment c = new Comment(name, comment);
      commentHistory.add(c);
    }

    //return the handled result
    Gson gson = new Gson();
    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(commentHistory));
  }

  /**
   * @return the request parameter, or the default value if the parameter
   *         was not specified by the client
   */
  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }
}
