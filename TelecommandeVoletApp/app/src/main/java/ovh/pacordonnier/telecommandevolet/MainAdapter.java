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

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ROOM_VIEW_TYPE = 1;
    private static final int BUTTON_VIEW_TYPE = 2;

    private Callback callback;
    private List<Room> rooms;

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < rooms.size()) {
            RoomViewHolder roomViewHolder = (RoomViewHolder) holder;
            roomViewHolder.room = rooms.get(position);
            roomViewHolder.textRoomName.setText(rooms.get(position).getCommonName());
        }
    }


    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < rooms.size()) {
            return ROOM_VIEW_TYPE;
        } else {
            return BUTTON_VIEW_TYPE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ROOM_VIEW_TYPE) {
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
        } else {
            final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_button, parent, false);
            final AllButtonsViewHolder viewHolder = new AllButtonsViewHolder(itemView);
            viewHolder.button_all_down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onAllDownClick();
                }
            });
            viewHolder.button_all_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onAllUpClick();
                }
            });
            return viewHolder;
        }
    }

    @Override
    public int getItemCount() {
        if (rooms == null) {
            return 0;
        }
        return rooms.size() + 1;
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

    protected class AllButtonsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.button_all_down)
        Button button_all_down;
        @BindView(R.id.button_all_up)
        Button button_all_up;


        public AllButtonsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public interface Callback {
        void onNameClick(Room room);

        void onUpClick(Room room);

        void onDownClick(Room room);

        void onAllDownClick();

        void onAllUpClick();
    }

}
