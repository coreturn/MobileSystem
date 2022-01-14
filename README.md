# Moment - 校园短视频信息即时分享APP

2021秋 FDU PJ

## 产品定位

抖音内容娱乐化，用户难以获得正向反馈。Moment APP参考抖音进行设计开发，聚焦**校园短视频**这一垂直领域，保证视频**内容质量以及相关性**，提供校园生活信息即时分享平台，有利于活跃校园氛围，推动信息流动，如可应用于如下场景：

* 校园活动宣传

* 校园生活分享

* 校园交友

## 功能设计

开题时计划完成四大功能模块，功能间紧密联系，带给用户良好的使用体验。
<img src="./design.png" width = "300" height = "200" alt="图片名称" align=center />

工程实践中，由于前端页面以及跳转逻辑的设计、后端MySQL开发、Servelt前后端交互，总体开发量较大，调整目标为优先实现**注册、信息流、评论**功能，目前已完成开发。

* 注册：支持用户的注册与登录

* 信息流：支持短视频的上下滑动 & 点击暂停/播放

* 评论：支持查看并发表评论

## 部署方法

项目分为两个子项目，分别采用前端Android和后端JavaWeb，进行数据库（MySQL）交互，前后端需要分别进行部署，涉及的技术栈有：

* Android开发
* JDBC + Servelt
* Tomcat  + MySQL

### Android部署
Android项目使用AS打开，修改buildToolsVersion（项目中是31.0.0）即可运行。

### JavaWeb部署
JavaWeb项目打包为war后，上传到服务器tomcat的webapps目录下，随后运行tomcat即可。

两项目部署完后，即可体验**注册、信息流、评论**功能。

## 项目介绍

依旧分为前端、后端两部分进行介绍。

### JavaWeb端
#### 建库建表
支持登录注册、评论功能

	//登录注册
	CREATE TABLE user
	(
	    id          INT     NOT NULL    PRIMARY KEY   AUTO_INCREMENT,
	    name        CHAR(30)    NULL,
	    password    CHAR(30)    NULL
	)
	
	// 评论，url标识表示视频
	CREATE TABLE comment
	(
	        `url`   varchar(128)    NOT NULL,
	        `comment` varchar(255)  NOT NULL,
	        INDEX (`url`)
	)
	
#### JDBC连接数据库
获取连接：

	Class.forName("com.mysql.cj.jdbc.Driver");
	String url = "jdbc:mysql://127.0.0.1:3306/userinfo";
	String username = "root";
	String password = "";  // 设置为对应密码
	connection = DriverManager.getConnection(url, username, password);

连接后，即进行SQL执行与响应。以评论功能举例，根据url，返回comment表中该url相关所有评论。返回值最终打包为响应结构体返回给前端。


### Android端
由四个Activity构成：

* LoadActivity：APP加载，初次加载跳转LoginActivity
* LoginActivity：登录，登录成功跳转MainActivity，注册账号跳转LoginActivity
* MainActivity：核心activity
* RegisterActivity：注册，已有账号跳转LoginActivity

接下来主要讲解核心MainActivity功能与实现：

#### 导航
<img src="./mine.jpeg" alt="图片名称" align=center />

在首页和个人主页间进行切换。首页可查看信息流并评论，个人主页进行登录注册。

#### 注册
<img src="./log.jpeg" alt="图片名称" align=center />

<img src="./reg.jpeg" alt="图片名称" align=center />

类似评论，请求中携带用户名和密码。

#### 评论
<img src="./comment.jpeg" alt="图片名称" align=center />

点击评论按钮查看和编写评论，点击事件发送的请求中携带视频信息和评论，更新后端MySQL。

#### 信息流
<img src="./info.jpeg" alt="图片名称" align=center />

支持视频上下滑动、点击暂停。

* 视频获取：Mock抖音API请求获取视频url信息。
	
* 视频播放

	前端基于ViewPager2实现，每个视频都是一个fragment，Activity和Fragment之间通过Eventbus通信。播放使用开源库，设置视频地址url即可：
	
	`import com.dueeeke.videoplayer.player.VideoView;`
	
## 参考
虽然Moment APP还比较朴素，但把它运行在手机中，能刷视频，实践中还学习收获了很多，这实在让人非常开心，以下是主要参考的资料：

* [TIkTok android](https://github.com/oyo775881/tiktok)

* [JavaWeb开发](https://www.helloworld.net/p/6080944426)





