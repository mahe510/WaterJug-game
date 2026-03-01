
        package com.example.waterjuggame.data

            object LevelRepository {

                val levels = listOf(

                    // -------- EASY (basic 2-jug understanding) --------
                    Level(1, listOf(4, 3), 2, Difficulty.EASY),
                    Level(2, listOf(5, 3), 4, Difficulty.EASY),
                    Level(3, listOf(6, 4), 2, Difficulty.EASY),
                    Level(4, listOf(7, 5), 6, Difficulty.EASY),

                    // -------- MEDIUM (bigger numbers, more steps) --------
                    Level(5, listOf(9, 4), 7, Difficulty.MEDIUM),
                    Level(6, listOf(9, 4), 6, Difficulty.MEDIUM),
                    Level(7, listOf(10, 7), 5, Difficulty.MEDIUM),
                    Level(8, listOf(11, 6), 7, Difficulty.MEDIUM),

                    // -------- HARD (3 JUG LOGIC STARTS HERE — TRANSITION SET) --------
                    Level(9,  listOf(12, 7, 5), 9, Difficulty.HARD),
                    Level(10, listOf(11, 7, 4), 6, Difficulty.HARD),
                    Level(11, listOf(13, 8, 5), 7, Difficulty.HARD),
                    Level(12, listOf(14, 9, 5), 8, Difficulty.HARD),
                    // -------- ADVANCED HARD (STATE-SPACE INTENSIVE — NON-TRIVIAL) --------
                    Level(13, listOf(13, 8, 5), 6, Difficulty.HARD),
                    Level(14, listOf(17, 11, 6), 7, Difficulty.HARD),
                    Level(15, listOf(19, 9, 4), 15, Difficulty.HARD),
                    Level(16, listOf(21, 14, 8), 5, Difficulty.HARD),
                    Level(17, listOf(23, 10, 7), 13, Difficulty.HARD),
                    Level(18, listOf(25, 16, 9), 4, Difficulty.HARD),
                    Level(19, listOf(27, 18, 11), 10, Difficulty.HARD),
                    Level(20, listOf(29, 13, 6), 17, Difficulty.HARD)
                )
            }
