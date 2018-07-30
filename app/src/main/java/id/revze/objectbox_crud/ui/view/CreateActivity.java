package id.revze.objectbox_crud.ui.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import id.revze.objectbox_crud.CoreApplication;
import id.revze.objectbox_crud.R;
import id.revze.objectbox_crud.model.Friend;
import id.revze.objectbox_crud.ui.helper.InterfaceHelper;
import io.objectbox.Box;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private EditText etName, etBirthDate;
    private RadioButton rbMale, rbFemale;
    private Button btnSave;
    private Box friendBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = CreateActivity.this;
        friendBox = ((CoreApplication) getApplication()).getBoxStore().boxFor(Friend.class);
        etName = findViewById(R.id.et_name);
        etBirthDate = findViewById(R.id.et_birth_date);
        rbMale = findViewById(R.id.rb_male);
        rbFemale = findViewById(R.id.rb_female);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn_save) {
            String name = etName.getText().toString().trim();
            String birthDate = etBirthDate.getText().toString().trim();
            boolean selectedGenderIsEmpty = !rbMale.isChecked() && !rbFemale.isChecked();

            if (name.equals("")) InterfaceHelper.getInstance().showAlertDialog(context, getString(R.string.name_empty_error));
            else if (birthDate.equals("")) InterfaceHelper.getInstance().showAlertDialog(context, getString(R.string.birth_date_empty_error));
            else if (selectedGenderIsEmpty) InterfaceHelper.getInstance().showAlertDialog(context, getString(R.string.gender_empty_error));
            else {
                friendBox.put(new Friend(0, etName.getText().toString(),
                                            etBirthDate.getText().toString(), (rbMale.isChecked()) ? 1 : 2));
                Intent mainIntent = new Intent(context, MainActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mainIntent);
                finish();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) finish();

        return super.onOptionsItemSelected(item);
    }
}
