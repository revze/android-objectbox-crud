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
import id.revze.objectbox_crud.model.Friend_;
import id.revze.objectbox_crud.ui.helper.InterfaceHelper;
import io.objectbox.Box;
import io.objectbox.query.Query;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String FRIEND_ID_KEY = "friend_id";
    private long friendId = 0;
    private Context context;
    private EditText etName, etBirthDate;
    private RadioButton rbMale, rbFemale;
    private Button btnSave;
    private Box friendBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = EditActivity.this;
        friendBox = ((CoreApplication) getApplication()).getBoxStore().boxFor(Friend.class);
        etName = findViewById(R.id.et_name);
        etBirthDate = findViewById(R.id.et_birth_date);
        rbMale = findViewById(R.id.rb_male);
        rbFemale = findViewById(R.id.rb_female);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent != null) {
            friendId = intent.getLongExtra(FRIEND_ID_KEY, 0);
            Query<Friend> selectFriendQuery = friendBox.query().equal(Friend_.id, friendId).build();
            Friend friend = selectFriendQuery.findFirst();

            etName.setText(friend.getName());
            etBirthDate.setText(friend.getBirthDate());
            if (friend.getGender() == 1) rbMale.setChecked(true);
            else rbFemale.setChecked(true);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn_save) {
            String name = etName.getText().toString().trim();
            String birthDate = etBirthDate.getText().toString().trim();

            if (name.equals("")) InterfaceHelper.getInstance().showAlertDialog(context, getString(R.string.name_empty_error));
            else if (birthDate.equals("")) InterfaceHelper.getInstance().showAlertDialog(context, getString(R.string.birth_date_empty_error));
            else {
//                friend.setName(name);
//                friend.setBirthDate(birthDate);
//                friend.setGender((rbMale.isChecked()) ? 1 : 2);
                friendBox.put(new Friend(friendId, name, birthDate, (rbMale.isChecked()) ? 1 : 2));
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
