package com.example.uicontrolll;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Declare variables
    EditText nameEdit, emailEdit, phoneEdit, addressEdit;
    RadioGroup genderGroup;
    CheckBox danceCheck, shootingCheck, singingCheck, readingCheck;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views with updated IDs
        nameEdit = findViewById(R.id.editTextName);
        emailEdit = findViewById(R.id.editTextEmail);
        phoneEdit = findViewById(R.id.editTextPhone);
        addressEdit = findViewById(R.id.editTextAddress);
        genderGroup = findViewById(R.id.genderGroup);
        danceCheck = findViewById(R.id.checkBoxDance);
        shootingCheck = findViewById(R.id.checkBoxShooting);
        singingCheck = findViewById(R.id.checkBoxSinging);
        readingCheck = findViewById(R.id.checkBoxReading);
        submitButton = findViewById(R.id.buttonSubmit);

        // Set onClickListener for the Submit button
        submitButton.setOnClickListener(v -> validateForm());
    }

    private void validateForm() {
        // Get input values
        String name = nameEdit.getText().toString().trim();
        String email = emailEdit.getText().toString().trim();
        String phone = phoneEdit.getText().toString().trim();
        String address = addressEdit.getText().toString().trim();

        // Validate Name
        if (TextUtils.isEmpty(name)) {
            nameEdit.setError("Name is required");
            nameEdit.requestFocus();
            return;
        }

        // Validate Email
        if (TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEdit.setError("Enter a valid email");
            emailEdit.requestFocus();
            return;
        }

        // Validate Phone
        if (TextUtils.isEmpty(phone) || phone.length() < 10) {
            phoneEdit.setError("Enter a valid phone number");
            phoneEdit.requestFocus();
            return;
        }

        // Validate Address
        if (TextUtils.isEmpty(address)) {
            addressEdit.setError("Address is required");
            addressEdit.requestFocus();
            return;
        }

        // Validate Gender selection
        int selectedGenderId = genderGroup.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedGenderRadio = findViewById(selectedGenderId);
        String gender = selectedGenderRadio.getText().toString();

        // Collect hobbies
        StringBuilder hobbies = new StringBuilder();
        if (danceCheck.isChecked()) hobbies.append("Dance, ");
        if (shootingCheck.isChecked()) hobbies.append("Sharp Shooting, ");
        if (singingCheck.isChecked()) hobbies.append("Singing, ");
        if (readingCheck.isChecked()) hobbies.append("Reading, ");

        // Remove trailing comma if hobbies are selected
        if (hobbies.length() > 0) {
            hobbies.setLength(hobbies.length() - 2); // Remove the last ", "
        } else {
            Toast.makeText(this, "Select at least one hobby", Toast.LENGTH_SHORT).show();
            return;
        }

        // Prepare final message
        String message = "Name: " + name +
                "\nEmail: " + email +
                "\nPhone: " + phone +
                "\nAddress: " + address +
                "\nGender: " + gender +
                "\nHobbies: " + hobbies;

        // Display the form submission details in a Toast message
        Toast.makeText(this, "Form Submitted:\n" + message, Toast.LENGTH_LONG).show();
    }
}
