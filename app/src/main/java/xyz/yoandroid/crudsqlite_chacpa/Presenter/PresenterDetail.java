package xyz.yoandroid.crudsqlite_chacpa.Presenter;

import android.os.Bundle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import xyz.yoandroid.crudsqlite_chacpa.BD.SqliteStringHelpers;
import xyz.yoandroid.crudsqlite_chacpa.Model.Alumno;
import xyz.yoandroid.crudsqlite_chacpa.Model.IAlumno;
import xyz.yoandroid.crudsqlite_chacpa.View.IDetailView;

public class PresenterDetail implements IPresenterDetail {


    IDetailView detailView;
    IAlumno modelAlumno;

    public PresenterDetail(IDetailView detailView) {
        this.detailView = detailView;
        this.modelAlumno=new Alumno(this,detailView);
    }

    @Override
    public void showAction(String tipo, Bundle bundle) {

        if(detailView!=null) {
            if(tipo.equals("add")) {
                detailView.setAddAlumno();
            }
            else {
                String nombre=bundle.getString(SqliteStringHelpers.CAMPO_NOMBRE);
                String correo= bundle.getString(SqliteStringHelpers.CAMPO_CORREO);
                String codigo= bundle.getString(SqliteStringHelpers.CAMPO_CODIGO);
                int idAlumno=bundle.getInt(SqliteStringHelpers.CAMPO_ID);
                detailView.setModeDetail(idAlumno, nombre,correo,codigo);
            }
        }
    }


    @Override
    public void insertAlumno(String tipo, String nombre, String correo, String codigo) {

        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(correo);

        if(detailView!=null) {
            if (tipo.equals("add")) {
                if (nombre.trim().equals("")) {
                    showErrorNombre();
                } else {
                    if (correo.trim().equals("")) {
                        showErrorCorreo();
                    } else {
                        if(mather.find() == false){
                            showErrorCorreoValid();
                        }else{
                            if (codigo.trim().equals("")) {
                                showErrorCodigo();
                            } else {
                                modelAlumno.insertAlumno(nombre, correo, codigo);
                            }
                        }
                    }
                }
            }
        }
    }



    @Override
    public void showMessage(String message) {

        if(detailView!=null) {
            detailView.showMessage(message);
        }
    }

    @Override
    public void updateAlumno(int id, String nombre, String correo, String codigo) {

        if(detailView!=null) {
            String idA= String.valueOf(id);
            modelAlumno.updateAlumno(idA,nombre,correo,codigo);
        }
    }

    @Override
    public void goActivityAdd(Class clase) {
        detailView.goActivityAdd(clase);
    }

    @Override
    public void deleteAlumno(int id, String name) {
        if(detailView!=null) {
            String idA= String.valueOf(id);
            modelAlumno.deleteAlumno(idA,name);
        }
    }

    @Override
    public void showErrorNombre() {
        detailView.showErrorNombre("Inserta un Nombre");
    }

    @Override
    public void showErrorCorreo() {
        detailView.showErrorCorreo("Inserta un Correo");
    }

    @Override
    public void showErrorCodigo() {
        detailView.showErrorCodigo("Inserta un Codigo");
    }

    @Override
    public void showErrorCorreoValid() {
        detailView.showErrorCorreoValid("Correo Inválido");
    }




}
