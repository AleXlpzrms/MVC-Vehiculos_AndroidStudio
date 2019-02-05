package com.alexlopezramos.mvc_vehiculos.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alexlopezramos.mvc_vehiculos.R;
import com.alexlopezramos.mvc_vehiculos.model.Vehiculo;

import java.util.ArrayList;

public class VehiculoAdapter extends ArrayAdapter<Vehiculo> {

    private Context context;
    private ArrayList<Vehiculo> vehiculos;
    private Vehiculo vehiculo;

    public VehiculoAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Vehiculo> objects) {
        super(context, resource, objects);
        this.context = context;
        vehiculos = objects;
    }

    @Override
    public int getCount() {
        return vehiculos.size();
    }

    @Override
    public Vehiculo getItem(int i) {
        return vehiculos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        vehiculo = (Vehiculo) getItem(i);
        view = LayoutInflater.from(context).inflate(R.layout.row, null);

        final TextView tvVehiculo = (TextView) view.findViewById(R.id.tvVehiculo);
        tvVehiculo.setText(vehiculo.getMatricula() + " - " + vehiculo.getMarca() + " " + vehiculo.getModelo());

        return view;
    }
}
