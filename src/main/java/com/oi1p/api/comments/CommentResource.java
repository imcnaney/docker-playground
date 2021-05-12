package com.oi1p.api.comments;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/comments")
public class CommentResource {

  @GET
  public Response getComments() {
    List<Comment> result = Comment.listAll();
    return Response.ok(result).build();
  }

  @GET
  @Path("/{id}")
  public Response getComment(@PathParam("id") Long id) {
    Optional<Comment> maybeResult = Comment.findByIdOptional(id);
    if (maybeResult.isPresent()) {
      Comment result = maybeResult.get();
      return Response.ok(result).build();
    } else {
      throw new WebApplicationException("no comment with id: " + id, Status.NOT_FOUND);
    }
  }

  @POST
  @Transactional
  public Response add(Comment comment) {
    comment.id = null;
    comment.persist();
    return Response.ok(comment).build();
  }

  @PATCH
  @Transactional
  public Response update(Comment comment) {
    if (comment.id == null) {
      throw new WebApplicationException("comment must have an id", 422);
    }
    Optional<Comment> maybeResult = Comment.findByIdOptional(comment.id);
    if (maybeResult.isPresent()) {
      Comment result = maybeResult.get();
      result.comment = comment.comment;
      result.persist();
      return Response.ok(result).build();
    } else {
      throw new WebApplicationException("no comment with id:" + comment.id, Status.NOT_FOUND);
    }
  }

  @DELETE
  @Transactional
  public Response delete(Comment comment) {
    if (Comment.deleteById(comment.id)) {
      return Response.noContent().build();
    } else {
      throw new WebApplicationException("no comment with id:" + comment.id, Status.NOT_FOUND);
    }
  }

  @GET
  @Path("exception")
  public Response exception() {
    throw new WebApplicationException("fake exception", 500);
  }

}
