package com.target.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.target.demo.model.Comment;

@Service
public class CommentService {
	//contains all the allowed words in upper case
	Set<String> allowedWords = new HashSet<String>();

	public Comment moderateComment(Comment comment) {
		if (comment.getComment() == null || comment.getComment().isEmpty()) {
			throw new RuntimeException("No comments found");
		}
		String words[] = comment.getComment().split(" ");
		String data = "";
		boolean isObjectionable = false;
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			if (!allowedWords.contains(word.toUpperCase().trim())) {
				words[i] = "***";
				isObjectionable = true;
			}
			data += data;
		}
		comment.setComment(data);
		comment.setApproved(!isObjectionable);
		return comment;

	}
}
