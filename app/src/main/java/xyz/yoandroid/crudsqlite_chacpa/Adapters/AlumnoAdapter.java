package xyz.yoandroid.crudsqlite_chacpa.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import xyz.yoandroid.crudsqlite_chacpa.BD.SqliteStringHelpers;
import xyz.yoandroid.crudsqlite_chacpa.R;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.yoandroid.crudsqlite_chacpa.Model.Alumno;
import xyz.yoandroid.crudsqlite_chacpa.View.DetailAlumno;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.AlumnosViewholder> {

    private ArrayList<Alumno> alumnos=new ArrayList<>();
    private Activity activity;
    private int layout;

    public AlumnoAdapter(ArrayList<Alumno> alumnos, Activity activity, int layout) {
        this.alumnos = alumnos;
        this.activity = activity;
        this.layout = layout;
    }

    @NonNull
    @Override
    public AlumnosViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        return new AlumnosViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnosViewholder holder, int position) {


            final Alumno alumno = alumnos.get(position);


            holder.txtNombre.setText(alumno.getNombre());
            holder.txtCorreo.setText(alumno.getCorreo());
            holder.txtCodigo.setText(alumno.getCodigo());
            String id= String.valueOf(alumno.getId());
            Log.e("IdAlumno",id);

            holder.cardContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent= new Intent(activity, DetailAlumno.class);

                    intent.putExtra("tipo_action","edit");
                    intent.putExtra(SqliteStringHelpers.CAMPO_NOMBRE,alumno.getNombre());
                    intent.putExtra(SqliteStringHelpers.CAMPO_CORREO,alumno.getCorreo());
                    intent.putExtra(SqliteStringHelpers.CAMPO_CODIGO,alumno.getCodigo());
                    intent.putExtra(SqliteStringHelpers.CAMPO_ID,alumno.getId());
                    activity.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }

    public class AlumnosViewholder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardContent)
        CardView cardContent;
        @BindView(R.id.itmNombre)
        TextView txtNombre;
        @BindView(R.id.itmCorreo)
        TextView txtCorreo;
        @BindView(R.id.itmCodigo)
        TextView txtCodigo;


        public AlumnosViewholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void filterList(ArrayList<Alumno> newList) {
        alumnos = new ArrayList<>();
        alumnos.addAll(newList);
        notifyDataSetChanged();
    }
}
