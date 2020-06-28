package com.ambient.stargaze.ui.home

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
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
import java.util.*


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

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

        viewModel.getPictureOfTheDay(StringUtils.formatDateToString(Date(System.currentTimeMillis())))

        binding.imCalender.setOnClickListener{ showCalenderDialog() }
        binding.ivZoom.setOnClickListener{ zoomImage() }

    }

    private fun zoomImage() {
        binding.ivZoom.scaleType = ImageView.ScaleType.CENTER_CROP
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