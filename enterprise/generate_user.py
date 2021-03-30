# -*- coding: utf-8 -*-

import random
import string
import bcrypt
import datetime
import cx_Oracle

db = cx_Oracle.connect('basecenter', 'basecenter', '172.31.0.45:1521/dghb')
cursor = db.cursor()

prefix = "U"
currentValue = 12060

def get_source_list():
    sql = ("select s.source_id, s.full_name from pf_bas_source s "
           "left join pf_bas_source_user u on u.source_id = s.source_id "
           "left join pf_bas_tag_detail t on t.pf_source_id = s.source_id "
           "where s.is_deleted = 0 and u.source_user_id is null and t.tag_head_id in('1012')")
    cursor.execute(sql)
    rows = cursor.fetchall()
    return rows


def create_source_user(user):
    sql = ("insert into pf_bas_source_user(source_user_id, user_name, nick_name, password, memo, source_id) "
           "values(seq_pf_bas_source_user.nextval, :user_name, :nick_name, :password, :memo, :source_id)")
    cursor.execute(sql, user)
    db.commit()
    print("用户 {nick_name} 添加成功".format(**user))

def generate_user_name():
    global currentValue
    date = datetime.date.today().strftime("%Y%m%d")
    currentValue += 1
    return prefix + date + str(currentValue).zfill(6)

choices = ['1234567890', 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', '!$%()+,-.:;>?@[]']

def generate_password():
    result = []
    for i in range(0, 12):
        result.append(random.choice(choices[i % 4]))
    random.shuffle(result)
    return "".join(result)

source_list = get_source_list()
for source in source_list:
    password = generate_password()
    hashed = bcrypt.hashpw(password.encode("utf-8"), bcrypt.gensalt(10, prefix = b"2a"))
    user = {
        "user_name": generate_user_name(),
        "nick_name": source[1],
        "password": bytes.decode(hashed, encoding='utf-8'),
        "memo": password,
        "source_id": source[0]
    }
    print(user)
    create_source_user(user)

