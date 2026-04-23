package ru.learning.rpgcompanionapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import ru.learning.rpgcompanionapp.R
import ru.learning.rpgcompanionapp.viewModel.CharListViewModel
import androidx.lifecycle.lifecycleScope
import com.google.android.material.appbar.MaterialToolbar
import ru.learning.rpgcompanionapp.databinding.FragmentCharacterBinding


class CharacterFragment : Fragment() {
    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!


    private val viewModel: CharListViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val toolbar = requireActivity().findViewById<MaterialToolbar>(R.id.topAppBar)
        toolbar.title = "TWP Companion"
        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }


        val charId = arguments?.getInt("charId", -1) ?: -1
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.chars.collect { chars ->
                val char = chars.find { it.charId == charId }
                Log.d("RPG_DEBUG", "skills = ${char?.skills}")
                if (char != null) {
                    val strMod = getModifierStat(char.charStr)
                    val dexMod = getModifierStat(char.charDex)
                    val conMod = getModifierStat(char.charCon)
                    val intMod = getModifierStat(char.charInt)
                    val wisMod = getModifierStat(char.charWis)
                    val chaMod = getModifierStat(char.charCha)

                    val profBonus = getProficiencyBonus(char.charLevel)

                    val athleticsValue = getSkillValue(char, "Athletics", strMod, profBonus)

                    val acrobaticsValue = getSkillValue(char, "Acrobatics", dexMod, profBonus)
                    val sleightOfHandValue = getSkillValue(char, "Sleight of Hand", dexMod, profBonus)
                    val stealthValue = getSkillValue(char, "Stealth", dexMod, profBonus)

                    val arcanaValue = getSkillValue(char, "Arcana", intMod, profBonus)
                    val historyValue = getSkillValue(char, "History", intMod, profBonus)
                    val investigationValue = getSkillValue(char, "Investigation", intMod, profBonus)
                    val natureValue = getSkillValue(char, "Nature", intMod, profBonus)
                    val religionValue = getSkillValue(char, "Religion", intMod, profBonus)

                    val animalHandlingValue = getSkillValue(char, "Animal Handling", wisMod, profBonus)
                    val insightValue = getSkillValue(char, "Insight", wisMod, profBonus)
                    val medicineValue = getSkillValue(char, "Medicine", wisMod, profBonus)
                    val perceptionValue = getSkillValue(char, "Perception", wisMod, profBonus)
                    val survivalValue = getSkillValue(char, "Survival", wisMod, profBonus)

                    val deceptionValue = getSkillValue(char, "Deception", chaMod, profBonus)
                    val intimidationValue = getSkillValue(char, "Intimidation", chaMod, profBonus)
                    val performanceValue = getSkillValue(char, "Performance", chaMod, profBonus)
                    val persuasionValue = getSkillValue(char, "Persuasion", chaMod, profBonus)

                    val strSave = getSaveThrowValue(char.charClass, "STR", strMod, profBonus)
                    val dexSave = getSaveThrowValue(char.charClass, "DEX", dexMod, profBonus)
                    val conSave = getSaveThrowValue(char.charClass, "CON", conMod, profBonus)
                    val intSave = getSaveThrowValue(char.charClass, "INT", intMod, profBonus)
                    val wisSave = getSaveThrowValue(char.charClass, "WIS", wisMod, profBonus)
                    val chaSave = getSaveThrowValue(char.charClass, "CHA", chaMod, profBonus)


                    binding.characterNameText.text = char.charName
                    binding.characterRaceClassLevelText.text =
                        "${char.charRace} • ${char.charClass} • ${char.charLevel}"
                    binding.characterSubClass.text = "Подкласс..."
                    binding.characterSpeedText.text = "${char.charSpeed} фт."

                    binding.strengthValueText.text = char.charStr.toString()
                    binding.intelligenceValueText.text = char.charInt.toString()
                    binding.dexterityValueText.text = char.charDex.toString()
                    binding.wisdomValueText.text = char.charWis.toString()
                    binding.constitutionValueText.text = char.charCon.toString()
                    binding.charismaValueText.text = char.charCha.toString()

                    binding.characterHpText.text = char.charHp.toString()
                    binding.characterAcText.text = char.charAc.toString()
                    binding.characterHitDiceText.text = "1к8"

                    binding.strengthMetaText.text = "мод ${formatMod(strMod)} • спас ${formatMod(strSave)}"
                    binding.dexterityMetaText.text = "мод ${formatMod(dexMod)} • спас ${formatMod(dexSave)}"
                    binding.constitutionMetaText.text = "мод ${formatMod(conMod)} • спас ${formatMod(conSave)}"
                    binding.intelligenceMetaText.text = "мод ${formatMod(intMod)} • спас ${formatMod(intSave)}"
                    binding.wisdomMetaText.text = "мод ${formatMod(wisMod)} • спас ${formatMod(wisSave)}"
                    binding.charismaMetaText.text = "мод ${formatMod(chaMod)} • спас ${formatMod(chaSave)}"

                    binding.strengthAthleticsText.text = "Атлетика ${formatMod(athleticsValue)}"

                    binding.dexterityAcrobaticsText.text = "Акробатика ${formatMod(acrobaticsValue)}"
                    binding.dexteritySleightOfHandText.text = "Ловкость рук ${formatMod(sleightOfHandValue)}"
                    binding.dexterityStealthText.text = "Скрытность ${formatMod(stealthValue)}"

                    binding.intelligenceArcanaText.text = "Магия ${formatMod(arcanaValue)}"
                    binding.intelligenceHistoryText.text = "История ${formatMod(historyValue)}"
                    binding.intelligenceInvestigationText.text = "Расследование ${formatMod(investigationValue)}"
                    binding.intelligenceNatureText.text = "Природа ${formatMod(natureValue)}"
                    binding.intelligenceReligionText.text = "Религия ${formatMod(religionValue)}"

                    binding.wisdomAnimalHandlingText.text = "Уход за животными ${formatMod(animalHandlingValue)}"
                    binding.wisdomInsightText.text = "Проницательность ${formatMod(insightValue)}"
                    binding.wisdomMedicineText.text = "Медицина ${formatMod(medicineValue)}"
                    binding.wisdomPerceptionText.text = "Восприятие ${formatMod(perceptionValue)}"
                    binding.wisdomSurvivalText.text = "Выживание ${formatMod(survivalValue)}"

                    binding.charismaDeceptionText.text = "Обман ${formatMod(deceptionValue)}"
                    binding.charismaIntimidationText.text = "Запугивание ${formatMod(intimidationValue)}"
                    binding.charismaPerformanceText.text = "Выступление ${formatMod(performanceValue)}"
                    binding.charismaPersuasionText.text = "Убеждение ${formatMod(persuasionValue)}"

                    binding.characterProfText.text = formatMod(profBonus)
                    binding.characterSaveThrowText.text = getSaveThrowNames(char.charClass)

                    binding.deleteCharacterButton.setOnClickListener {
                        androidx.appcompat.app.AlertDialog.Builder(requireContext())
                            .setTitle("Удалить персонажа?")
                            .setMessage("Это действие нельзя отменить.")
                            .setPositiveButton("Удалить") { _, _ ->
                                viewModel.deleteChar(char.charId)
                                requireActivity().onBackPressedDispatcher.onBackPressed()
                            }
                            .setNegativeButton("Отмена", null)
                            .show()
                    }
                }

            }

        }

    }

    private fun formatMod(mod: Int): String {
        return if (mod >= 0) "+$mod" else "$mod"
    }

    private fun getModifierStat(stat: Int): Int {
        return Math.floorDiv(stat - 10, 2)
    }

    override fun onDestroyView() {
        val toolbar = requireActivity().findViewById<MaterialToolbar>(R.id.topAppBar)
        toolbar.navigationIcon = null
        toolbar.setNavigationOnClickListener(null)
        _binding = null
        super.onDestroyView()
    }

    private fun getSkillValue(
        char: ru.learning.rpgcompanionapp.dto.CharData,
        skillApiName: String,
        statMod: Int,
        proficiencyBonus: Int
    ): Int {
        return if (char.skills.contains(skillApiName)) {
            statMod + proficiencyBonus
        } else {
            statMod
        }
    }

    private fun getProficiencyBonus(level: Int): Int{
        return when(level) {
            in 1..4 -> 2
            in 5..8 -> 3
            in 9..12 -> 4
            in 13..16 -> 5
            else -> 6
        }
    }

    private fun getSaveThrowNames(charClass: String): String {
        return when (charClass.lowercase()) {
            "barbarian", "варвар" -> "СИЛ, ТЕЛ"
            "bard", "бард" -> "ЛОВ, ХАР"
            "cleric", "жрец" -> "МДР, ХАР"
            "druid", "друид" -> "ИНТ, МДР"
            "fighter", "воин" -> "СИЛ, ТЕЛ"
            "monk", "монах" -> "СИЛ, ЛОВ"
            "paladin", "паладин" -> "МДР, ХАР"
            "ranger", "следопыт" -> "СИЛ, ЛОВ"
            "rogue", "плут" -> "ЛОВ, ИНТ"
            "sorcerer", "чародей" -> "ТЕЛ, ХАР"
            "warlock", "колдун" -> "МДР, ХАР"
            "wizzard", "волшебник" -> "ИНТ, МДР"
            else -> "—"
        }
    }

    private fun hasSaveThrowProficiency(charClass: String, statCode: String): Boolean {
        return when (charClass.lowercase()) {
            "barbarian", "варвар" -> statCode == "STR" || statCode == "CON"
            "bard", "бард" -> statCode == "DEX" || statCode == "CHA"
            "cleric", "жрец" -> statCode == "WIS" || statCode == "CHA"
            "druid", "друид" -> statCode == "INT" || statCode == "WIS"
            "fighter", "воин" -> statCode == "STR" || statCode == "CON"
            "monk", "монах" -> statCode == "STR" || statCode == "DEX"
            "paladin", "паладин" -> statCode == "WIS" || statCode == "CHA"
            "ranger", "следопыт" -> statCode == "STR" || statCode == "DEX"
            "rogue", "плут" -> statCode == "DEX" || statCode == "INT"
            "sorcerer", "чародей" -> statCode == "CON" || statCode == "CHA"
            "warlock", "колдун" -> statCode == "WIS" || statCode == "CHA"
            "wizard","wizzard", "волшебник" -> statCode == "INT" || statCode == "WIS"
            else -> false
        }
    }

    private fun getSaveThrowValue(
        charClass: String,
        statCode: String,
        statMod: Int,
        proficiencyBonus: Int
    ): Int {
        return if (hasSaveThrowProficiency(charClass, statCode)) {
            statMod + proficiencyBonus
        } else {
            statMod
        }
    }

    private fun hasSkillProficiency(charClass: String, skill: String): Boolean {
        return when (charClass.lowercase()) {
            "fighter", "воин" -> skill in listOf("athletics", "perception")
            "rogue", "плут" -> skill in listOf("stealth", "acrobatics")
            "wizard", "волшебник" -> skill in listOf("arcana", "history")
            else -> false
        }
    }
}