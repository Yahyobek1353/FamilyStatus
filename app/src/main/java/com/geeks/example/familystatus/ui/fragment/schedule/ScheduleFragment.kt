package com.geeks.example.familystatus.ui.fragment.schedule

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.geeks.example.familystatus.R
import com.geeks.example.familystatus.data.model.ScheduleDto
import com.geeks.example.familystatus.databinding.FragmentScheduleBinding
import com.geeks.example.familystatus.other.adapter.ScheduleAdapter
import com.geeks.example.familystatus.ui.App
import com.google.gson.Gson

class ScheduleFragment : Fragment(), ScheduleAdapter.Clickable {
    private lateinit var binding: FragmentScheduleBinding
    private var adapter: ScheduleAdapter? = null
    private var NavHostFragment: NavHostFragment? = null




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding1 = FragmentScheduleBinding.inflate(inflater, container, false)
        binding = binding1

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ScheduleAdapter(this)
        binding.rvNotes.adapter = adapter
        val list = App.db.getDao().getAllNotes()
        adapter!!.addNote(list)
        NavHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as
                    NavHostFragment
        val navController = NavHostFragment!!.navController

        binding.btnAdd?.setOnClickListener {
            navController.navigate(R.id.addScheduleFragment)
        }
        binding.btnSort?.setOnClickListener{adapter!!.sortNotes() }
        requireActivity().supportFragmentManager.setFragmentResultListener(
            "change_note", this
        ) { _, result ->
            val note: ScheduleDto? = result.getSerializable("edit_note") as ScheduleDto?
            note?.let { adapter!!.changeNote(it, result.getInt("position")) }


        }

    }

    override fun edit(position: Int) {
        val note: ScheduleDto = adapter!!.getList()!![position]
        val bundle = Bundle()
        note.title.let {  Log.e("ololo",it )}
        bundle.putString("changeTitle", note.title)
        bundle.putString("changeDesc", note.desc)
        bundle.putString("changeDate", note.date)
        bundle.putInt("position", position)

        val navController = NavHostFragment?.navController
        navController?.navigate(R.id.addScheduleFragment, bundle)
    }

    override fun delete(position: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle("Удалить заметку?")
            .setMessage("Если удалить нкиогда не вернешь!")
            .setNegativeButton("Нет",null)
            .setPositiveButton("Да,я уверен!"){_, _ ->
                adapter?.removeNotes(position)
            }
            .show()

    }
    private fun convertObjectToJson(note: ScheduleDto): String {
        val gson = Gson()
        return gson.toJson(note)
    }
    override fun share(note: ScheduleDto) {
        val gsonNote = convertObjectToJson(note)
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, gsonNote)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)

    }


}