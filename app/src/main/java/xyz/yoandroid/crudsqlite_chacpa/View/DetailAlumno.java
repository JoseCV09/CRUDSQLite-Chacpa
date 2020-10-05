package xyz.yoandroid.crudsqlite_chacpa.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import xyz.yoandroid.crudsqlite_chacpa.Presenter.IPresenterDetail;
import xyz.yoandroid.crudsqlite_chacpa.Presenter.PresenterDetail;
import xyz.yoandroid.crudsqlite_chacpa.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailAlumno extends AppCompatActivity implements IDetailView {


    @BindView(R.id.etNombre)
    public EditText txtNombre;
    @BindView(R.id.etCorreo)
    public EditText txtCorreo;
    @BindView(R.id.etCodigo)
    public EditText txtCodigo;

    @BindView(R.id.toolbarDetailClient)
    public Toolbar toolbar;

    private Menu myMenu;
    MenuItem menuItemEdit,menuItemDone,menuItemDelete,menuItemCancel,menuItemSave;

    String nameToolbar;
    int idAlumno;

    IPresenterDetail presenterDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_client);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Agregar Clientes");
        presenterDetail=new PresenterDetail(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.myMenu=menu;
        getMenuInflater().inflate(R.menu.menu_detail_client,menu);
        setItemsMenu();
        showAction();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menSave:
                insertAlumno(txtNombre.getText().toString(),txtCorreo.getText().toString(),
                        txtCodigo.getText().toString());
                return true;
            case R.id.menEdit:
                setModeEdit();
                return true;
            case R.id.menDone:
                presenterDetail.updateAlumno(idAlumno,txtNombre.getText().toString(),txtCorreo.getText().toString(),
                        txtCodigo.getText().toString());
                return true;
            case R.id.menCancel:
                finish();
                return true;
            case R.id.menDelete:
                showDialogDelete();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showDialogDelete() {
        final AlertDialog dialog= new AlertDialog.Builder(this).create();
        dialog.setTitle("Eliminar alumno?");
        dialog.setCancelable(true);
        dialog.setMessage("Desea eliminar a este Alumno?");
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenterDetail.deleteAlumno(idAlumno,txtNombre.getText().toString());
            }
        });

        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.hide();
            }
        });
        dialog.show();
    }

    private void setItemsMenu() {
        menuItemEdit=myMenu.findItem(R.id.menEdit);
        menuItemDone=myMenu.findItem(R.id.menDone);
        menuItemDelete=myMenu.findItem(R.id.menDelete);
        menuItemCancel=myMenu.findItem(R.id.menCancel);
        menuItemSave=myMenu.findItem(R.id.menSave);
    }

    @Override
    public void setModeDetail(int id, String nombre, String correo, String codigo) {
        menuItemDone.setVisible(false);
        menuItemCancel.setVisible(true);
        menuItemEdit.setVisible(true);
        menuItemDelete.setVisible(true);
        menuItemSave.setVisible(false);
        this.nameToolbar=nombre;
        idAlumno=id;
        getSupportActionBar().setTitle("Editarás a: "+nombre);
        txtNombre.setText(nombre);
        txtCorreo.setText(correo);
        txtCodigo.setText(codigo);
        modeAction(false,R.drawable.border_edittext_block);
    }

    @Override
    public void setModeEdit() {
        menuItemDone.setVisible(true);
        menuItemCancel.setVisible(true);
        menuItemEdit.setVisible(false);
        menuItemDelete.setVisible(false);
        menuItemSave.setVisible(false);
       modeAction(true,R.drawable.border_edittext);
    }


    @Override
    public void setAddAlumno() {

        menuItemDone.setVisible(false);
        menuItemCancel.setVisible(false);
        menuItemEdit.setVisible(false);
        menuItemDelete.setVisible(false);
        menuItemSave.setVisible(true);


    }

    @Override
    public void showAction() {

        Bundle bundle=getIntent().getExtras();
        String tipo=bundle.getString("tipo_action");
        presenterDetail.showAction(tipo,bundle);

    }

    @Override
    public void insertAlumno(String nombre, String correo, String codigo) {

        Bundle bundle=getIntent().getExtras();
        String tipo=bundle.getString("tipo_action");
        presenterDetail.insertAlumno(tipo,nombre,correo,codigo);


    }

    @Override
    public void showMessage(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goActivityAdd(Class clase) {
        startActivity(new Intent(this,clase));
    }

    @Override
    public void showErrorNombre(String message) {
        txtNombre.setError(message);
    }

    @Override
    public void showErrorCorreo(String message) {
        txtCorreo.setError(message);
    }

    @Override
    public void showErrorCorreoValid(String message) {
        txtCorreo.setError(message);
    }
    @Override
    public void showErrorCodigo(String message) {
        txtCodigo.setError(message);
    }


    public void modeAction(Boolean enable, int background) {
        txtNombre.setEnabled(enable);
        txtCorreo.setEnabled(enable);
        txtCodigo.setEnabled(enable);

        txtNombre.setBackgroundResource(background);
        txtCorreo.setBackgroundResource(background);
        txtCodigo.setBackgroundResource(background);
    }
}
