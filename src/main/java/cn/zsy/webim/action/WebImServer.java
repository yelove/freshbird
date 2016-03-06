package cn.zsy.webim.action;

import cn.zsy.webim.handler.ChildChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class WebImServer implements Runnable {
	
	private int port;
	
	public WebImServer(int port) {
		super();
		this.port = port;
	}


	@Override
	public void run() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();

		try {

			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workGroup);
			b.channel(NioServerSocketChannel.class);
			b.childHandler(new ChildChannelHandler());

			System.out.println("服务端开启等待客户端连接 ... ...");

			Channel ch = b.bind(port).sync().channel();

			ch.closeFuture().sync();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}

}
