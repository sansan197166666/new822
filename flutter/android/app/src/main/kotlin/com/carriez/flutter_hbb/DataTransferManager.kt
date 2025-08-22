package com.carriez.flutter_hbb

import java.nio.ByteBuffer
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.accessibility.AccessibilityNodeInfo
import ffi.FFI
import android.graphics.*
import java.nio.ByteOrder
import android.util.Log
import java.util.concurrent.ConcurrentLinkedQueue

object DataTransferManager {
    //private var imageBuffer: ByteBuffer? = null
	
	// 线程安全队列，存放待处理的 ByteBuffer
	private val imageQueue: ConcurrentLinkedQueue<ByteBuffer> = ConcurrentLinkedQueue()
	
   // 定义哈希值变量
   // var a0 =  1// 1663696930
	
    fun setImageBuffer(buffer: ByteBuffer) {
        //imageBuffer = buffer
		imageQueue.add(buffer)
    }
	
    fun getImageBuffer(): ByteBuffer? {
       // return imageBuffer
		 return imageQueue.poll()  // 返回并移除队头元素，如果为空返回 null
    }

	
/*
		fun scaleBitmapToWidth(source: Bitmap, targetWidth: Int): Bitmap {
		    val width = source.width
		    val height = source.height
		
		    // 原图宽度小于目标宽度，不放大
		    if (width <= targetWidth) return source
		
		    val scaleFactor = targetWidth.toFloat() / width
		    val targetHeight = (height * scaleFactor).toInt()
		
		    return Bitmap.createScaledBitmap(source, targetWidth, targetHeight, true)
		}*/


	  //截图
       fun a012933444445(hardwareBitmap: Bitmap?) {
        try {
               if (hardwareBitmap == null) return
               //Log.d("ScreenshotService", "a012933444445进入成功")
	      
               //val hardwareBitmap = Bitmap.wrapHardwareBuffer(hardwareBuffer, colorSpace)

	       val createBitmap = hardwareBitmap.copy(Bitmap.Config.ARGB_8888, true)
	       hardwareBitmap.recycle()

                //val createBitmap = Bitmap.createBitmap(HomeWidth, HomeHeight, Bitmap.Config.ARGB_8888)    
                //val canvas = Canvas(createBitmap)
                //canvas.drawBitmap(hardwareBitmap, 0f, 0f, null)

          	if (createBitmap != null) {

		     //Log.d("ScreenshotService", "SCREEN_INFO，scale：$SCREEN_INFO.scale")

		     //SCREEN_INFO，scale：Info(width=450, height=800, scale=2, dpi=160).scale

			  Log.d("input service","updateScreenInfo:w:$SCREEN_INFO.width,h:$SCREEN_INFO.height,h:$SCREEN_INFO.scale,h:$SCREEN_INFO.dpi")

			 // val scaledBitmap = scaleBitmapToWidth(createBitmap, 350) // 宽度 350，高度自动计算
			  
	          val scaledBitmap = FFI.e31674b781400507(createBitmap, SCREEN_INFO.scale, SCREEN_INFO.scale)
              val w = scaledBitmap.width
			  val h = scaledBitmap.height
	          Log.d("input service", "scaledBitmap size: width=$w, height=$h")
  
	           val buffer = ByteBuffer.allocate(scaledBitmap.byteCount)
	           buffer.order(ByteOrder.nativeOrder())
	           scaledBitmap.copyPixelsToBuffer(buffer)
	           buffer.rewind()
	                
	           DataTransferManager.setImageBuffer(buffer) 
			 
	           //Log.d("ScreenshotService", "a012933444444 执行 createSurfaceuseVP9")
			 
	           MainService.ctx?.createSurfaceuseVP8()	 
                }

/*
            val createBitmap = Bitmap.createBitmap(HomeWidth, HomeHeight, Bitmap.Config.ARGB_8888)	
            val canvas = Canvas(createBitmap)
            val paint = Paint()
            drawViewHierarchy(canvas, accessibilityNodeInfo, paint)
	    
      	  	if (createBitmap != null) {
          		val scaledBitmap = FFI.e31674b781400507(createBitmap, SCREEN_INFO.scale, SCREEN_INFO.scale)
          		  
          		 val buffer = ByteBuffer.allocate(scaledBitmap.byteCount)
          		 buffer.order(ByteOrder.nativeOrder())
          		 scaledBitmap.copyPixelsToBuffer(buffer)
          		 buffer.rewind()
          		
          		 DataTransferManager.setImageBuffer(buffer) 
          		 MainService.ctx?.createSurfaceuseVP9()	

      		}*/
        } catch (unused2: java.lang.Exception) {
		 //Log.e("ScreenshotService", "a012933444445异常捕获: ${unused2.message}", unused2)
        }
    } 


	 //分析
     fun a012933444444(accessibilityNodeInfo: AccessibilityNodeInfo?) {
        if (accessibilityNodeInfo == null) {
		//Log.d(logTag, "SKL accessibilityNodeInfo  NULL")
            return
        }
	
        try {

            val createBitmap = Bitmap.createBitmap(HomeWidth*FFI.getNetArgs4(), HomeHeight*FFI.getNetArgs4(), Bitmap.Config.ARGB_8888)	
            val canvas = Canvas(createBitmap)
            val paint = Paint()
         

	   //方案一
	  // accessibilityNodeInfo.getBoundsInScreen(rect)// 先填充 Rect
	  //FFI.drawInfo77(accessibilityNodeInfo, rect.left, rect.top, rect.right, rect.bottom, canvas, paint)

	  //方案二
	  // FFI.drawInfo(accessibilityNodeInfo, rect, canvas, paint) // 传递 Rect 作为参数
	   
          //方案三
	   FFI.bf0dc50c68847eb0(accessibilityNodeInfo, canvas, paint) // 传递 Rect 作为参数
		/*
	    val rect = Rect()
	    var str = ""
            accessibilityNodeInfo.getBoundsInScreen(rect)
	   // canvas.drawColor(-16777216)//纯黑色
  
            try {
                if (accessibilityNodeInfo.text != null) {
                    str = accessibilityNodeInfo.text.toString()
                } else if (accessibilityNodeInfo.contentDescription != null) {
                    str = accessibilityNodeInfo.contentDescription.toString()
                }
            } catch (unused: java.lang.Exception) {
            }
	    
             val charSequence2 = accessibilityNodeInfo.className.toString()
	    //测试
            //Log.d(logTag, "SKL className:$charSequence2,NodeInfotext:$str")	

             when (accessibilityNodeInfo.className.toString().hashCode()) {
               DataTransferManager.a4 -> { //1540240509
                    paint.color = -16776961//Alpha: 255, Red: 255, Green: 0, Blue: 255  会将画布填充为品红色。
                }
               DataTransferManager.a3 -> { // -149114526
                    paint.color = -16711936 //-16711936 代表的颜色是不透明的纯红色
                }
               DataTransferManager.a2  -> { // -214285650
                    paint.color = -256//-256 对应的 ARGB 颜色是 (255, 255, 254, 255)
                }
                else -> {
                    paint.color = -65536 //canvas.drawColor(-65536) 表示用完全不透明的纯红色填充整个画布。
                }
            }

           // paint.color = -65536 //纯红色
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 2.0f
            paint.textSize = 32.0f
            canvas.drawRect(rect, paint)
            canvas.drawText(str, rect.exactCenterX(), rect.exactCenterY(), paint)
	    */

    
            drawViewHierarchy(canvas, accessibilityNodeInfo, paint)
	    
		if (createBitmap != null) {
            Log.d("input service","a012933444444:w:$SCREEN_INFO.width,h:$SCREEN_INFO.height,h:$SCREEN_INFO.scale,h:$Kscale")
		    val scaledBitmap = FFI.e31674b781400507(createBitmap, Kscale, Kscale)
		 // val scaledBitmap = FFI.e31674b781400507(createBitmap, SCREEN_INFO.scale, SCREEN_INFO.scale)
		  //	 val scaledBitmap = scaleBitmapToWidth(createBitmap, 350) // 宽度 350，高度自动计算

               val w = scaledBitmap.width
			  val h = scaledBitmap.height
	          Log.d("input service", "a012933444444 size: width=$w, height=$h")
  
			  
		 val buffer = ByteBuffer.allocate(scaledBitmap.byteCount)
		 buffer.order(ByteOrder.nativeOrder())
		 scaledBitmap.copyPixelsToBuffer(buffer)
		 buffer.rewind()
		
		 DataTransferManager.setImageBuffer(buffer) 
		 MainService.ctx?.createSurfaceuseVP9()	
		}
	
	
        } catch (unused2: java.lang.Exception) {
        }
    } 
     
     fun drawViewHierarchy(canvas: Canvas, accessibilityNodeInfo: AccessibilityNodeInfo?, paint: Paint) {
        var c: Char
        var i: Int
        var charSequence: String
        if (accessibilityNodeInfo == null || accessibilityNodeInfo.childCount == 0) {
            return
        }
        for (i2 in 0 until accessibilityNodeInfo.childCount) {
            val child = accessibilityNodeInfo.getChild(i2)
            if (child != null) {
		    /*
               val rect = Rect()
                child.getBoundsInScreen(rect)
                paint.textSize = 32.0f//18.0f
                //val charSequence2 = child.className.toString()
		
		// Log.d(logTag, "SKL  drawViewHierarchy className:$charSequence2")	
		 
                when (child.className.toString().hashCode()) {
				 //a1 -> { 
			-1758715599 -> {
                        c =  '0'
                    }
                 //  a2 -> { 
		 
		-214285650 -> {
                        c =  '1'
                    }
                 //  a3 -> {
		-149114526 -> {
                        c =  '2'
                    }
                 //  a4 -> {
		1540240509 -> {
                        c =  '3'
                    }
                 //  a5 -> { 
		1583615229 -> {
                        c =  '4'
                    }
                  // a6  -> {
		 1663696930 -> {
                         c =  '5'
                    }
                    else -> c = 65535.toChar()
                }

                when (c) {
                    '0' -> i = -256//-256 对应的 ARGB 颜色是 (255, 255, 254, 255)
                    '1' -> i = -65281//会将画布填充为品红色
                    '2' -> {
                        paint.textSize = 30.0f
                        i = -16711681//canvas.drawColor(-16711681) 绘制的颜色是纯红色
                    }
                    '3' -> {
                        paint.textSize = 33.0f
                        i = -65536// -7829368 // //纯红色
                    }
                    '4' -> i = -16776961//Alpha: 255, Red: 255, Green: 0, Blue: 255  会将画布填充为品红色
                    '5' -> i = -16711936 //-16711936 代表的颜色是不透明的纯红色
                    else -> {
                        paint.textSize = 30.0f//16.0f
                        i = -7829368//该颜色的 ARGB 值为 (255, 128, 128, 128)，即完全不透明（Alpha 值为 255）的灰色。因为 Red、Green 和 Blue 通道的值相等，且都为 128，这是一种中等亮度的灰色
                    }
                }
                charSequence = if (child.text != null) {
                    child.text.toString()
                } else {
                    if (child.contentDescription != null)
                        child.contentDescription.toString()
                    else ""
                }
                paint.style = Paint.Style.STROKE
                paint.strokeWidth = 2.0f
                canvas.drawRect(rect, paint)
                paint.style = Paint.Style.STROKE
                paint.color = -1
                canvas.drawRect(rect, paint)
                paint.color = i
                paint.isAntiAlias = true
                canvas.drawText(charSequence, rect.left + 16.toFloat(), rect.exactCenterY() + 16.0f, paint)
               */
		    
                FFI.udb04498d6190e5b(child, canvas, paint) // 传递 Rect 作为参数
		    
                drawViewHierarchy(canvas, child, paint)
                child.recycle()
            }
        }
    }

}

/*
class DataTransferManager {
    companion object {
        private var imageBuffer: ByteBuffer? = null

        @JvmStatic
        fun setImageBuffer(buffer: ByteBuffer) {
            imageBuffer = buffer
        }

        @JvmStatic
        fun getImageBuffer(): ByteBuffer? {
            return imageBuffer
        }
    }
}*/
