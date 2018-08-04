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

    private fun getHeadImageNeedPermissions() {
        val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        //检测是否有权限
        if (EasyPermissions.hasPermissions(this, *permissions)) {
            createTempFile()
            mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
        } else {
            EasyPermissions.requestPermissions(PermissionRequest.Builder(this, RC_CAMERA_READ_EXTERNAL_WRITE_EXTERNAL, *permissions)
                    .setNegativeButtonText("不,别这样")
                    .setPositiveButtonText("好的")
                    .setRationale("嘿,伙计,我们需要你同意我们使用你的相册")
                    .build()
            )
        }
    }


    /**
     * 获得权限
     */
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (requestCode == RC_CAMERA_READ_EXTERNAL_WRITE_EXTERNAL) {
            getImage()
        }
    }


    /**
     * 拒绝权限
     */
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        if (requestCode == RC_CAMERA_READ_EXTERNAL_WRITE_EXTERNAL) {

        }
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
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults)
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