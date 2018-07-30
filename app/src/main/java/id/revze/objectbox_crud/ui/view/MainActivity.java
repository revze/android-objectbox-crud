package id.revze.objectbox_crud.ui.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.revze.objectbox_crud.CoreApplication;
import id.revze.objectbox_crud.R;
import id.revze.objectbox_crud.model.Friend;
import id.revze.objectbox_crud.ui.adapter.FriendAdapter;
import io.objectbox.Box;
import io.objectbox.BoxStore;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private Box friendBox;
    private RecyclerView rvFriend;
    private FriendAdapter friendAdapter;
    private ArrayList<Friend> friends = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        friendBox = ((CoreApplication) getApplication()).getBoxStore().boxFor(Friend.class);
        rvFriend = findViewById(R.id.rv_friend);
        friendAdapter = new FriendAdapter(context);
        LinearLayoutManager friendAdapterLayoutManager = new LinearLayoutManager(context);
        friendAdapter.setFriends(friends);
        rvFriend.setLayoutManager(friendAdapterLayoutManager);
        rvFriend.setAdapter(friendAdapter);
        friends.addAll(friendBox.query().build().find());
        friendAdapter.notifyItemRangeChanged(0, friends.size());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_create) {
            Intent createIntent = new Intent(context, CreateActivity.class);
            startActivity(createIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
