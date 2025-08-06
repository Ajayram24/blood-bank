import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bloodbank.DonationHistoryAdapter
import com.example.bloodbank.R
import com.example.bloodbank.api.DonationRecord

class AdminDonationHistoryActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DonationHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_donation_history)

        recyclerView = findViewById(R.id.recyclerDonationHistory)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val sampleData = listOf(
            DonationRecord("Sarah Johnson", 28, "O+", "June 15, 2023", "Sep 15, 2023", "Memorial Hospital", "Eligible"),
            DonationRecord("Michael Chen", 35, "B-", "May 20, 2023", "Aug 20, 2023", "St. Luke's Medical Center", "Eligible"),
            DonationRecord("Emma Williams", 42, "A-", "Jul 1, 2023", "Oct 1, 2023", "City General Hospital", "Waiting")
        )

        adapter = DonationHistoryAdapter(sampleData)
        recyclerView.adapter = adapter
    }
}
