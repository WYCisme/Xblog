................
<!-- title -->
[TOC]

## 更换源

1. cd /etc/apt
2. sudo cp sources.list sources.list.bak
3. 删掉sources.list内容, 插入以下内容
4. vi sources.list

```java
deb-src http://archive.ubuntu.com/ubuntu xenial main restricted #Added by software-properties
deb http://mirrors.aliyun.com/ubuntu/ xenial main restricted
deb-src http://mirrors.aliyun.com/ubuntu/ xenial main restricted multiverse universe #Added by software-properties
deb http://mirrors.aliyun.com/ubuntu/ xenial-updates main restricted
deb-src http://mirrors.aliyun.com/ubuntu/ xenial-updates main restricted multiverse universe #Added by software-properties
deb http://mirrors.aliyun.com/ubuntu/ xenial universe
deb http://mirrors.aliyun.com/ubuntu/ xenial-updates universe
deb http://mirrors.aliyun.com/ubuntu/ xenial multiverse
deb http://mirrors.aliyun.com/ubuntu/ xenial-updates multiverse
deb http://mirrors.aliyun.com/ubuntu/ xenial-backports main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ xenial-backports main restricted universe multiverse #Added by software-properties
deb http://archive.canonical.com/ubuntu xenial partner
deb-src http://archive.canonical.com/ubuntu xenial partner
deb http://mirrors.aliyun.com/ubuntu/ xenial-security main restricted
deb-src http://mirrors.aliyun.com/ubuntu/ xenial-security main restricted multiverse universe #Added by software-properties
deb http://mirrors.aliyun.com/ubuntu/ xenial-security universe
deb http://mirrors.aliyun.com/ubuntu/ xenial-security multiverse
```

4. sudo apt-get update

## 安装JDK

```java
apt-get install openjdk-8-jre-headless
```

## 安装uzip

```java
apt-get install unzip
```

uzip命令为

> unzip    xxxxx.zip


## 修改ssh文件 以root登录

vi /etc/ssh/sshd_config

```java
service sshd restart
```

## docker安装

```java
 sudo apt-get install docker
```

## 安装RZ

```java
apt-get install lrzsz
```

## 查看内核版本

 1.cat /etc/issue （简单）

2.cat /etc/lsb-release（具体）

3.uname -a（内核）