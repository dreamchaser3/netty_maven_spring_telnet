package netty.test;

import java.net.InetAddress;
import java.util.Date;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TelnetServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.write("환영합니다. " + InetAddress.getLocalHost().getHostName() + "에 접속하셨습니다!\r\n");
        ctx.write("현재 시간은 " + new Date() + " 입니다.\r\n");
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        String res;
        boolean close = false;

        if(s.isEmpty()) {
            res = "명령을 입력해 주세요.\r\n";
        } else if("bye".equals(s.toLowerCase())) {
            res = "좋은 하루 되세요~\r\n";
            close = true;
        } else {
            res = "입력하신 명령이 '" + s + "' 입니까?\r\n";
        }

        ChannelFuture future = channelHandlerContext.write(res);

        if(close) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }
}
