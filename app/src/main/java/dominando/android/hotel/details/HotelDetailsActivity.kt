package dominando.android.hotel.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dominando.android.hotel.R
import dominando.android.hotel.databinding.ActivityHotelDetailsBinding
import dominando.android.hotel.form.HotelFormFragment
import dominando.android.hotel.model.Hotel

class HotelDetailsActivity : AppCompatActivity(), HotelFormFragment.OnHotelSavedListener {

    private val hotelId: Long by lazy { intent.getLongExtra(EXTRA_HOTEL_ID, -1) }

    private lateinit var binding: ActivityHotelDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotelDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            showHotelDetailsFragment()
        }
    }

    override fun onHotelSaved(hotel: Hotel) {
        setResult(RESULT_OK)
        showHotelDetailsFragment()
    }

    private fun showHotelDetailsFragment() {
        val fragment = HotelDetailsFragment.newInstance(hotelId)
        supportFragmentManager.beginTransaction().replace(
            R.id.details,
            fragment,
            HotelDetailsFragment.TAG_DETAILS
        ).commit()
    }

    companion object {
        private const val EXTRA_HOTEL_ID = "hotel_id"
        fun open(activity: Activity, hotelId: Long) {
            activity.startActivityForResult(
                Intent(
                    activity,
                    HotelDetailsActivity::class.java
                ).apply {
                    putExtra(EXTRA_HOTEL_ID, hotelId)
                }, 0
            )
        }
    }
}
