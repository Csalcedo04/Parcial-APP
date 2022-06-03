package com.example.parcialapp.ui.gallery

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.checkPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.parcialapp.databinding.FragmentGalleryBinding
import java.security.AccessController.checkPermission


class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //binding.imageButton1.setOnClickListener{(requestPermission())}
        return root
    }

    /*Deficit de funcionamiento debido a una funcion this incapaz de ser registrada
    private fun requestPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )== PackageManager.PERMISSION_GRANTED ->{
                    pickPhotoFromGallery()
                }
                else -> requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }else{
            pickPhotoFromGallery()
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){isGranted->
        if(isGranted){
            pickPhotoFromGallery()
        }
    }

    private val startForActivityGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            val data= result.data?.data
            binding.imageButton1.setImageURI(data)
        }

    }

    private fun pickPhotoFromGallery() {
        val intent= Intent(Intent.ACTION_GET_CONTENT)
        intent.type= "image/*"
        startForActivityGallery.launch(intent)
    }
    */ */

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}