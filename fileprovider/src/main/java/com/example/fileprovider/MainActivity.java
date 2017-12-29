package com.example.fileprovider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.RationaleListener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_TAKE_PHOTO = 0x110;
    @BindView(R.id.paizhao)
    Button paizhao;
    @BindView(R.id.iv)
    ImageView mIvPhoto;
    private String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    public void takePhotoNoCompress(View view) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                new AlertDialog.Builder(this).setMessage("请给与拍照权限！否则将影响软件使用！！").
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 10000);

                            }
                        }).show();

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 10000);

            }

        } else {
            //TODO
            takephoto();
            //Toast.makeText(this, "已有拍照权限！", Toast.LENGTH_SHORT).show();
        }

    }

    // 成功回调的方法，用注解即可，这里的300就是请求时的requestCode。
    @PermissionYes(100)
    private void getPermissionYes(List<String> grantedPermissions) {
        // TODO 申请权限成功。
         mIvPhoto.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath));
        //Glide.with(this).load(BitmapFactory.decodeFile(mCurrentPhotoPath)).asBitmap().into(mIvPhoto);

    }

    @PermissionNo(100)
    private void getPermissionNo(List<String> deniedPermissions) {
        // TODO 申请权限失败。
        // 是否有不再提示并拒绝的权限。
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {


            // 第二种：用自定义的提示语。
            AndPermission.defaultSettingDialog(this, 400)
                    .setTitle("权限申请失败")
                    .setMessage("您拒绝了我们必要的一些权限，已经没法愉快的玩耍了，请在设置中授权！")
                    .setPositiveButton("好，去设置")
                    .show();


        }
    }

    private RationaleListener rationaleListener = (requestCode, rationale) -> {
        new AlertDialog.Builder(this)
                .setTitle("友好提醒")
                .setMessage("你已拒绝过定位权限，沒有定位定位权限无法为你推荐附近的妹子，你看着办！")
                .setPositiveButton("好，给你", (dialog, which) -> {
                    rationale.resume();
                })
                .setNegativeButton("我拒绝", (dialog, which) -> {
                    rationale.cancel();
                }).show();
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_TAKE_PHOTO) {
//读取文件权限，运行时权限
            AndPermission.with(this)
                    .requestCode(100)
                    .permission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    .rationale(rationaleListener)
                    .callback(this)
                    .start();


        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {

        switch (requestCode) {
            case 10000:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //TODO
                    //ToastUtils.showShort("已有拍照权限！");
                    takephoto();
                } else {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
// 到设置界面授予权限
                        new AlertDialog.Builder(this).setMessage("请给与拍照权限！否则将影响软件使用！！").
                                setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                                        intent.setData(uri);
                                        startActivity(intent);
                                    }
                                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).show();


                        Toast.makeText(this, "拍照权限被禁止！请打开权限！", Toast.LENGTH_SHORT).show();

                    }


                }

                break;

            default:
                break;
        }
    }

    public void takephoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            String filename = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.CHINA)
                    .format(new Date()) + ".png";
            File file = new File(Environment.getExternalStorageDirectory(), filename);
            mCurrentPhotoPath = file.getAbsolutePath();

            Uri fileUri = null;

            if (Build.VERSION.SDK_INT >= 24) {
                //处理7.0FileProvider
                fileUri = FileProvider.getUriForFile(this, "com.example.fileprovider.zk", file);
            } else {
                fileUri = Uri.fromFile(file);
            }

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(takePictureIntent, REQUEST_CODE_TAKE_PHOTO);
        }
    }
}