package com.example.parcialapp.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.parcialapp.MainActivity
import com.example.parcialapp.R
import com.example.parcialapp.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val newsViewModel =
            ViewModelProvider(this).get(NewsViewModel::class.java)

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNews
        newsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        val myWebView: WebView = root.findViewById(R.id.noticia)
        myWebView.loadUrl("https://www.nytimes.com/es/")
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}