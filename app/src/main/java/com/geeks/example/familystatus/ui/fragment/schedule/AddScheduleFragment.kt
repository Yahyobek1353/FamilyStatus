package com.geeks.example.familystatus.ui.fragment.schedule

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.geeks.example.familystatus.R
import com.geeks.example.familystatus.data.model.ScheduleDto
import com.geeks.example.familystatus.databinding.FragmentAddScheduleBinding
import com.geeks.example.familystatus.ui.App
import java.io.FileNotFoundException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddScheduleFragment : Fragment() {
    private lateinit var binding: FragmentAddScheduleBinding
    private var navHostFragment: NavHostFragment? = null




    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding1 = FragmentAddScheduleBinding.inflate(inflater, container, false)
        binding = binding1

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as
                    NavHostFragment

        val navController = navHostFragment!!.navController
        val bundle = Bundle()
        val db = App.db

        if (arguments != null) {
            binding.etTitle.setText(requireArguments().getString("changeTitle"))
            binding.etDes.setText(requireArguments().getString("changeDesc"))
            binding.etDate.setText(requireArguments().getString("changeDate"))
            //Инициализация изменения заметок
            binding.btnSave.setOnClickListener(View.OnClickListener {
                val changeTitle = binding.etTitle.text.toString()
                val changeDesc = binding.etDes.text.toString()
                val position = requireArguments().getInt("position")
                val sdf = SimpleDateFormat("dd/MM/yyyy_HH:mm", Locale.getDefault())
                val changeDate = sdf.format(Date())
                val note = ScheduleDto(null,changeTitle, changeDate, changeDesc)
                bundle.putSerializable("edit_note", note)
                bundle.putInt("position", position)
                requireActivity().supportFragmentManager.setFragmentResult("change_note", bundle)
                requireActivity().supportFragmentManager.popBackStack()
            })
        } else {

            //Инициализация сохранения новых заметок
            binding.btnSave.setOnClickListener(View.OnClickListener {
                val notes = ScheduleDto(null,
                    binding.etTitle.text.toString(),
                    binding.etDate.text.toString(),
                    binding.etDes.text.toString()
                )
                Thread{
                    db.getDao().insertNotes(notes)
                }.start()




                val title = binding.etTitle.text.toString()
                val desc = binding.etDes.text.toString()
                val sdf = SimpleDateFormat("dd/MM/yyyy_HH:mm", Locale.getDefault())
                val date = sdf.format(Date())
                val note = ScheduleDto(null,title,date, desc )
                bundle.putSerializable("note", note)
                requireActivity().supportFragmentManager.setFragmentResult("new_note", bundle)
                requireActivity().supportFragmentManager.popBackStack()
            })
        }
        binding.imgAdd.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, 1)
        }


    }

    override fun onActivityResult(reqCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(reqCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            try {
                val imageUri = data?.data
                val imageStream = requireActivity().contentResolver.openInputStream(
                    imageUri!!
                )
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                binding.imgAdd!!.setImageBitmap(selectedImage)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                Toast.makeText(requireActivity(), "Something went wrong", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(requireActivity(), "You haven't picked Image", Toast.LENGTH_LONG).show()
        }
    }

}