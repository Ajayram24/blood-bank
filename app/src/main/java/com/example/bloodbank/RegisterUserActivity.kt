import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.bloodbank.R

class RegisterUserActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var bloodGroups: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        spinner = findViewById(R.id.spinnerBloodGroup)
        bloodGroups = arrayOf("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, bloodGroups)
        spinner.adapter = adapter

        val btnRegister = findViewById<Button>(R.id.btnRegister)
        btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val name = findViewById<EditText>(R.id.etName).text.toString()
        val dob = findViewById<EditText>(R.id.etDOB).text.toString()
        val phone = findViewById<EditText>(R.id.etPhone).text.toString()
        val email = findViewById<EditText>(R.id.etEmail).text.toString()
        val bloodGroup = spinner.selectedItem.toString()
        val lastDonation = findViewById<EditText>(R.id.etLastDonation).text.toString()
        val address = findViewById<EditText>(R.id.etAddress).text.toString()
        val city = findViewById<EditText>(R.id.etCity).text.toString()
        val state = findViewById<EditText>(R.id.etState).text.toString()
        val zip = findViewById<EditText>(R.id.etZip).text.toString()

        val selectedGenderId = findViewById<RadioGroup>(R.id.rgGender).checkedRadioButtonId
        val gender = findViewById<RadioButton>(selectedGenderId)?.text.toString()

        val user = UserModel(name, dob, gender, phone, email, bloodGroup, lastDonation, address, city, state, zip)


    }

    class UserModel(
        name: String,
        dob: String,
        gender: String,
        phone: String,
        email: String,
        bloodGroup: String,
        lastDonation: String,
        address: String,
        city: String,
        state: String,
        zip: String
    ) {
        fun zip() {

        }

        val state: Any = TODO()
        val city: Any
        val address: Any
        val lastDonation: Any
        val bloodGroup: Any
        val email: Any
        val phone: Any
        val gender: Any
        val name: Any
        val dob: Any = TODO()
    }
}

class Toast {
    object LENGTH_LONG {

    }

    companion object {
        fun makeText(registerUserActivity: RegisterUserActivity, message: Any, lengthLong: LENGTH_LONG) {

        }
    }

}
