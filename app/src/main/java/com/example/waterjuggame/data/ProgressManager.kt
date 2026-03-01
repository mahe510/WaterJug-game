package com.example.waterjuggame.data

import android.content.Context

object ProgressManager {

    private const val PREF_NAME = "water_jug_progress"
    private const val KEY_UNLOCKED = "unlocked_levels"

    // ---------------- LOAD ----------------
    private fun loadUnlocked(context: Context): MutableSet<Int> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val saved = prefs.getStringSet(KEY_UNLOCKED, null)

        return if (saved != null)
            saved.map { it.toInt() }.toMutableSet()
        else
            mutableSetOf(0) // Level 1 unlocked by default
    }

    // ---------------- SAVE ----------------
    private fun saveUnlocked(context: Context, set: Set<Int>) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        prefs.edit()
            .putStringSet(KEY_UNLOCKED, set.map { it.toString() }.toSet())
            .apply()
    }

    // ---------------- PUBLIC API ----------------

    fun isUnlocked(context: Context, levelIndex: Int): Boolean {
        return loadUnlocked(context).contains(levelIndex)
    }

    fun unlockNext(context: Context, currentLevelIndex: Int) {
        val unlocked = loadUnlocked(context)

        val next = currentLevelIndex + 1
        if (next < LevelRepository.levels.size) {
            unlocked.add(next)
        }

        saveUnlocked(context, unlocked)
    }
}
