package ru.learning.rpgcompanionapp.fragments

import android.os.Bundle
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

                if (char != null) {
                    val strMod = getModifierStat(char.charStr)
                    val dexMod = getModifierStat(char.charDex)
                    val conMod = getModifierStat(char.charCon)
                    val intMod = getModifierStat(char.charInt)
                    val wisMod = getModifierStat(char.charWis)
                    val chaMod = getModifierStat(char.charCha)


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

                    binding.strengthMetaText.text = "мод ${formatMod(strMod)} • спас ${formatMod(strMod)}"
                    binding.dexterityMetaText.text = "мод ${formatMod(dexMod)} • спас ${formatMod(dexMod)}"
                    binding.constitutionMetaText.text = "мод ${formatMod(conMod)} • спас ${formatMod(conMod)}"
                    binding.intelligenceMetaText.text = "мод ${formatMod(intMod)} • спас ${formatMod(intMod)}"
                    binding.wisdomMetaText.text = "мод ${formatMod(wisMod)} • спас ${formatMod(wisMod)}"
                    binding.charismaMetaText.text = "мод ${formatMod(chaMod)} • спас ${formatMod(chaMod)}"

                    binding.strengthAthleticsText.text = "Атлетика ${formatMod(strMod)}"

                    binding.dexterityAcrobaticsText.text = "Акробатика ${formatMod(dexMod)}"
                    binding.dexteritySleightOfHandText.text = "Ловкость рук ${formatMod(dexMod)}"
                    binding.dexterityStealthText.text = "Скрытность ${formatMod(dexMod)}"

                    binding.intelligenceArcanaText.text = "Магия ${formatMod(intMod)}"
                    binding.intelligenceHistoryText.text = "История ${formatMod(intMod)}"
                    binding.intelligenceInvestigationText.text = "Расследование ${formatMod(intMod)}"
                    binding.intelligenceNatureText.text = "Природа ${formatMod(intMod)}"
                    binding.intelligenceReligionText.text = "Религия ${formatMod(intMod)}"

                    binding.wisdomAnimalHandlingText.text = "Уход за животными ${formatMod(wisMod)}"
                    binding.wisdomInsightText.text = "Проницательность ${formatMod(wisMod)}"
                    binding.wisdomMedicineText.text = "Медицина ${formatMod(wisMod)}"
                    binding.wisdomPerceptionText.text = "Восприятие ${formatMod(wisMod)}"
                    binding.wisdomSurvivalText.text = "Выживание ${formatMod(wisMod)}"

                    binding.charismaDeceptionText.text = "Обман ${formatMod(chaMod)}"
                    binding.charismaIntimidationText.text = "Запугивание ${formatMod(chaMod)}"
                    binding.charismaPerformanceText.text = "Выступление ${formatMod(chaMod)}"
                    binding.charismaPersuasionText.text = "Убеждение ${formatMod(chaMod)}"

                    binding.characterProfText.text = getProficiencyBonus(char.charLevel).toString()
                    binding.characterSaveThrowText.text = "STR, CON"
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