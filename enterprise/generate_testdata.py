# -*- coding: utf-8 -*-
import random
import datetime
import cx_Oracle

db = cx_Oracle.connect('paishuihu', 'paishuihu', '172.31.0.38:1521/toit')
cursor = db.cursor()

def get_element():
    sql = (
        "select ce.CP_Element_Id from Bas_CheckPoint_Element ce "
        "left join Bas_CheckPoint cp on cp.CheckPoint_Id = ce.CheckPoint_Id "
        "where cp.Source_Id = '999999999999999' and ce.is_deleted = 0 and cp.is_deleted = 0"
    )
    cursor.execute(sql)
    rows = cursor.fetchall()
    return rows

def insert_real(data):
    sql = (
        "insert into Dat_Second(CP_Element_Id, DataDate, Amount) "
        "values(:CP_Element_Id, :DataDate, :Amount)"
    )
    cursor.execute(sql, data)
    db.commit()
    print("实时数据插入成功")

def insert_minute(data):
    sql = (
        "insert into Dat_Minute(CP_Element_Id, DataDate, Min_Amt, Max_Amt, Avg_Amt, Cou_Amt) "
        "values(:CP_Element_Id, :DataDate, :Min_Amt, :Max_Amt, :Avg_Amt, :Cou_Amt)"
    )
    cursor.execute(sql, data)
    db.commit()
    print("分钟数据插入成功")

def insert_hour(data):
    sql = (
        "insert into Dat_Hour(CP_Element_Id, DataDate, Min_Amt, Max_Amt, Avg_Amt, Cou_Amt) "
        "values(:CP_Element_Id, :DataDate, :Min_Amt, :Max_Amt, :Avg_Amt, :Cou_Amt)"
    )
    cursor.execute(sql, data)
    db.commit()
    print("小时数据插入成功")

def insert_day(data):
    sql = (
        "insert into Dat_Day(CP_Element_Id, DataDate, Min_Amt, Max_Amt, Avg_Amt, Cou_Amt) "
        "values(:CP_Element_Id, :DataDate, :Min_Amt, :Max_Amt, :Avg_Amt, :Cou_Amt)"
    )
    cursor.execute(sql, data)
    db.commit()
    print("日数据插入成功")

def generate_data(ele, date):
    min_amt = random.randint(0, 9)
    max_amt = random.randint(min_amt, 18)
    rand = random.randint(min_amt, max_amt)
    avg_amt = round((min_amt + rand + max_amt) / 3)
    cou_amt = rand
    data = {
        "CP_Element_Id": ele[0],
        "DataDate": date,
        "Min_Amt": min_amt,
        "Max_Amt": max_amt,
        "Avg_Amt": avg_amt,
        "Cou_Amt": rand
    }
    return data

def generate_real(elements):
    date = datetime.datetime.strptime("2020-05-25 00:00:00",'%Y-%m-%d %H:%M:%S')
    for i in range(0, 3000):
        for ele in elements:
            amt = random.randint(0, 18)
            data = {
                "CP_Element_Id": ele[0],
                "DataDate": date + datetime.timedelta(seconds=10),
                "Amount": amt
            }
            try:
                insert_real(data)
            except Exception as ex:
                print(ex)
        date = date + datetime.timedelta(seconds=10)


def generate_minute(elements):
    date = datetime.datetime.strptime("2020-05-25 08:00:00",'%Y-%m-%d %H:%M:%S')
    for i in range(0, 30):
        for ele in elements:
            try:
                insert_minute(generate_data(ele, date))
            except Exception as ex:
                print(ex)
        date = date + datetime.timedelta(minutes=10)

def generate_hour(elements):
    date = datetime.datetime.strptime("2020-05-25 00:00:00",'%Y-%m-%d %H:%M:%S')
    for i in range(0, 12):
        for ele in elements:
            try:
                insert_hour(generate_data(ele, date))
            except Exception as ex:
                print(ex)
                pass
        date = date + datetime.timedelta(hours=1)

def generate_day(elements):
    date = datetime.datetime.strptime("2020-05-15",'%Y-%m-%d')
    for i in range(0, 7):
        for ele in elements:
            try:
                insert_day(generate_data(ele, date))
            except Exception as ex:
                print(ex)
        date = date + datetime.timedelta(days=1)


elements = get_element()
generate_real(elements)
# generate_minute(elements)
# generate_hour(elements)
# generate_day(elements)