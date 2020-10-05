package xyz.yoandroid.crudsqlite_chacpa.Presenter;


import java.util.ArrayList;

import xyz.yoandroid.crudsqlite_chacpa.Model.Alumno;

public interface IPresenterMain {

    ArrayList<Alumno> getAllAlumno();

    void updateLista();

    ArrayList<Alumno> filterClient(String text, ArrayList<Alumno> oldList);


}
