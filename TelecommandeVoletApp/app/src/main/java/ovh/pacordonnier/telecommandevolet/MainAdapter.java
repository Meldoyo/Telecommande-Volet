package ovh.pacordonnier.telecommandevolet;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ovh.pacordonnier.telecommandevolet.model.Room;

/**
 * Created by pcordonnier up 14/11/16.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RoomViewHolder> {
    private Callback callback;
    private List<Room> rooms;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        final RoomViewHolder viewHolder = new RoomViewHolder(itemView);
        viewHolder.button_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onDownClick(viewHolder.room);
            }
        });
        viewHolder.button_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onUpClick(viewHolder.room);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RoomViewHolder holder, int position) {
        holder.room = rooms.get(position);
        holder.textRoomName.setText(rooms.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (rooms == null) {
            return 0;
        }
        return rooms.size();
    }

    protected class RoomViewHolder extends RecyclerView.ViewHolder {
        public Room room;
        @BindView(R.id.text_room_name)
        TextView textRoomName;
        @BindView(R.id.button_up)
        public Button button_up;
        @BindView(R.id.button_down)
        public Button button_down;

        public RoomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface Callback {
        void onNameClick(Room room);

        void onUpClick(Room room);

        void onDownClick(Room room);
    }

}
