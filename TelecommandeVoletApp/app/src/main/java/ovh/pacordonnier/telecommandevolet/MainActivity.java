package ovh.pacordonnier.telecommandevolet;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
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
        setSupportActionBar(toolbar);
        mainPresenter.get();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(R.layout.dialog_ip_choice)
                    .setTitle("Modifier adresse IP")
                    .setPositiveButton("Modifier", null)
                    .setNegativeButton("Annuler", null);
            final AlertDialog alertDialog = builder.create();
            final String ip = mainPresenter.getIP();
            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(final DialogInterface dialog) {
                    Button b = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    final EditText editText = (EditText) alertDialog.findViewById(R.id.edit_ip);
                    editText.setText(ip);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mainPresenter.setIP(editText.getText().toString());
                            mainPresenter.get();
                            dialog.dismiss();
                        }
                    });
                }
            });
            alertDialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
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
    public void onAllDownClick() {
        mainPresenter.allDown();
    }

    @Override
    public void onAllUpClick() {
        mainPresenter.allUp();
    }

    @Override
    public void onDownClick(Room room) {
        mainPresenter.down(room);
        Log.d("TAG", "onDownClick");
    }
}
