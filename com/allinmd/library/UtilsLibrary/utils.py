#!/usr/bin/env python
#-*- coding:utf8 -*-
'''
Created on 2015年5月5日

@author: sun
'''

__version__ = '0.1'

import sys
reload(sys)
sys.setdefaultencoding('utf8')

from robot.api import logger
import urllib2, json, re, os, random

class UtilsLibrary():           
    def get_email(self, url, email):
        u'''接收部分url和email，拼装成一个完整url，得到重置密码链接。
            H5测试环境的部分url:http://192.168.1.33:8080/allin_manager_platform//logSmsAction!list\n
            例：
           | Get Email        | url       | test@xxx.com         |          
        '''
        html = url \
            + "?order=asc&page=1" \
            + "&queryJson={'email'%3A'" \
            + email \
            + "'}" + "&rows=1&sort=id"
    
        req = urllib2.Request(html)
        content = urllib2.urlopen(req).read()
        data = json.loads(content)
        reg = re.compile("(http://)(www.|m.)(.*).cn/[a-n]{4,5}/customer/reset/password/input/\\?itemId=[0-9]{4}&resetSite=[0-9]&validCode=[0-9a-z]{32}")
        str = reg.findall(data['rows'][0]['sendContent'])
        url = (str[0][0]+str[0][1]+str[0][2]).split('"')[0]
        return url
    
    def get_phone(self, url, phone):
        u'''接收部分url和手机号，拼装成一个完整url，得到重置密码的验证码。
            H5测试环境的部分url:http://192.168.1.33:8080/allin_manager_platform//logEmailAction!list\n
            例：
           | Get Phone          | url          | 13800138000          |          
        '''
        html = url \
            + "?order=asc&page=1" \
            + "&queryJson={'cellPhone'%3A'" \
            + phone \
            + "'}" + "&rows=1&sort=id"
            
        req = urllib2.Request(html)
        content = urllib2.urlopen(req).read()
        data = json.loads(content)
        reg = re.compile('[0-9]{6}')
        code = reg.findall(data['rows'][0]['smsContent'])
        return code[0]

    def get_lastname(self):
        u'''从文档中随机得到一个lastname       
        '''    
        lastfile = open(os.getcwd()+"\\data\\lastname.txt")
        for l in lastfile.readlines():
            lastname = l
        lastname = lastname.split(',')
        return unicode(lastname[random.randint(0,len(lastname))], errors='ignore')
    
    def get_firstname(self):
        u'''从文档中随机得到一个firstname      
        '''    
        firstfile = open(os.getcwd()+"\\data\\firstname.txt")
        for l in firstfile.readlines():
            firstname = l    
        firstname =  firstname.split(',')
        return unicode(firstname[random.randint(0,len(firstname))], errors='ignore')

    def save_user_phone(self, user):
        u'''注册成功的用户名(手机号)保存到txt文档中。\n
            例：
           | Save User Phone         | 13800138000          |
        '''          
        file = open(os.getcwd()+"\\data\\username_phone.txt", 'a')
        file.write('\n'+ user)
        file.close()

    def save_user_email(self, user):
        u'''注册成功的用户名(邮箱)保存到txt文档中。\n
            例：
           | Save User Email         | test@xxx.com          |
        '''          
        file = open(os.getcwd()+"\\data\\username_email.txt", 'a')
        file.write('\n'+ user)
        file.close()
        
if __name__ == '__main__':
#    get = GetReset()
    pass