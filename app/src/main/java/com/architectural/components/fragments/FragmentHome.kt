package com.architectural.components.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.architectural.components.R
import com.architectural.components.adapters.CustomAdapterHome
import com.architectural.components.databinding.FragmentHomeBinding
import com.architectural.components.interfaces.OnItemClick
import com.architectural.components.model.Note
import com.architectural.components.utils.Constants.Companion.TAG
import com.architectural.components.view_models.FragmentHomeViewModel

class FragmentHome : Fragment(), OnItemClick {

    private var binding: FragmentHomeBinding? = null
    private var noteList: ArrayList<Note> = ArrayList()
    private var adapterHome: CustomAdapterHome? = null
    private lateinit var viewModel: FragmentHomeViewModel
    private val navController: NavController
        get() {
            return findNavController()
        }

    private fun initializations() {
        viewModel = ViewModelProvider(this)[FragmentHomeViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializations()
        initializeRecyclerView()
        fillData()

        binding!!.fabAddHome.setOnClickListener { onAddClick() }
    }

    private fun initializeRecyclerView() {
        adapterHome = CustomAdapterHome(noteList, this)
        binding!!.rvHome.adapter = adapterHome
    }

    private fun fillData() {
        Log.d(TAG, "fillData: called fill")
        val observer: Observer<List<Note>> = Observer {
            Log.d(TAG, "fillData: called $it")
            noteList.clear()
            noteList.addAll(it)
            adapterHome!!.notifyDataSetChanged()
        }
        viewModel.noteList.observe(viewLifecycleOwner, observer)
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