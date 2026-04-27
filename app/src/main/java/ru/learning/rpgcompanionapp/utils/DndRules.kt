package ru.learning.rpgcompanionapp.utils

object DndRules {
    fun getModifierStat(stat: Int): Int {
        return Math.floorDiv(stat - 10, 2)
    }

    fun getProficiencyBonus(level: Int): Int {
        return when (level) {
            in 1..4 -> 2
            in 5..8 -> 3
            in 9..12 -> 4
            in 13..16 -> 5
            else -> 6
        }
    }
}