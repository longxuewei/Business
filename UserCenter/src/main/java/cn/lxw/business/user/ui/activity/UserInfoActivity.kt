package cn.lxw.business.user.ui.activity

import ProviderConstant
import UserPrefsUtils
import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import cn.lxw.business.R
import cn.lxw.business.baselibrary.common.BaseConstant
import cn.lxw.business.baselibrary.ext.onClick
import cn.lxw.business.baselibrary.ui.activity.BaseMvpActivity
import cn.lxw.business.user.data.protocol.UserInfo
import cn.lxw.business.user.injection.component.DaggerUserComponent
import cn.lxw.business.user.presenter.UserInfoPresenter
import cn.lxw.business.user.presenter.view.UserInfoView
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.base.utils.DateUtils
import com.kotlin.base.utils.GlideUtils
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
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
 * 备注：用户信息界面
 * 功能描述：
 */
class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView, View.OnClickListener, TakePhoto.TakeResultListener, EasyPermissions.PermissionCallbacks, EasyPermissions.RationaleCallbacks {


    /** 选取照片的框架 */
    private lateinit var mTakePhoto: TakePhoto

    /** 临时文件 */
    private lateinit var mTempFile: File

    /** 七牛云客户端 */
    private val mUploadManager: UploadManager by lazy { UploadManager() }

    /** 本地选取的文件路径 */
    private var mLocalFilePath: String? = null

    /** 上传七牛云之后,返回来的图片地址 */
    private var mRemoteFileUrlPath: String? = null

    private var mUserIcon: String? = null
    private var mUserGender: String? = null
    private var mUserName: String? = null
    private var mUserSign: String? = null

    /** 拍照所需要的权限 */
    private val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        mTakePhoto = TakePhotoImpl(this, this)
        mTakePhoto.onCreate(savedInstanceState)
        initView()
        initData()
    }

    private fun initData() {
        mUserIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
        mUserGender = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_GENDER)
        mUserName = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        mUserSign = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_SIGN)

        mRemoteFileUrlPath = mUserIcon
        mUserIcon?.let {
            if (it.isNotEmpty()) {

                GlideUtils.loadUrlImage(this, it, cliUserHeadImage)
            }
        }

        if (mUserGender == "0") {
            rbMale.isChecked = true
        } else {
            rbFemale.isChecked = true
        }

        tvUserMobile.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_MOBILE)
        etUserName.setText(mUserName)
        etUserSign.setText(mUserSign)

    }


    /**
     * 将选取照片的结果交由框架处理
     */
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
        mHeaderBar.getRightTextView().onClick {
            presenter.editUser(mRemoteFileUrlPath!!, etUserName.text.toString()
                    ?: "", if (rbMale.isChecked) "0" else "1", etUserSign.text.toString())
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
     * 权限同意
     */
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {}


    /**
     * 权限拒绝
     */
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        //当一些权限被拒绝且永久不在提示的时候!
        if (EasyPermissions.somePermissionPermanentlyDenied(this, permissions.toMutableList())) {
            AppSettingsDialog.Builder(this@UserInfoActivity)
                    .setTitle("请授予我们权限.")
                    .setRationale("由于你决绝了我们的权限,我们将无法继续工作,请手动打开权限!").build().show()
        }
        //有的权限被拒绝,但可以二次申请!
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
        result?.image?.let {
            mLocalFilePath = it.compressPath
            presenter.getUploadToken()
        }

    }

    /**
     * 取消选择
     */
    override fun takeCancel() {

    }

    /**
     * 获取图片失败
     */
    override fun takeFail(result: TResult?, msg: String?) {
        Log.d("TakePhoto", msg)
    }


    /**
     * 创建临时文件
     */
    private fun createTempFile() {
        val tempFile = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            this.mTempFile = File(Environment.getExternalStorageDirectory(), tempFile)
            return
        }
        //没有内存卡.直接在FileDir里面创建
        this.mTempFile = File(filesDir, tempFile)
    }


    /**
     * 获取Token回调
     * [result]: Token
     */
    override fun onUploadTokenResult(result: String) {
        mLocalFilePath?.let {
            mUploadManager.put(it, null, result, { _, _, response ->
                mRemoteFileUrlPath = BaseConstant.IMAGE_SERVER_ADDRESS + response?.get("hash")
                GlideUtils.loadUrlImage(this@UserInfoActivity, mRemoteFileUrlPath!!, cliUserHeadImage)
            }, null)

        }
    }

    companion object {
        /** 请求权限的回调码 */
        const val RC_CAMERA_READ_EXTERNAL_WRITE_EXTERNAL = 0x01
    }


    /**
     * 更新用户信息回调
     */
    override fun onEditUserResult(result: UserInfo) {
        UserPrefsUtils.putUserInfo(result)
        toast("更新用户信息成功")
    }

}

