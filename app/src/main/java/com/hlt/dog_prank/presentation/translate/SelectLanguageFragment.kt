package com.hlt.dog_prank.presentation.translate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hlt.dog_prank.databinding.FragmentSelectLanguageBinding
import com.hlt.dog_prank.domain.utils.mainNavController
import androidx.core.widget.addTextChangedListener

class SelectLanguageFragment : Fragment() {

    private var _binding: FragmentSelectLanguageBinding? = null
    private val binding get() = _binding!!

    private val allLanguages = listOf(
        "English",
        "French",
        "Hindi",
        "Japanese",
        "Portuguese",
        "Vietnamese",
        "Korean",
        "Turkish",
        "Spanish",
        "Italian",
    )

    private lateinit var adapter: LanguageAdapter
    private var currentLanguage = "English"

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

        binding.btnBack.setOnClickListener {
            mainNavController().popBackStack()
        }

        binding.txtCurrentLanguage.text = currentLanguage
        binding.txtCurrentLanguage.setOnClickListener {
            onLanguageSelected(currentLanguage)
        }

        adapter = LanguageAdapter { language ->
            onLanguageSelected(language)
        }

        binding.rvLanguage.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLanguage.adapter = adapter
        adapter.submitList(allLanguages)

        setupSearch()
    }

    private fun setupSearch() {

        binding.icClear.setOnClickListener {
            binding.edtSearch.text?.clear()
        }

        binding.edtSearch.addTextChangedListener { editable ->
            val query = editable.toString().trim().lowercase()
            val isSearching = query.isNotEmpty()

            binding.icClear.isVisible = isSearching
            binding.txtCurrent.isVisible = !isSearching
            binding.txtCurrentLanguage.isVisible = !isSearching
            binding.txtAll.isVisible = !isSearching

            val filtered = if (query.isEmpty()) {
                allLanguages
            } else {
                allLanguages.filter {
                    it.lowercase().contains(query)
                }
            }

            adapter.submitList(filtered)
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
