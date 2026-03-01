package com.example.waterjuggame.data

import android.content.Context
import org.json.JSONObject

// ---------- USER MODEL ----------
data class User(
    val name: String,
    val email: String,
    val score: Int = 0,
    val levelsCompleted: Int = 0
)

// 🔥 stores best score per level
private const val LEVEL_SCORES = "level_scores"

// ---------- USER MANAGER ----------
object UserManager {

    private const val PREF_NAME = "water_jug_user_pref"
    private const val CURRENT_USER = "current_user"

    private fun prefs(context: Context) =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    // =====================================================
    // LEVEL BEST SCORE SYSTEM  (NEW — does NOT break old code)
    // =====================================================

    fun getLevelBestScore(context: Context, level: Int): Int {
        val jsonString = prefs(context).getString(LEVEL_SCORES, "{}")!!
        val json = JSONObject(jsonString)
        return json.optInt(level.toString(), 0)
    }

    fun updateBestScore(context: Context, level: Int, newScore: Int) {

        val jsonString = prefs(context).getString(LEVEL_SCORES, "{}")!!
        val json = JSONObject(jsonString)

        val oldScore = json.optInt(level.toString(), 0)

        // ✅ Only save if player improved
        if (newScore > oldScore) {
            json.put(level.toString(), newScore)

            prefs(context).edit()
                .putString(LEVEL_SCORES, json.toString())
                .apply()
        }
    }

    // ✅ Calculate TOTAL score from best scores
    fun getTotalScore(context: Context): Int {

        val jsonString = prefs(context).getString(LEVEL_SCORES, "{}")!!
        val json = JSONObject(jsonString)

        var total = 0
        json.keys().forEach { key ->
            total += json.getInt(key)
        }

        return total
    }

    // =====================================================
    // EXISTING USER SYSTEM (UNCHANGED)
    // =====================================================

    fun saveUser(context: Context, user: User) {

        val json = JSONObject().apply {
            put("name", user.name)
            put("email", user.email)
            put("score", user.score) // kept for compatibility
            put("levelsCompleted", user.levelsCompleted)
        }

        prefs(context).edit()
            .putString(CURRENT_USER, json.toString())
            .apply()
    }

    fun getCurrentUser(context: Context): User? {

        val data = prefs(context).getString(CURRENT_USER, null)
            ?: return null

        val json = JSONObject(data)

        return User(
            name = json.getString("name"),
            email = json.getString("email"),
            score = json.optInt("score", 0),
            levelsCompleted = json.optInt("levelsCompleted", 0)
        )
    }

    fun login(context: Context, name: String, email: String) {

        val existing = getCurrentUser(context)

        if (existing != null && existing.email == email) {
            // same user → keep progress
            return
        }

        val newUser = User(name = name, email = email)
        saveUser(context, newUser)
    }

    // ❗ DO NOT use this for level scoring anymore
    fun updateScore(context: Context, earned: Int) {

        val user = getCurrentUser(context) ?: return

        val updatedUser = user.copy(
            score = user.score + earned
        )

        saveUser(context, updatedUser)
    }

    fun incrementLevelsCompleted(context: Context) {

        val user = getCurrentUser(context) ?: return

        val updatedUser = user.copy(
            levelsCompleted = user.levelsCompleted + 1
        )

        saveUser(context, updatedUser)
    }

    fun logout(context: Context) {
        prefs(context).edit().remove(CURRENT_USER).apply()
    }
}