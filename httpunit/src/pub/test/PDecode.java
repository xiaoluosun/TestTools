package pub.test;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class PDecode extends ProtocolDecoderAdapter {

	@Override
	public void decode(IoSession arg0, IoBuffer arg1, ProtocolDecoderOutput arg2)
			throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("in PDecode");
		arg2.write(arg1);
	}

}
