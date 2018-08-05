package cn.lxw.business.user.ui.activity

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import cn.lxw.business.R
import cn.lxw.business.baselibrary.ext.onClick
import cn.lxw.business.baselibrary.ui.activity.BaseMvpActivity
import cn.lxw.business.user.injection.component.DaggerUserComponent
import cn.lxw.business.user.presenter.UserInfoPresenter
import cn.lxw.business.user.presenter.view.UserInfoView
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.kotlin.base.utils.DateUtils
import kotlinx.android.synthetic.main.activity_user_info.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest
import java.io.File

/**
 ***************************************************
 * 猿代码：Lxw
 * Email： China2021@126.com
 * 时间轴：2018年06月08日 17:38
 ***************************************************
 * 备注：登陆界面
 * 功能描述：
 */
class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView, View.OnClickListener, TakePhoto.TakeResultListener, EasyPermissions.PermissionCallbacks, EasyPermissions.RationaleCallbacks {


    private lateinit var mTakePhoto: TakePhoto

    private lateinit var mTempFile: File

    /** 拍照所需要的权限 */
    private val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        mTakePhoto = TakePhotoImpl(this, this)
        mTakePhoto.onCreate(savedInstanceState)
        initView()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }

    /**
     * 点击事件
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.rlUserHeadImage -> {
            }
            R.id.mArrowIv -> {
            }
            R.id.cliUserHeadImage -> {
            }
            R.id.etUserName -> {
            }
            R.id.rbMale -> {
            }
            R.id.rbFemale -> {
            }
            R.id.tvUserMobile -> {
            }
            R.id.etUserSign -> {
            }
        }
    }


    /**
     * 初始化视图
     */
    private fun initView() {
        rlUserHeadImage.onClick {
            showAlertView()
        }
        mArrowIv.onClick {}
        cliUserHeadImage.onClick {}
        etUserName.onClick {}
        rbMale.onClick {}
        rbFemale.onClick {}
        tvUserMobile.onClick {}
        etUserSign.onClick {}
    }


    /**
     * 显示采集头像的方式Dialog.
     */
    private fun showAlertView() {
        AlertView("选择图片", "", "取消", null, arrayOf("拍照", "相册"), this, AlertView.Style.ActionSheet, OnItemClickListener { o, position ->
            //是否压缩图片
            mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
            when (position) {
                0 -> {
                    getHeadImageNeedPermissions()
                }
                1 -> {
                    mTakePhoto.onPickFromGallery()
                }
            }
        }).show()
    }


    /**
     * 获取设置头像需要的权限,添加注解是因为获取到权限之后的流程方便继续执行.
     */
    @AfterPermissionGranted(RC_CAMERA_READ_EXTERNAL_WRITE_EXTERNAL)
    private fun getHeadImageNeedPermissions() {
        //检测是否有权限
        if (EasyPermissions.hasPermissions(this, *permissions)) {
            createTempFile()
            mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
        } else {
            //当用户拒绝第一次权限之后,将会弹出由PermissionRequest.Builder出来的对话框.
            EasyPermissions.requestPermissions(PermissionRequest.Builder(this, RC_CAMERA_READ_EXTERNAL_WRITE_EXTERNAL, *permissions)
                    .setNegativeButtonText("不,别这样")
                    .setPositiveButtonText("好的")
                    .setRationale("嘿,伙计,我们需要你同意我们使用你的相册")
                    .build()
            )
        }
    }


    /**
     * 权限拒绝
     */
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        //当一些权限被拒绝且永久不在提示的时候!
        if (EasyPermissions.somePermissionPermanentlyDenied(this, permissions.toMutableList())) {
//            AlertView("获取授权", "我们需要你的授权才能继续工作,请手动打开权限.", null, arrayOf("欣然接受", "残忍拒绝"), null, this, AlertView.Style.Alert, object : OnItemClickListener {
//                override fun onItemClick(o: Any?, position: Int) {
//                    if(position==1){
//
//                    }
//                }
//            }).show()
            AppSettingsDialog.Builder(this@UserInfoActivity)
                    .setTitle("请授予我们权限.")
                    .setRationale("由于你决绝了我们的权限,我们将无法继续工作,请手动打开权限!").build().show()
        }
        //有的权限被拒绝,但可以二次申请!
    }


    /**
     * 接受权限回调
     */
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }

    /**
     * 再次拒绝,我们的提示
     */
    override fun onRationaleDenied(requestCode: Int) {
        Log.d("EasyPermissions", "你拒绝了权限,请在设置中打开权限,我们才能继续")
    }

    /**
     * 同意了我们的二次请求
     */
    override fun onRationaleAccepted(requestCode: Int) {
        Log.d("EasyPermissions", "继续")
        getHeadImageNeedPermissions()
    }


    /**
     * 权限回调交由 [EasyPermissions] 处理
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //结果交由 EasyPermissions 处理,这里的this参数,是让他回调,带有@AfterPermissionGranted 方法,方便后续流程
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }


    private fun getImage() {
        createTempFile()
        mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
    }


    /**
     * 实现依赖注入
     */
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).build().inject(this)
        presenter.mView = this
    }

    override fun takeSuccess(result: TResult?) {
        Log.d("TakePhoto", result?.image?.originalPath)//原始图片地址
        Log.d("TakePhoto", result?.image?.compressPath)//压缩图片地址
    }

    override fun takeCancel() {

    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.d("TakePhoto", msg)
    }

    private fun createTempFile() {
        val tempFile = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            this.mTempFile = File(Environment.getExternalStorageDirectory(), tempFile)
            return
        }
        this.mTempFile = File(filesDir, tempFile)
    }

    companion object {
        const val RC_CAMERA_READ_EXTERNAL_WRITE_EXTERNAL = 0x01
    }
}