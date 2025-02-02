package com.techelevator.dao;

import java.util.List;

public interface FavoriteDao {

    // Add an event to the user's favorites
    void addFavorite(int userId, int eventId);

    // Remove an event from the user's favorites
    void removeFavorite(int userId, int eventId);

    // Get all favorited events for a user
    List<Integer> getFavoriteEventIds(int userId);
}
