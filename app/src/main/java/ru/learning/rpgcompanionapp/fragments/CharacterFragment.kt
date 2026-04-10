package ru.learning.rpgcompanionapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import ru.learning.rpgcompanionapp.R
import ru.learning.rpgcompanionapp.viewModel.CharListViewModel


class CharacterFragment : Fragment() {
    private val viewModel: CharListViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val charId = arguments?.getInt("charId", -1) ?: -1
        val nameText = view.findViewById<TextView>(R.id.characterNameText)
        val raceClassLevelText = view.findViewById<TextView>(R.id.characterRaceClassLevelText)
        val charHpText = view.findViewById<TextView>(R.id.characterHpText)
        val charAcText = view.findViewById<TextView>(R.id.characterAcText)
        val charSpeedText = view.findViewById<TextView>(R.id.characterSpeedText)
        val subClassText = view.findViewById<TextView>(R.id.characterSubClass)
        val charStrText = view.findViewById<TextView>(R.id.strengthValueText)
        val charIntText = view.findViewById<TextView>(R.id.intelligenceValueText)
        val charDexText = view.findViewById<TextView>(R.id.dexterityValueText)
        val charWisText = view.findViewById<TextView>(R.id.wisdomValueText)
        val charConText = view.findViewById<TextView>(R.id.constitutionValueText)
        val charChaText = view.findViewById<TextView>(R.id.charismaValueText)
        val strModText = view.findViewById<TextView>(R.id.strengthMetaText)
        val dexModText = view.findViewById<TextView>(R.id.dexterityMetaText)
        val conModText = view.findViewById<TextView>(R.id.constitutionMetaText)
        val intModText = view.findViewById<TextView>(R.id.intelligenceMetaText)
        val wisModText = view.findViewById<TextView>(R.id.wisdomMetaText)
        val chaModText = view.findViewById<TextView>(R.id.charismaMetaText)
        val profiencyTextView = view.findViewById<TextView>(R.id.characterProfText)
        val hitDiceText = view.findViewById<TextView>(R.id.characterHitDiceText)
        val saveText = view.findViewById<TextView>(R.id.characterSaveThrowText)


        val strAthleticsText = view.findViewById<TextView>(R.id.strengthAthleticsText)

        val dexAcrobaticsText = view.findViewById<TextView>(R.id.dexterityAcrobaticsText)
        val dexSleightOfHandText = view.findViewById<TextView>(R.id.dexteritySleightOfHandText)
        val dexStealthText = view.findViewById<TextView>(R.id.dexterityStealthText)

        val intArcanaText = view.findViewById<TextView>(R.id.intelligenceArcanaText)
        val intHistoryText = view.findViewById<TextView>(R.id.intelligenceHistoryText)
        val intInvestigationText = view.findViewById<TextView>(R.id.intelligenceInvestigationText)
        val intNatureText = view.findViewById<TextView>(R.id.intelligenceNatureText)
        val intReligionText = view.findViewById<TextView>(R.id.intelligenceReligionText)

        val wisAnimalHandlingText = view.findViewById<TextView>(R.id.wisdomAnimalHandlingText)
        val wisInsightText = view.findViewById<TextView>(R.id.wisdomInsightText)
        val wisMedicineText = view.findViewById<TextView>(R.id.wisdomMedicineText)
        val wisPerceptionText = view.findViewById<TextView>(R.id.wisdomPerceptionText)
        val wisSurvivalText = view.findViewById<TextView>(R.id.wisdomSurvivalText)

        val chaDeceptionText = view.findViewById<TextView>(R.id.charismaDeceptionText)
        val chaIntimidationText = view.findViewById<TextView>(R.id.charismaIntimidationText)
        val chaPerformanceText = view.findViewById<TextView>(R.id.charismaPerformanceText)
        val chaPersuasionText = view.findViewById<TextView>(R.id.charismaPersuasionText)
        viewModel.chars.observe(viewLifecycleOwner) { chars ->
            val char = chars.find { it.charId == charId }

            if (char != null) {
                val strMod = getModifierStat(char.charStr)
                val dexMod = getModifierStat(char.charDex)
                val conMod = getModifierStat(char.charCon)
                val intMod = getModifierStat(char.charInt)
                val wisMod = getModifierStat(char.charWis)
                val chaMod = getModifierStat(char.charCha)

                nameText.text = char.charName
                raceClassLevelText.text = "${char.charRace} • ${char.charClass} • ${char.charLevel}"
                subClassText.text = "Подкласс..."
                charSpeedText.text = "${char.charSpeed} фт."
                charStrText.text = char.charStr.toString()
                charIntText.text = char.charInt.toString()
                charDexText.text = char.charDex.toString()
                charWisText.text = char.charWis.toString()
                charConText.text = char.charCon.toString()
                charChaText.text = char.charCha.toString()
                charHpText.text = "${char.charHp}".toString()
                charAcText.text = char.charAc.toString()
                hitDiceText.text = "1к8"





                // модификаторы
                strModText.text = "мод ${formatMod(strMod)} • спас ${formatMod(strMod)}"
                dexModText.text = "мод ${formatMod(dexMod)} • спас ${formatMod(dexMod)}"
                conModText.text = "мод ${formatMod(conMod)} • спас ${formatMod(conMod)}"
                intModText.text = "мод ${formatMod(intMod)} • спас ${formatMod(intMod)}"
                wisModText.text = "мод ${formatMod(wisMod)} • спас ${formatMod(wisMod)}"
                chaModText.text = "мод ${formatMod(chaMod)} • спас ${formatMod(chaMod)}"

                strAthleticsText.text = "Атлетика ${formatMod(strMod)}"

                dexAcrobaticsText.text = "Акробатика ${formatMod(dexMod)}"
                dexSleightOfHandText.text = "Ловкость рук ${formatMod(dexMod)}"
                dexStealthText.text = "Скрытность ${formatMod(dexMod)}"

                intArcanaText.text = "Магия ${formatMod(intMod)}"
                intHistoryText.text = "История ${formatMod(intMod)}"
                intInvestigationText.text = "Расследование ${formatMod(intMod)}"
                intNatureText.text = "Природа ${formatMod(intMod)}"
                intReligionText.text = "Религия ${formatMod(intMod)}"

                wisAnimalHandlingText.text = "Уход за животными ${formatMod(wisMod)}"
                wisInsightText.text = "Проницательность ${formatMod(wisMod)}"
                wisMedicineText.text = "Медицина ${formatMod(wisMod)}"
                wisPerceptionText.text = "Восприятие ${formatMod(wisMod)}"
                wisSurvivalText.text = "Выживание ${formatMod(wisMod)}"

                chaDeceptionText.text = "Обман ${formatMod(chaMod)}"
                chaIntimidationText.text = "Запугивание ${formatMod(chaMod)}"
                chaPerformanceText.text = "Выступление ${formatMod(chaMod)}"
                chaPersuasionText.text = "Убеждение ${formatMod(chaMod)}"

                profiencyTextView.text = "${getProficiencyBonus(char.charLevel)}"
                saveText.text = "STR, CON"


            }
        }

    }

    private fun formatMod(mod: Int): String {
        return if (mod >= 0) "+$mod" else "$mod"
    }

    private fun getModifierStat(stat: Int): Int {
        return Math.floorDiv(stat - 10, 2)
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
}