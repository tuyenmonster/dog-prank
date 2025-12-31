package com.hlt.dog_prank.presentation.translate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hlt.dog_prank.databinding.FragmentSelectLanguageBinding
import com.hlt.dog_prank.domain.utils.mainNavController

class SelectLanguageFragment : Fragment() {

    private var _binding: FragmentSelectLanguageBinding? = null
    private val binding get() = _binding!!

    private val languages = listOf(
        "English",
        "Vietnamese",
        "Japanese",
        "Korean",
        "Chinese",
        "Spanish",
        "French"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectLanguageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {

        // Back
        binding.btnBack.setOnClickListener {
            mainNavController().popBackStack()
        }

        // RecyclerView
        binding.rvLanguage.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = LanguageAdapter(languages) { language ->
                onLanguageSelected(language)
            }
        }
    }

    private fun onLanguageSelected(language: String) {
        parentFragmentManager.setFragmentResult(
            RecordHumanFragment.REQ_LANGUAGE,
            Bundle().apply {
                putString(RecordHumanFragment.KEY_LANGUAGE, language)
            }
        )
        mainNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
