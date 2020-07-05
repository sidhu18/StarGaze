package com.ambient.stargaze.ui.home

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ambient.stargaze.R
import com.ambient.stargaze.databinding.FragmentHomeBinding
import com.ambient.stargaze.helpers.StringUtils
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class HomeFragment : Fragment(){

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val DEFAUT_MAXLINES = 6
    private val EXPANDED_MAXLINES = 200
    private lateinit var youTubePlayer2: YouTubePlayer
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        lifecycle.addObserver(binding.youtubePlayerView)
        initViewListeners()

        viewModel.apodResponse.observe(viewLifecycleOwner, androidx.lifecycle.Observer { it ->
            it?.let {
                if(it.mediaType == "video") {
                    it.url?.let { it1 -> StringUtils.getYoutubeVideoId(it1) }?.let { it2 ->
                        youTubePlayer2.cueVideo(
                            it2, 0f
                        )
                    }
                }
            }
        })


    }

    private fun initViewListeners() {
        binding.imCalender.setOnClickListener{ showCalenderDialog() }
        binding.ivZoom.setOnClickListener{ zoomImage() }
        binding.tvDesc.setOnClickListener{ expandOrCollapseText() }
        binding.ivPlay.setOnClickListener{ playVideo() }
        binding.ivPause.setOnClickListener{ pauseVideo() }


        binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer2 = youTubePlayer
                youTubePlayer2.cueVideo("",0f)
            }

            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
                super.onStateChange(youTubePlayer, state)
                when(state){
                    PlayerConstants.PlayerState.PLAYING -> setPlayingState()
                    PlayerConstants.PlayerState.PAUSED -> setPauseState()
                }
            }
        })
    }

    private fun playVideo(){
        setPlayingState()
        youTubePlayer2.play()
    }

    private fun setPlayingState(){
        ivPlay.visibility = View.GONE
        ivPause.visibility = View.VISIBLE
    }

    private fun pauseVideo(){
        setPauseState()
        youTubePlayer2.pause()
    }

    private fun setPauseState(){
        ivPlay.visibility = View.VISIBLE
        ivPause.visibility = View.GONE
    }

    private fun zoomImage() {
        if(binding.imageView3.scaleType == ImageView.ScaleType.FIT_CENTER){
            binding.imageView3.scaleType = ImageView.ScaleType.CENTER_CROP
        } else
            binding.imageView3.scaleType = ImageView.ScaleType.FIT_CENTER
    }

    private fun expandOrCollapseText(){
        if(binding.tvDesc.maxLines == DEFAUT_MAXLINES)
            binding.tvDesc.maxLines = EXPANDED_MAXLINES
        else
            binding.tvDesc.maxLines = DEFAUT_MAXLINES
    }

    private fun showCalenderDialog(){
        // Get Current Date

        // Get Current Date
        val c: Calendar = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)


        val datePickerDialog = DatePickerDialog(
            this.requireContext(),
            OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                viewModel.fetchApodByDate(year.toString() + "-"+ checkDigit(monthOfYear + 1).toString() + "-"+ checkDigit(dayOfMonth).toString())
            },
            mYear,
            mMonth,
            mDay
        )
        datePickerDialog.datePicker.minDate = 836000832000
        datePickerDialog.datePicker.maxDate = c.timeInMillis

        datePickerDialog.show()
    }

    private fun checkDigit(number: Int): String? {
        return if (number <= 9) "0$number" else number.toString()
    }
}