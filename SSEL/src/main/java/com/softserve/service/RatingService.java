package com.softserve.service;

import java.util.List;

import com.softserve.entity.Rating;

public interface RatingService {
	
	Rating addRating(Rating rating);
	
	Rating updateRating(Rating rating);
	
	Rating getRatingById(int id);
	
	List<Rating> getRatingByGroupAndUser(int groupId, int userId);
	
	double getAverageRatingByUserAndGroup(int userId, int groupId);

	double getProgressByGroupAndUser(int groupId, int userId);
	
}
