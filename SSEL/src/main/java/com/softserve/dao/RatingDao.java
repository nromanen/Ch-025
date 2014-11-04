package com.softserve.dao;

import java.util.List;

import com.softserve.entity.Rating;

public interface RatingDao {
	/**
	 * Add new rating record into ratings
	 * @param newRating new rating
	 * @return added rating
	 */
	public Rating addRating(Rating newRating);
	
	/**
	 * Update rating information 
	 * @param updated updated rating record
	 * @return updated rating
	 */
	public Rating updateRating(Rating updated);
	
	/**
	 * Delete rating record from table
	 * @param rating rating record to delete
	 */
	public void deleteRating(Rating rating);
	
	/**
	 * Return rating record by rating id
	 * @param ratingId unique rating record identifier
	 * @return rating row if exists or null otherwise
	 */
	public Rating getRatingById(int ratingId);
	
	/**
	 * Return ratings for some student 
	 * @param blockId unique block identifier
	 * @param userId unique user identifier
	 * @return ratings by group for user
	 */
	public List<Rating> getRatingsByGroupAndUser(int groupId, int userId);
	
	/**
	 * Return average rating for course
	 * @param groupId
	 * @param userId
	 * @return
	 */
	public double getAverageRatingByGroupAndUser(int groupId, int userId);
	
	/**
	 * Return progress for some student by his id and group
	 * @param groupId unique group identifier
	 * @param userId unique user identifier
	 * @return
	 */
	public double getProgressByGroupAndUser(int groupId, int userId);
}
