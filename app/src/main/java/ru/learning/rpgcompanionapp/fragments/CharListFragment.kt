package ru.learning.rpgcompanionapp.fragments

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.learning.rpgcompanionapp.R
import ru.learning.rpgcompanionapp.adapter.CharAdapter
import ru.learning.rpgcompanionapp.viewModel.CharListViewModel
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope


class CharListFragment : Fragment() {


    private val viewModel: CharListViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_char_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<FloatingActionButton>(R.id.create_Char_Fab)
        val recyclerView = view.findViewById<RecyclerView>(R.id.charRecyclerViewXML)

        val charAdapter = CharAdapter { char ->
            findNavController().navigate(
                R.id.action_charListFragment_to_characterFragment,
                bundleOf("charId" to char.charId)
            )
        }

        button.setOnClickListener {
            findNavController().navigate(R.id.action_charListFragment_to_charEditFragment)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = charAdapter

        lifecycleScope.launchWhenStarted {
            viewModel.chars.collect { chars ->
                charAdapter.submitList(chars)
            }
        }
    }
}