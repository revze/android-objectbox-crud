package id.revze.objectbox_crud.ui.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import id.revze.objectbox_crud.CoreApplication;
import id.revze.objectbox_crud.R;
import id.revze.objectbox_crud.model.Friend;
import id.revze.objectbox_crud.model.Friend_;
import io.objectbox.Box;
import io.objectbox.query.Query;

public class DetailActivity extends AppCompatActivity {

    public static final String FRIEND_ID_KEY = "friend_id";
    private Box friendBox;
    private long friendId = 0;
    private Context context;
    private TextView tvName, tvBirthDate, tvGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = DetailActivity.this;
        friendBox = ((CoreApplication) getApplication()).getBoxStore().boxFor(Friend.class);
        tvName = findViewById(R.id.tv_name);
        tvBirthDate = findViewById(R.id.tv_birth_date);
        tvGender = findViewById(R.id.tv_gender);

        Intent intent = getIntent();
        if (intent != null) {
            friendId = intent.getLongExtra(FRIEND_ID_KEY, 0);
            Query<Friend> selectFriendQuery = friendBox.query().equal(Friend_.id, friendId).build();
            Friend friend = selectFriendQuery.findFirst();

            tvName.setText(getString(R.string.detail_name_info) + friend.getName());
            tvBirthDate.setText(getString(R.string.detail_birth_date_info) + friend.getBirthDate());
            tvGender.setText(getString(R.string.detail_gender_info) + (friend.getGender() == 1 ? getString(R.string.male_radio_option) : getString(R.string.female_radio_option)));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) finish();

        return super.onOptionsItemSelected(item);
    }
}
