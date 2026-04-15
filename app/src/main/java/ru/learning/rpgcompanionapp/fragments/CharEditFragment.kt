package ru.learning.rpgcompanionapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.learning.rpgcompanionapp.dto.CharData
import ru.learning.rpgcompanionapp.dto.CreateCharacterInput
import ru.learning.rpgcompanionapp.viewModel.CharListViewModel
import ru.learning.rpgcompanionapp.databinding.FragmentCharEditBinding
import ru.learning.rpgcompanionapp.R

class CharEditFragment : Fragment() {

    private val viewModel: CharListViewModel by activityViewModels()

    private var _binding: FragmentCharEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = requireActivity().findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.topAppBar)
        toolbar.title = "TWP Companion"
        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.goBackCharEditButton.setOnClickListener {
            val input = readInput()
            val char = buildCharacter(input)
            viewModel.addChar(char)
            findNavController().popBackStack()
        }
    }

    private fun buildCharacter(input: CreateCharacterInput): CharData {
        return CharData(
            charId = 0,
            charName = input.name,
            charRace = input.race,
            charClass = input.className,
            charLevel = input.level,
            charHp = input.hp,
            charAc = input.ac,
            charSpeed = input.speed,
            charStr = input.str,
            charDex = input.dex,
            charCon = input.con,
            charInt = input.intStat,
            charWis = input.wis,
            charCha = input.cha,
            charNotes = "",
            charImage = ""
        )
    }

    private fun readInput(): CreateCharacterInput {
        val name = binding.charEditTextName.text.toString().ifBlank { "Без Имени" }
        val race = binding.charEditTextRace.text.toString().ifBlank { "Без Расы" }
        val level = binding.charEditTextLevel.text.toString().toIntOrNull() ?: 1
        val className = binding.charEditTextClass.text.toString().ifEmpty { "Без Класса" }

        val hp = binding.charEditTextHp.text.toString().toIntOrNull() ?: 1
        val ac = binding.charEditTextAc.text.toString().toIntOrNull() ?: 10
        val speed = binding.charEditTextSpeed.text.toString().toIntOrNull() ?: 30

        val str = binding.charEditTextStr.text.toString().toIntOrNull() ?: 10
        val dex = binding.charEditTextDex.text.toString().toIntOrNull() ?: 10
        val con = binding.charEditTextCon.text.toString().toIntOrNull() ?: 10
        val intStat = binding.charEditTextInt.text.toString().toIntOrNull() ?: 10
        val wis = binding.charEditTextWis.text.toString().toIntOrNull() ?: 10
        val cha = binding.charEditTextCha.text.toString().toIntOrNull() ?: 10

        return CreateCharacterInput(
            name = name,
            race = race,
            className = className,
            level = level,
            hp = hp,
            ac = ac,
            speed = speed,
            str = str,
            dex = dex,
            con = con,
            intStat = intStat,
            wis = wis,
            cha = cha
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val toolbar = requireActivity().findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.topAppBar)
        toolbar.navigationIcon = null
        toolbar.setNavigationOnClickListener(null)
        _binding = null
    }
}