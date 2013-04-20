package rjfsdo.sharoncn.android.BDQN.CoolVideoPlayer.Utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class BufferUtils {
	private static FloatBuffer buffer;
	public static FloatBuffer floatBuffer(float[] data){
		//每个Float类型数据占用4个字节的空间
		ByteBuffer vbb = ByteBuffer.allocateDirect(data.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		buffer = vbb.asFloatBuffer();
		buffer.put(data);
		buffer.position(0);
		return buffer;
	}
}
