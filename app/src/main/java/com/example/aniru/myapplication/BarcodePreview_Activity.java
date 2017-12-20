package com.example.aniru.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

import timber.log.Timber;

/**
 * Created by aniru on 11/26/2017.
 */

public class BarcodePreview_Activity extends AppCompatActivity {

    SurfaceView cameraView;
    CameraSource cameraSource;
    Barcode barCode;
    BarcodeDetector barcodeDetector;
    CameraSource mCameraSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcodepreview);

        cameraView = (SurfaceView)findViewById(R.id.camera_view);


        // (1) Setup the barcode detector
        barcodeDetector =
                new BarcodeDetector.Builder(this)
                        .setBarcodeFormats(Barcode.ISBN)
                        .build();

        mCameraSource = new CameraSource.Builder(getApplicationContext(), barcodeDetector)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedFps(15.0f)
                .build();

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            // (2) Detect the barcode
            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() != 0) {
                    barCode = barcodes.valueAt(0);

                    // (3) Decode the barcode and send it back to the dialog box
                    Toast.makeText(BarcodePreview_Activity.this, barCode.rawValue, Toast.LENGTH_SHORT).show();
                }
            }
        });

        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // Start camera
                try {
                    if (! barcodeDetector.isOperational()) {
                        Toast.makeText(BarcodePreview_Activity.this, "Barcode detector is not operational", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        cameraSource.start(cameraView.getHolder());
                    }
                } catch (SecurityException se) {
                    Timber.e("CAMERA SOURCE", se.getMessage());
                } catch (IOException ie) {
                    Timber.e("CAMERA SOURCE", ie.getMessage());
                }
                catch( Exception e){
                    Timber.e("CAMERA SOURCE", e.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                // Stop camera
                cameraSource.stop();
            }
        });
    }
}
