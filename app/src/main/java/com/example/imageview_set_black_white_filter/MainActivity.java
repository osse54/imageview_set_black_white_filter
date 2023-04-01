package com.example.imageview_set_black_white_filter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
            프로젝트 설명
            이미지 뷰와 그 백그라운드 색상에 흑백 필터를 입힌다.
            클릭 이벤트로 흑백 필터를 입히거나 필터를 지운다.

            4x5 matrix for transforming the color and alpha components of a Bitmap. The matrix can be passed as single array, and is treated as follows:
            비트맵의 색과 알파 구성요소를 변환하는 4x5 행렬입니다.
            매트릭스는 단일 배열로 전달될 수 있으며 다음과 같이 처리됩니다.
               [ a, b, c, d, e,
                 f, g, h, i, j,
                 k, l, m, n, o,
                 p, q, r, s, t ]

            When applied to a color [R, G, B, A], the resulting color is computed as:
            색상 [R, G, B, A]에 적용하면 결과 색상은 다음과 같이 계산됩니다.
                R’ = a*R + b*G + c*B + d*A + e;
                G’ = f*R + g*G + h*B + i*A + j;
                B’ = k*R + l*G + m*B + n*A + o;
                A’ = p*R + q*G + r*B + s*A + t;

            That resulting color [R’, G’, B’, A’] then has each channel clamped to the 0 to 255 range.
            결과 색상 [R’, G’, B’, A’]은 각 채널이 0에서 255 범위로 고정됩니다.

            Set this colormatrix to identity:
            이 색상 행렬을 식별자로 설정합니다.
                [ 1 0 0 0 0   - red vector
                  0 1 0 0 0   - green vector
                  0 0 1 0 0   - blue vector
                  0 0 0 1 0 ] - alpha vector

         */

        // 색 행렬 객체 생성
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0); // 값이 0이면 색상을 그레이 스케일에 맵핑합니다. / saturation : 채도
        // ColorMatrix 즉 색상 행렬을 이용하여 색상을 변환하는 컬러 필터입니다.
        // 픽셀의 채도를 변경하거나 YUV에서 RGB로 변환하는 등에 사용할 수 있습니다.
        // 색 행렬 필터 객체 생성
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);

        // 이미지 뷰 객체 변수에 저장
        ImageView iv = findViewById(R.id.iv);

        // 이미지 뷰와 백그라운드에 그레이 스케일 색상 필터를 적용
        iv.setColorFilter(filter);
        iv.getBackground().setColorFilter(filter);

        // 현재 색상 필터가 적용되어 있는지 확인하는 boolean 변수
        AtomicBoolean isBlack = new AtomicBoolean(true);

        // 이미지 뷰 클릭 이벤트
        iv.setOnClickListener(v -> {
            Log.i(TAG, "onCreate: iv clicked / isBlack: " + isBlack.get());
            if (isBlack.get()) { // 그레이 스케일 색상 필터가 적용되어 있으면
                Log.i(TAG, "onCreate: color filter removed");
                isBlack.set(false); // 그레이 스케일 색상 필터가 적용되어 있지 않음을 저장
                // 그레이 스케일 색상 필터를 제거
                iv.setColorFilter(null);
                iv.getBackground().setColorFilter(null);
            } else { // 그레이 스케일 색상 필터가 적용되어 있지 않으면
                Log.i(TAG, "onCreate: color filter applied");
                isBlack.set(true); // 그레이 스케일 색상 필터가 적용되어 있음을 저장
                // 그레이 스케일 색상 필터를 적용
                iv.setColorFilter(filter);
                iv.getBackground().setColorFilter(filter);
            }
        });
    }
}