package pub.test;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class PEncoder extends ProtocolEncoderAdapter {

	@Override
	public void encode(IoSession arg0, Object arg1, ProtocolEncoderOutput arg2)
			throws Exception {
		// TODO Auto-generated method stub
		
		
		IoBuffer buf = IoBuffer.allocate(512).setAutoExpand(true);
//		System.out.println(arg1);
		buf.put(arg1.toString().getBytes());
		buf.flip();
		arg2.write(buf);
		arg2.flush();		
	}

}
