package com.architectural.components.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.architectural.components.R
import com.architectural.components.adapters.CustomAdapterHome
import com.architectural.components.databinding.FragmentHomeBinding
import com.architectural.components.interfaces.OnItemClick
import com.architectural.components.model.Note
import com.architectural.components.view_models.FragmentHomeViewModel

class FragmentHome : Fragment(), OnItemClick {

    private var binding: FragmentHomeBinding? = null
    private var noteList: List<Note> = ArrayList()
    private val navController: NavController
        get() {
            return findNavController()
        }
    private lateinit var viewModel: FragmentHomeViewModel

    private fun initializations() {
        viewModel = ViewModelProvider(this)[FragmentHomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializations()
        fillData()

        binding!!.fabAddHome.setOnClickListener { onAddClick() }
    }

    private fun fillData() {
        noteList = viewModel.noteList
        val adapter = CustomAdapterHome(noteList, this)
        binding?.rvHome?.adapter = adapter
    }

    private fun onAddClick() {
        if (navController.currentDestination?.id == R.id.fragmentHome) {
            navController.navigate(R.id.action_fragmentHome_to_fragmentAdd)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onItemClickListener(position: Int) {
        if (navController.currentDestination?.id == R.id.fragmentHome) {
            val action =
                FragmentHomeDirections.actionFragmentHomeToFragmentAdd(noteList[position])
            navController.navigate(action)
        }
    }
}