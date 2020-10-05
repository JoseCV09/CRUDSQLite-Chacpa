package xyz.yoandroid.crudsqlite_chacpa.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import xyz.yoandroid.crudsqlite_chacpa.Adapters.AlumnoAdapter;
import xyz.yoandroid.crudsqlite_chacpa.Model.Alumno;
import xyz.yoandroid.crudsqlite_chacpa.Presenter.IPresenterMain;
import xyz.yoandroid.crudsqlite_chacpa.Presenter.PresenterMain;
import xyz.yoandroid.crudsqlite_chacpa.R;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IMainView {

    @BindView(R.id.floBtnAdd)
    public FloatingActionButton btnAdd;

    @BindView(R.id.toolbarMainActivity)
    Toolbar toolbar;

    @BindView(R.id.recyClientes)
    RecyclerView recyclerView;
    AlumnoAdapter adapter;
    ArrayList<Alumno> alumnos=new ArrayList<>();

    IPresenterMain presenterMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Alumnos");

        adapter=new AlumnoAdapter(alumnos,this,R.layout.row_data_client);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.addItemDecoration(new
                DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        presenterMain=new PresenterMain(this);

        llenarData();
    }

    private void llenarData() {
        getAllAlumno();
    }

    @OnClick(R.id.floBtnAdd)
    public void agregarUser() {
        goActivityAdd(DetailAlumno.class,"add");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView search = (SearchView) menu.findItem(R.id.menSearch).getActionView();
        search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<Alumno> alumno=presenterMain.filterClient(newText,alumnos);
                adapter.filterList(alumno);
                return true;
            }
        });
        return true;
    }

    @Override
    public void goActivityAdd(Class clase,String tipo) {
        Intent intent=new Intent(MainActivity.this,DetailAlumno.class);
        intent.putExtra("tipo_action",tipo);
        startActivity(intent);
    }

    @Override
    public void getAllAlumno() {
        try {
            ArrayList<Alumno> alumno=presenterMain.getAllAlumno();
            if(alumno!=null) {
                alumnos.addAll(alumno);
                adapter.notifyDataSetChanged();
            }
        }catch (Exception ex){
        }
    }

    @Override
    public void updateLista() {
        adapter.notifyDataSetChanged();
    }
}
