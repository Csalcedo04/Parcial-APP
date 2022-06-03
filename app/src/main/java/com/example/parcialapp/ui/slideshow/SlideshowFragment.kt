package com.example.parcialapp.ui.slideshow

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.parcialapp.R
import com.example.parcialapp.databinding.FragmentSlideshowBinding
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        class weatherTask(): AsyncTask<String, Void, String>(){
            override fun onPreExecute() {
                super.onPreExecute()
                root.findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
                root.findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.GONE
                root.findViewById<TextView>(R.id.errorText).visibility = View.GONE
            }

            override fun doInBackground(vararg p0: String?): String? {
                var response: String?
                try {
                    response= URL("https://api.openweathermap.org/data/2.5/weather?lat=10.370409&lon=-75.465640&appid=7d40af72d0ff7edf3b24abfd8f6f71e9&units=metric")
                        .readText(Charsets.UTF_8)
                }
                catch (e: Exception)
                {
                    response=null
                }
                return response
            }

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                try {
                    val jsonObject= JSONObject(result)
                    val main= jsonObject.getJSONObject("main")
                    val sys= jsonObject.getJSONObject("sys")
                    val wind= jsonObject.getJSONObject("wind")
                    val weather= jsonObject.getJSONArray("weather").getJSONObject(0)
                    val updateAt:Long= jsonObject.getLong("dt")
                    val updateAtText= "Update at: "+ SimpleDateFormat("dd/MM/yy  hh:mm a", Locale.ENGLISH).format(Date(updateAt*1000))
                    val temp= main.getString("temp")+"°C"
                    val tempMin= "Min Temp: "+ main.getString("temp_min")+"°C"
                    val tempMax= "Max Temp: "+ main.getString("temp_max")+"°C"
                    val pressure= main.getString("pressure")
                    val humidity= main.getString("humidity")
                    val sunrise:Long= sys.getLong("sunrise")
                    val sunset:Long= sys.getLong("sunset")
                    val windSpeed =wind.getString("speed")
                    val weatherDescription= weather.getString("description")
                    val address= jsonObject.getString("name")+ ", "+ sys.getString("country")
                    root.findViewById<TextView>(R.id.address).text=address
                    root.findViewById<TextView>(R.id.updated_at).text=updateAtText
                    root.findViewById<TextView>(R.id.status).text=weatherDescription.capitalize()
                    root.findViewById<TextView>(R.id.temp).text=temp
                    root.findViewById<TextView>(R.id.temp_min).text=tempMin
                    root.findViewById<TextView>(R.id.temp_max).text=tempMax
                    root.findViewById<TextView>(R.id.sunrise).text=SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise*1000))
                    root.findViewById<TextView>(R.id.sunset).text=SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset*1000))
                    root.findViewById<TextView>(R.id.pressure).text=pressure
                    root.findViewById<TextView>(R.id.humidity).text=humidity
                    root.findViewById<TextView>(R.id.wind).text=windSpeed
                    root.findViewById<ProgressBar>(R.id.loader).visibility= View.GONE
                    root.findViewById<RelativeLayout>(R.id.mainContainer).visibility= View.VISIBLE
                }
                catch (e: Exception){
                    root.findViewById<ProgressBar>(R.id.loader).visibility= View.GONE
                    root.findViewById<TextView>(R.id.errorText).visibility=View.VISIBLE

                }
            }
        }

        weatherTask().execute()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
