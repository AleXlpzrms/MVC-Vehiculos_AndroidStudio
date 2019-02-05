package com.alexlopezramos.mvc_vehiculos.view;

import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.alexlopezramos.mvc_vehiculos.R;
import com.alexlopezramos.mvc_vehiculos.adapter.VehiculoAdapter;
import com.alexlopezramos.mvc_vehiculos.controller.VehiculoController;
import com.alexlopezramos.mvc_vehiculos.model.Vehiculo;

import java.util.ArrayList;

public class VehiculoView extends AppCompatActivity implements View.OnClickListener {

    private VehiculoController controller;
    private ArrayList<Vehiculo> vehiculos;
    private Vehiculo vehiculo;
    private EditText etMatricula, etMarca, etModelo;
    private Button btnAdd, btnUpdate, btnDelete;
    private ListView lvVehiculos;
    private VehiculoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_vehiculo);

        //Controller
        controller = new VehiculoController(this);

        initComponents();

        llenarLista();
    }

    //Inicializar Componentes
    private void initComponents() {
        etMatricula = (EditText) findViewById(R.id.etMatricula);
        etMarca = (EditText) findViewById(R.id.etMarca);
        etModelo = (EditText) findViewById(R.id.etModelo);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        lvVehiculos = (ListView) findViewById(R.id.lvVehiculos);

        btnAdd.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    //Llenar ListView con BD
    public void llenarLista() {
        vehiculos = controller.getVehiculos();
        adapter = new VehiculoAdapter(this, R.layout.row, controller.getVehiculos());
        lvVehiculos.setAdapter(adapter);
        lvVehiculos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vehiculo = vehiculos.get(position);
                etMatricula.setText(vehiculo.getMatricula());
                etMarca.setText(vehiculo.getMarca());
                etModelo.setText(vehiculo.getModelo());
            }
        });
    }

    //Botones
    @Override
    public void onClick(View view) {
        String matricula, marca, modelo;
        matricula = etMatricula.getText().toString();
        marca = etMarca.getText().toString();
        modelo = etModelo.getText().toString();
        if(!matricula.equals("") || !marca.equals("") || !modelo.equals("")) {
            if(matricula.length() == 7) {
                vehiculo = new Vehiculo(matricula, marca, modelo);
                switch (view.getId()) {
                    case R.id.btnAdd:
                        try {
                            boolean matriculaExists = controller.insertVehiculo(vehiculo);
                            if(!matriculaExists) {
                                Toast.makeText(this, "Vehiculo añadido", Toast.LENGTH_SHORT).show();
                                llenarLista();
                            }
                            else
                                Toast.makeText(this, "La matricula ya existe", Toast.LENGTH_SHORT).show();
                        } catch(SQLiteException sqle) {
                            Toast.makeText(this, sqle.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.btnUpdate:
                        try {
                            boolean matriculaExists = controller.updateVehiculo(vehiculo);
                            if(matriculaExists) {
                                Toast.makeText(this, "Vehiculo actualizado", Toast.LENGTH_SHORT).show();
                                llenarLista();
                            }
                            else
                                Toast.makeText(this, "La matricula no existe", Toast.LENGTH_SHORT).show();
                        } catch(SQLiteException sqle) {
                            Toast.makeText(this, sqle.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.btnDelete:
                        try {
                            boolean matriculaExists = controller.deleteVehiculo(vehiculo);
                            if(matriculaExists) {
                                Toast.makeText(this, "Vehiculo eliminado", Toast.LENGTH_SHORT).show();
                                llenarLista();
                            }
                            else
                                Toast.makeText(this, "La matricula no existe", Toast.LENGTH_SHORT).show();
                        } catch(SQLiteException sqle) {
                            Toast.makeText(this, sqle.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            } else {
                Toast.makeText(this, "La matricula debe constar de 7 caracteres alfanuméricos", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Por favor rellena todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}
