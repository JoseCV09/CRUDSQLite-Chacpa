package xyz.yoandroid.crudsqlite_chacpa.Presenter;

import android.os.Bundle;

public interface IPresenterDetail {

    void showAction(String tipo, Bundle bundle);

    void insertAlumno(String tipo, String nombre, String correo, String codigo);

    void showMessage(String message);

    void updateAlumno(int id, String nombre, String correo, String codigo);

    void goActivityAdd(Class clase);

    void deleteAlumno(int id, String nombre);

    void showErrorNombre();

    void showErrorCorreo();

    void showErrorCodigo();

    void showErrorCorreoValid();



}
