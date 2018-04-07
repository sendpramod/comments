package com.target.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.target.demo.model.Comment;
import com.target.demo.model.Response;
import com.target.service.CommentService;

@RestController
public class CommentsController {
	
	@Autowired
	CommentService commentService;
	
	private static List<Comment> COMMENTS = new ArrayList();

	@RequestMapping("/")
	public List<Comment> getComments() {
		List<Comment> comments = new ArrayList<Comment>();
		for (Comment c : COMMENTS) {
			if (c.isApproved()) {
				comments.add(c);
			}
		}

		return comments;
	}
	
	public Response<Comment> createComment(@RequestBody Comment comment){
		comment =commentService.moderateComment(comment);
		Response<Comment> response= new Response<Comment>();
		response.setContent(comment);
		if(!comment.isApproved()){
			response.setMessage("Comment cannot be saved please check the words marked in star");
		}else{
			COMMENTS.add(comment);
			response.setMessage("Comment saved successfully");
		}
		return response;
	}

}
