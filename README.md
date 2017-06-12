### 场景
在开发中我们经常需要对图片以人脸为中心进行剪切并显示，这时就需要下面这个工具了。

### 实现效果
![实现效果](http://img.blog.csdn.net/20170612144622869?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMzUwMTYzNw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

#### [Demo及工程地址：https://github.com/CNCFOX/ImageViewClip](https://github.com/CNCFOX/ImageViewClip)

#### 项目参考及引用
使用库： http://code.taobao.org/p/tclip/    
参考项目：https://github.com/beartung/tclip-android   
本项目参考以上上面及识别库进行修改封装打包，意在更方便灵活使用。
### 项目使用
下载工具 jar(IVClip_V1.0.jar) : https://github.com/CNCFOX/ImageViewClip/raw/master/Libs/IVClip_V1.0.jar

下载so文件：https://github.com/CNCFOX/ImageViewClip/raw/master/Libs/so_File.zip

将下载的`jar` 和 `.so` 文件加入到项目中。
### API 说明
在项目中使用如下API即可：
#### CImageView 
这是一个继承ImageView的图片控件，可以直接在xml 中进行使用：

```
<com.cfox.ivcliplib.CImageView
    android:src="@mipmap/img"
    android:layout_width="80dp"
    android:layout_height="80dp" />
```
#### CImageUtils 

**说明**：这里的宽和高不是显示的宽和高，指的是剪切时的宽和高。实际显示宽和高由自己设定，如果将ImageView 控件的宽和高设置为`wrap_content`此时的宽和高即为剪切的宽和高。
- `crop(ImageView imageView , int width, int height)`       
    将指定的ImageView 中的图片剪切指定大小
    > imageView : 被处理的ImageView 控件   
    > width : 宽    
    > height : 高  
    > 无返回值

    使用示例：
    ```
    ImageView mImg = (ImageView) findViewById(R.id.img);
    CImageUtils.instance(this).crop(mImg,400,400);
    ```
- `cropToBitmap(ImageView imageView, int width, int height)`   
    将指定的ImageView 中的图片剪切指定大小，返回剪切后图片以Bitmap类型。
    > imageView : 被处理的ImageView 控件   
    > width : 宽    
    > height : 高  
    > 返回值 : Bitmap

    使用示例：  
    ```
    ImageView mImgA_A = (ImageView) findViewById(R.id.img_a_a);
    ImageView mBaseView = (ImageView) findViewById(R.id.img_base1);
    
    Bitmap clipBitmap = CImageUtils.instance(this).cropToBitmap(mBaseView,400,400);
    mImgA_A.setImageBitmap(clipBitmap);
    ```
- `cropToBitmap(Bitmap imageBitmap, int width, int height)`  
    将指定的Bitmap图片，剪切指定大小，返回剪切后图片以Bitmap类型。
    > imageBitmap : Bitmap图片  
    > width : 宽    
    > height : 高  
    > 返回值 : Bitmap

    使用示例：  
    ```
    ImageView mImgA_A = (ImageView) findViewById(R.id.img_a_a);
    ImageView mBaseView = (ImageView) findViewById(R.id.img_base1);
    
    Bitmap baseBitmap = ((BitmapDrawable)mBaseView.getDrawable()).getBitmap();
    Bitmap clipBitmap = CImageUtils.instance(this).cropToBitmap(baseBitmap,320,320);
    mImgA_A.setImageBitmap(clipBitmap);
    ```

#### Demo 工程编译运行
- 在git中执行下面命令clone工程到本地：  
`git clone git@github.com:CNCFOX/ImageViewClip.git`

- 用Android studio 打开工程
- 打开 Gradle 找到 `:ivcliplib` ,展开`other`文件夹，找到`ndkClean` 和 `ndkBuild`分别执行，然后运行项目。
如下图：
![:ivcliplib](http://img.blog.csdn.net/20170612143101425?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMzUwMTYzNw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![ndkClean 和 ndkBuild](http://img.blog.csdn.net/20170612143309957?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMzUwMTYzNw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

