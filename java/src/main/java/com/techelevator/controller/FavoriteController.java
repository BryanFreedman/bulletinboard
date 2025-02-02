package com.techelevator.controller;

import com.techelevator.dao.FavoriteDao;
import com.techelevator.security.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteDao favoriteDao;

    public FavoriteController(FavoriteDao favoriteDao) {
        this.favoriteDao = favoriteDao;
    }

    /**
     * Add an event to favorites.
     */
    @PostMapping("/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFavorite(@PathVariable int eventId) {
        int userId = SecurityUtils.getCurrentUserId();
        favoriteDao.addFavorite(userId, eventId);
    }

    /**
     * Remove an event from favorites.
     */
    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFavorite(@PathVariable int eventId) {
        int userId = SecurityUtils.getCurrentUserId();
        favoriteDao.removeFavorite(userId, eventId);
    }

    /**
     * Get all favorited event IDs for the logged-in user.
     */
    @GetMapping
    public ResponseEntity<List<Integer>> getFavoriteEvents() {
        int userId = SecurityUtils.getCurrentUserId();
        return ResponseEntity.ok(favoriteDao.getFavoriteEventIds(userId));
    }
}
