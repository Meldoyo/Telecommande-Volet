package ovh.pacordonnier.telecommandevolet;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ovh.pacordonnier.telecommandevolet.model.Room;

public class MainActivity extends AppCompatActivity implements MainView, MainAdapter.Callback{

    MainPresenter mainPresenter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);
        ButterKnife.bind(this);
        setupRecyclerView();
        mainPresenter.get();
    }

    private void setupRecyclerView() {
        adapter = new MainAdapter();
        adapter.setCallback(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void resultGet(List<Room> rooms) {
        adapter.setRooms(rooms);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void resultOK() {
        Toast.makeText(this, "OK !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void resultNotOk() {
        Toast.makeText(this, "Not OK !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onNameClick(Room room) {

    }

    @Override
    public void onUpClick(Room room) {
        mainPresenter.up(room);
        Log.d("TAG", "onUpClick");
    }

    @Override
    public void onDownClick(Room room) {
        mainPresenter.down(room);
        Log.d("TAG", "onDownClick");
    }
}
