package ru.learning.rpgcompanionapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.learning.rpgcompanionapp.databinding.FragmentLoginBinding
import androidx.lifecycle.ViewModelProvider
import android.widget.Toast
import ru.learning.rpgcompanionapp.viewModel.AuthViewModel
import ru.learning.rpgcompanionapp.R

class LoginFragment : Fragment() {

    private lateinit var viewModel: AuthViewModel
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        binding.loginButton.setOnClickListener {
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), getString(R.string.login_fill_fields), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(
                requireContext(),
                getString(R.string.login_mock_message),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}