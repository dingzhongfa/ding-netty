package com.ding.netty.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;


/**
 * @author dingzhongfa
 * @email : 71964899@qq.com
 * @date 2019-06-07 下午7:14
 */
public class NettyHttpServerHandler extends ChannelHandlerAdapter {

    private int count;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("the msg is : " + msg);
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(("receive request count : " + ++count + "   " + System.currentTimeMillis()).getBytes("utf-8")));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, String.valueOf(response.content().readableBytes()));
        response.headers().set(HttpHeaderNames.CONNECTION);
        ctx.writeAndFlush(response);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }
}
