package ru.learning.rpgcompanionapp.utils

fun translateClassName(name: String): String {
    return when (name) {
        "Barbarian" -> "Варвар"
        "Bard" -> "Бард"
        "Cleric" -> "Жрец"
        "Druid" -> "Друид"
        "Fighter" -> "Воин"
        "Monk" -> "Монах"
        "Paladin" -> "Паладин"
        "Ranger" -> "Следопыт"
        "Rogue" -> "Плут"
        "Sorcerer" -> "Чародей"
        "Warlock" -> "Колдун"
        "Wizard" -> "Волшебник"
        else -> name
    }
}

fun translateRaceName(name: String): String {
    return when (name) {
        "Dragonborn" -> "Драконорождённый"
        "Dwarf" -> "Дварф"
        "Elf" -> "Эльф"
        "Gnome" -> "Гном"
        "Half-Elf" -> "Полуэльф"
        "Half-Orc" -> "Полуорк"
        "Halfling" -> "Полурослик"
        "Human" -> "Человек"
        "Tiefling" -> "Тифлинг"
        else -> name
    }
}