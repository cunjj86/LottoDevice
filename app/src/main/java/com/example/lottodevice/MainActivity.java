package com.example.lottodevice;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lottodevice.databinding.ActivityMainBinding;

import java.util.Arrays;

public class MainActivity extends BaseActivity {

    ActivityMainBinding act;

    int[] winningNumberArray = new int[6];
    int[] pickUpNumberArray = new int[6];

    int usesPay = 0;
    int aggPickPay = 0;
    final int[] rankPay = new int[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        rankPay[0] = 1000000000;
        rankPay[1] = 50000000;
        rankPay[2] = 1000000;
        rankPay[3] = 50000;
        rankPay[4] = 5000;

        act.purchaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int rank = 6;
                usesPay = usesPay + 1000;
                act.usesPayTxt.setText(String.format("사용금액 : %,d 원", usesPay));

                makePickUpNumber();

                act.pickUpNumber1.setText(Integer.toString(pickUpNumberArray[0]));
                act.pickUpNumber2.setText(Integer.toString(pickUpNumberArray[1]));
                act.pickUpNumber3.setText(Integer.toString(pickUpNumberArray[2]));
                act.pickUpNumber4.setText(Integer.toString(pickUpNumberArray[3]));
                act.pickUpNumber5.setText(Integer.toString(pickUpNumberArray[4]));
                act.pickUpNumber6.setText(Integer.toString(pickUpNumberArray[5]));

                for (int i=0; i < winningNumberArray.length; i++) {
                    if (winningNumberArray[i] == pickUpNumberArray[i]) {
                        rank = rank - 1;
                    }
                }

                Log.d("랭크값 : ", Integer.toString(rank));

                if (rank == 0) {
                    Toast.makeText(mContext, "1등에 당첨되었습니다!! 축하드립니다.", Toast.LENGTH_SHORT).show();
                    aggPickPay = aggPickPay + rankPay[0];
                }
                else if (rank == 1) {
                    Toast.makeText(mContext, "2등에 당첨되었습니다!! 축하드립니다.", Toast.LENGTH_SHORT).show();
                    aggPickPay = aggPickPay + rankPay[1];
                }
                else if (rank == 2) {
                    Toast.makeText(mContext, "3등에 당첨되었습니다!! 축하드립니다.", Toast.LENGTH_SHORT).show();
                    aggPickPay = aggPickPay + rankPay[2];
                }
                else if (rank == 3) {
                    Toast.makeText(mContext, "4등에 당첨되었습니다!! 축하드립니다.", Toast.LENGTH_SHORT).show();
                    aggPickPay = aggPickPay + rankPay[3];
                }
                else if (rank == 4) {
                    Toast.makeText(mContext, "5등에 당첨되었습니다!! 축하드립니다.", Toast.LENGTH_SHORT).show();
                    aggPickPay = aggPickPay + rankPay[4];
                }
                else {
                    Toast.makeText(mContext, "아쉽네요. 다음 기회에 도전하세요!", Toast.LENGTH_SHORT).show();
                }

                act.aggPickPayTxt.setText(String.format("누적 당첨금액 : %,d 원", aggPickPay));
            }
        });

    }

    void makePickUpNumber() {
        while (true) {

            int[] tempNumber = new int[6];

            for (int i=0; i < tempNumber.length; i++) {
                int randomNumber = (int) (Math.random() * 49 + 1);

                tempNumber[i] = randomNumber;
            }

            boolean isDupOk = true;
            int[] saveNumber = new int[6];

            for (int j=0; j < tempNumber.length; j++) {

                for (int k=0; k < tempNumber.length - 1; k++) {

                    if (tempNumber[k] == tempNumber[k+1]) {
                        isDupOk = false;
//                        Toast.makeText(mContext, "랜덤 숫자 중복 오류입니다.", Toast.LENGTH_LONG).show();
                        break;
                    }

                    saveNumber[k] = tempNumber[k];
                    tempNumber[k] = tempNumber[k+1];
                    tempNumber[k+1] = saveNumber[k];

                }

            }

            if (isDupOk) {

                for (int n=0; n < pickUpNumberArray.length; n++) {

                    pickUpNumberArray[n] = tempNumber[n];

                }

                Arrays.sort(pickUpNumberArray);

                break;

            }

        }
    }

    @Override
    public void setValues() {

        makeWinningNumber();

        act.winningNumber1.setText(Integer.toString(winningNumberArray[0]));
        act.winningNumber2.setText(Integer.toString(winningNumberArray[1]));
        act.winningNumber3.setText(Integer.toString(winningNumberArray[2]));
        act.winningNumber4.setText(Integer.toString(winningNumberArray[3]));
        act.winningNumber5.setText(Integer.toString(winningNumberArray[4]));
        act.winningNumber6.setText(Integer.toString(winningNumberArray[5]));

        act.usesPayTxt.setText(String.format("사용금액 : %,d 원", usesPay));
        act.aggPickPayTxt.setText(String.format("누적 당첨금액 : %,d 원", aggPickPay));

    }

    void makeWinningNumber() {

        while (true) {

            int[] tempNumber = new int[6];

            for (int i=0; i < tempNumber.length; i++) {
                int randomNumber = (int) (Math.random() * 49 + 1);

                tempNumber[i] = randomNumber;
            }

            boolean isDupOk = true;
            int[] saveNumber = new int[6];

            for (int j=0; j < tempNumber.length; j++) {

                for (int k=0; k < tempNumber.length - 1; k++) {

                    if (tempNumber[k] == tempNumber[k+1]) {
                        isDupOk = false;
//                        Toast.makeText(mContext, "랜덤 숫자 중복 오류입니다.", Toast.LENGTH_LONG).show();
                        break;
                    }

                    saveNumber[k] = tempNumber[k];
                    tempNumber[k] = tempNumber[k+1];
                    tempNumber[k+1] = saveNumber[k];

                }

            }

            if (isDupOk) {

                for (int n=0; n < winningNumberArray.length; n++) {

                    winningNumberArray[n] = tempNumber[n];

                }

                Arrays.sort(winningNumberArray);

                break;

            }

        }

    }

    @Override
    public void bindViews() {

        act = DataBindingUtil.setContentView(this, R.layout.activity_main);

    }
}
