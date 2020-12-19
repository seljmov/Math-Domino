package com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.APIInstruments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

import com.example.science.R;

public class ColoredNumbers {

    private static ColoredNumbers instance = new ColoredNumbers();

    private ColoredNumbers(){

    }

    public static ColoredNumbers getInstance() {
        return instance;
    }

    private Bitmap getResource(Context context, int number){
        switch (number) {
            case 1:
                return BitmapFactory.decodeResource(context.getResources(), R.mipmap.kost1);
            case 2:
                return BitmapFactory.decodeResource(context.getResources(), R.mipmap.kost2);
            case 3:
                return BitmapFactory.decodeResource(context.getResources(), R.mipmap.kost3);
            case 4:
                return BitmapFactory.decodeResource(context.getResources(), R.mipmap.kost4);
            case 5:
                return BitmapFactory.decodeResource(context.getResources(), R.mipmap.kost5);
            case 6:
                return BitmapFactory.decodeResource(context.getResources(), R.mipmap.kost6);
        }
        return null;
    }

    public Bitmap numberWhite(Context context, int number){
        Bitmap bitmap = getResource(context, number);
        Bitmap result = Bitmap
                .createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(result);

        ColorMatrix matrix = new ColorMatrix(
                new float[]{
                        0, 0, 0, 0, 248,
                        0, 0, 0, 0, 248,
                        0, 0, 0, 0, 248,
                        0, 0, 0, 1, 0
                }
                // #F8F8F8
        );

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));

        canvas.drawBitmap(bitmap, 0, 0, paint);

        return result;
    }

    public Bitmap numberRed(Context context, int number){
        Bitmap bitmap = getResource(context, number);
        Bitmap result = Bitmap
                .createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(result);

        ColorMatrix matrix = new ColorMatrix(
                new float[]{
                        0, 0, 0, 0, 229,
                        0, 0, 0, 0, 57,
                        0, 0, 0, 0, 53,
                        0, 0, 0, 1, 0
                }
                // #e53935
        );

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));

        canvas.drawBitmap(bitmap, 0, 0, paint);

        return result;
    }

    public Bitmap numberGreen(Context context, int number){
        Bitmap bitmap = getResource(context, number);
        Bitmap result = Bitmap
                .createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(result);

        ColorMatrix matrix = new ColorMatrix(
                new float[]{
                        0, 0, 0, 0, 67,
                        0, 0, 0, 0, 160,
                        0, 0, 0, 0, 71,
                        0, 0, 0, 1, 0
                }
                // #43A047
        );

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));

        canvas.drawBitmap(bitmap, 0, 0, paint);

        return result;
    }
}
