package ru.learning.rpgcompanionapp.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class DndRulesTest {

    @Test
    fun getModifierStat_returnsCorrectModifier() {
        assertEquals(-1, DndRules.getModifierStat(8))
        assertEquals(0, DndRules.getModifierStat(10))
        assertEquals(1, DndRules.getModifierStat(12))
        assertEquals(3, DndRules.getModifierStat(16))
    }

    @Test
    fun getProficiencyBonus_returnsCorrectBonusByLevel() {
        assertEquals(2, DndRules.getProficiencyBonus(1))
        assertEquals(3, DndRules.getProficiencyBonus(5))
        assertEquals(4, DndRules.getProficiencyBonus(9))
        assertEquals(5, DndRules.getProficiencyBonus(13))
        assertEquals(6, DndRules.getProficiencyBonus(17))
    }
}