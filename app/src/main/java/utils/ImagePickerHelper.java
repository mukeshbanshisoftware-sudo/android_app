package utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.provider.MediaStore;

import androidx.activity.result.ActivityResultLauncher;

public class ImagePickerHelper {

    public static void showPicker(
            Activity activity,
            ActivityResultLauncher<Intent> cameraLauncher,
            ActivityResultLauncher<Intent> galleryLauncher){

        String[] items={"Camera","Gallery"};

        new AlertDialog.Builder(activity)
                .setTitle("Choose Image")
                .setItems(items,(dialog,which)->{

                    if(which==0){

                        Intent cameraIntent =
                                new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        cameraLauncher.launch(cameraIntent);

                    }else{

                        Intent galleryIntent =
                                new Intent(
                                        Intent.ACTION_PICK,
                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                        galleryLauncher.launch(galleryIntent);

                    }

                })
                .show();

    }

}
