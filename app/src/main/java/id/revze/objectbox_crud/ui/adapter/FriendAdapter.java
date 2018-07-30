package id.revze.objectbox_crud.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.revze.objectbox_crud.CoreApplication;
import id.revze.objectbox_crud.R;
import id.revze.objectbox_crud.model.Friend;
import id.revze.objectbox_crud.model.Friend_;
import id.revze.objectbox_crud.ui.helper.InterfaceHelper;
import id.revze.objectbox_crud.ui.view.DetailActivity;
import id.revze.objectbox_crud.ui.view.EditActivity;
import io.objectbox.Box;
import io.objectbox.query.Query;
import io.objectbox.query.QueryBuilder;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendAdapterViewHolder> {
    private List<Friend> friends;
    private Context context;
    private Box friendBox;

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public FriendAdapter(Context context) {
        this.context = context;
        friendBox = ((CoreApplication) context.getApplicationContext()).getBoxStore().boxFor(Friend.class);
    }

    @NonNull
    @Override
    public FriendAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_friend, parent, false);
        return new FriendAdapterViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendAdapterViewHolder holder, final int position) {
        final Friend friend = getFriends().get(position);
        final Query<Friend> selectFriendQuery = friendBox.query().equal(Friend_.id, friend.getId()).build();
        holder.tvName.setText(friend.getName());
        holder.tvBirthDate.setText(friend.getBirthDate());
        holder.tvGender.setText((friend.getGender() == 1) ? context.getString(R.string.male_radio_option) : context.getString(R.string.female_radio_option));
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(context, EditActivity.class);
                editIntent.putExtra(EditActivity.FRIEND_ID_KEY, friend.getId());
                context.startActivity(editIntent);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InterfaceHelper.getInstance().showConfirmationDialog(context, context.getString(R.string.main_delete_friend_confirmation), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectFriendQuery.remove();
                        getFriends().remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(0, getItemCount());
                    }
                });
            }
        });
        holder.layoutFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(context, DetailActivity.class);
                detailIntent.putExtra(DetailActivity.FRIEND_ID_KEY, friend.getId());
                context.startActivity(detailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getFriends().size();
    }

    class FriendAdapterViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutFriend;
        TextView tvName, tvBirthDate, tvGender;
        Button btnEdit, btnDelete;

        public FriendAdapterViewHolder(View itemView) {
            super(itemView);
            layoutFriend = itemView.findViewById(R.id.layout_friend);
            tvName = itemView.findViewById(R.id.tv_name);
            tvBirthDate = itemView.findViewById(R.id.tv_birth_date);
            tvGender = itemView.findViewById(R.id.tv_gender);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
