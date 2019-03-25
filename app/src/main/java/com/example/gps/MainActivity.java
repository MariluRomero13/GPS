package com.example.gps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LocationManager lm;
    LocationListener lli;
    LocationProvider lp;
    TextView txt1, txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        //lp = lm.getProvider(LocationManager.GPS_PROVIDER);
        //st = lm.isProviderEnabled(LocationManager.GPS_PROVIDER); //El provider es el GPS


        //pregunta si el gsp está activo o no
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "No está activo el GPS", Toast.LENGTH_SHORT).show();
            //return;
        }

        lli = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Toast.makeText(MainActivity.this, "Si llegueee", Toast.LENGTH_SHORT).show();
                txt1.setText(String.valueOf(location.getLatitude()));
                txt2.setText(String.valueOf(location.getAltitude()));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(MainActivity.this, "Holaaa", Toast.LENGTH_SHORT).show();
            return;
        }
        //location manager yo obtengo un location provider, un location provider viene siendo el GPS o el Network, son proveedores de ubicación
        //con el locationmmanager sacamos la geolocalización, no todos los gps devuelven la misma información o con la misma exactitud
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, lli);
    }
}
