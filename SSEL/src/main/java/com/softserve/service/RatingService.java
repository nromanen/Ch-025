package com.softserve.service;

import java.util.List;

import com.softserve.entity.Rating;

public interface RatingService {
	
	public Rating addService(Rating rating);
	
	public Rating getRatingById(int id);
	
	public List<Rating> getRatingByGroupAndUser(int groupId, int userId);
	
	public double getAverageRatingByUserAndGroup(int userId, int groupId);

	public double getProgressByGroupAndUser(int groupId, int userId);
}
