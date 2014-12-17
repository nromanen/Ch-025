package com.softserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.RatingDao;
import com.softserve.entity.Rating;
import com.softserve.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService{

	@Autowired
	private RatingDao ratingDao;

	@Transactional
	@Override
	public Rating addRating(Rating rating) {
		return ratingDao.addRating(rating);
	}

	@Transactional
	@Override
	public Rating getRatingById(int id) {
		return ratingDao.getRatingById(id);
	}

	@Override
	public List<Rating> getRatingByGroupAndUser(int groupId, int userId) {
		return ratingDao.getRatingsByGroupAndUser(groupId, userId);
	}
	
	@Override
	public double getAverageRatingByUserAndGroup(int userId, int groupId) {
		return ratingDao.getAverageRatingByGroupAndUser(groupId, userId);
	}

	@Override
	public double getProgressByGroupAndUser(int groupId, int userId) {
		return ratingDao.getProgressByGroupAndUser(groupId, userId);
	}

	@Override
	public Rating updateRating(Rating rating) {
		return ratingDao.updateRating(rating);
	}

	
}
