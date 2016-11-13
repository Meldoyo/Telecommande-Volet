package ovh.pacordonnier.telecommandevolet;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView{

    MainPresenter mainPresenter;

    @OnClick(R.id.button_on)
    public void buttonOnClicked() {
        mainPresenter.on();

    }

    @OnClick(R.id.button_off)
    public void buttonOffCLicked() {
        mainPresenter.off();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);
        ButterKnife.bind(this);
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
}
